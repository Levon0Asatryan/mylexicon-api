package com.example.mylexicon.words.application.service

import com.example.mylexicon.words.application.port.input.ExplainWordUseCase
import com.example.mylexicon.words.application.port.output.DictionaryLookupPort
import com.example.mylexicon.words.domain.exception.WordNotFoundException
import com.example.mylexicon.words.domain.model.WordExplanation
import com.example.mylexicon.words.domain.model.WordId
import org.springframework.stereotype.Service

@Service
class ExplainWordService(
    private val dictionaryLookupPort: DictionaryLookupPort
) : ExplainWordUseCase {

    override fun explain(word: String): WordExplanation {
        val id = WordId.of(word)

        val defs = dictionaryLookupPort.fetchDefinitions(id.value)

        if (defs.isEmpty()) {
            throw WordNotFoundException(id)
        }

        return WordExplanation(word = word, definitions = defs)
    }
}