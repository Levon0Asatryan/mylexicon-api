package com.example.mylexicon.words.domain.exception

import com.example.mylexicon.words.domain.model.WordId

class WordNotFoundException(id: WordId) : RuntimeException("Word not found: ${id.value}")