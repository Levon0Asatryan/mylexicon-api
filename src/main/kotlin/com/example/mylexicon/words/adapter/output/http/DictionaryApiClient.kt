package com.example.mylexicon.words.adapter.output.http

import com.example.mylexicon.words.application.port.output.DictionaryLookupPort
import com.example.mylexicon.words.domain.model.Definition
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class DictionaryApiClient(
    webClient: WebClient,
    @Value("\${dictionary.base-url}") private val baseUrl: String
) : DictionaryLookupPort {
    private val client = webClient.mutate().baseUrl(baseUrl).build()

    override fun fetchDefinitions(word: String): List<Definition> {
        val payload = client.get()
            .uri("/{word}", word)
            .exchangeToMono { rsp ->
                when (rsp.statusCode()) {
                    HttpStatus.OK ->
                        rsp.bodyToMono(List::class.java).map { it }

                    HttpStatus.NOT_FOUND ->
                        Mono.just(emptyList())

                    else ->
                        rsp.createException().flatMap { e -> Mono.error<List<Any>>(e) }
                }
            }
            .block() ?: return emptyList()


        return runCatching {
            val first = payload.firstOrNull() as? Map<*, *> ?: return@runCatching emptyList<Definition>()
            val meanings = first["meanings"] as? List<*> ?: return@runCatching emptyList<Definition>()

            meanings.flatMap { m ->
                val mMap = m as? Map<*, *> ?: return@flatMap emptyList<Definition>()
                val pos = mMap["partOfSpeech"] as? String
                val defs = mMap["definitions"] as? List<*> ?: emptyList<Any>()

                defs.mapNotNull { d ->
                    val dm = d as? Map<*, *> ?: return@mapNotNull null
                    val text = (dm["definition"] as? String)?.trim()
                    if (text.isNullOrEmpty()) null
                    else Definition(
                        text = text,
                        partOfSpeech = pos,
                        example = dm["example"] as? String
                    )
                }
            }
        }.getOrElse { emptyList() }
    }
}