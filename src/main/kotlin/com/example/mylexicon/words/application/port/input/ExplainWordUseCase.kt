package com.example.mylexicon.words.application.port.input

import com.example.mylexicon.words.domain.model.WordExplanation

interface ExplainWordUseCase {
    fun explain(word: String): WordExplanation
}