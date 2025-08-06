package com.example.mylexicon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyLexiconApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<MyLexiconApplication>(*args)
        }
    }
}
