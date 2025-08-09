package com.example.mylexicon.words.adapter.input.web

import com.example.mylexicon.words.domain.exception.WordNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class WordExceptionHandler {

    data class ApiError(val status: Int, val error: String, val message: String)

    @ExceptionHandler(WordNotFoundException::class)
    fun handleNotFound(ex: WordNotFoundException): ResponseEntity<ApiError> =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ApiError(
                status = HttpStatus.NOT_FOUND.value(),
                error = "Not Found",
                message = ex.message ?: "Word not found"
            )
        )
}