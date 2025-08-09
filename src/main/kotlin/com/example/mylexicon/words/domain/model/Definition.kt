package com.example.mylexicon.words.domain.model

data class Definition(
    val text: String,
    val partOfSpeech: String? = null,
    val example: String? = null,
)