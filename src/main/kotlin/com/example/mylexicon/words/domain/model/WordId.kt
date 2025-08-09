package com.example.mylexicon.words.domain.model

@JvmInline
value class WordId private constructor(val value: String) {
    companion object {
        fun of(raw: String): WordId {
            val normalized = raw.trim().lowercase()
            require(normalized.isNotEmpty()) { "WordId cannot be blank" }
            // keep it simple for now; relax/extend later if you need digits etc.
            require(normalized.matches(Regex("[a-z][a-z\\-']*"))) { "Invalid WordId: $raw" }
            return WordId(normalized)
        }
    }

    override fun toString(): String = value
}