package com.example.mylexicon.words.application.port.output

import com.example.mylexicon.words.domain.model.Definition

interface DictionaryLookupPort {
    fun fetchDefinitions(word: String): List<Definition>
}