package com.example.mylexicon.words.adapter.input.web.controller

import com.example.mylexicon.words.adapter.input.web.response.WordExplanationResponse
import com.example.mylexicon.words.application.port.input.ExplainWordUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class WordController(
    private val explainWordUseCase: ExplainWordUseCase
) {

    @GetMapping("/words/{word}/explanation")
    fun getWordExplanation(@PathVariable word: String): WordExplanationResponse {
        return WordExplanationResponse.from(explainWordUseCase.explain(word))
    }
}