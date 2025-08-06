package com.example.mylexicon.words.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class WordController {

    @GetMapping("/words/{word}/explanation")
     fun getWordExplanation(@PathVariable word: String,): String {
        // This is a placeholder for the actual implementation
        // In a real application, you would fetch the explanation from a service or database
        return "Explanation for the word '$word'"
     }
}