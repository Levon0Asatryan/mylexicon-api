package com.example.mylexicon.words.adapter.input.web.response

import com.example.mylexicon.words.domain.model.WordExplanation

data class WordExplanationResponse(val word: String, val definitions: List<DefinitionDto>) {
    data class DefinitionDto(
        val text: String,
        val partOfSpeech: String?,
        val example: String?
    )

    companion object {
        fun from(domain: WordExplanation) = WordExplanationResponse(
            word = domain.word,
            definitions = domain.definitions.map { d ->
                DefinitionDto(d.text, d.partOfSpeech, d.example)
            }
        )
    }
}