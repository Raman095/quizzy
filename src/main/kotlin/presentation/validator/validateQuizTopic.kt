package com.synac.presentation.presentation.validator

import com.synac.presentation.domain.model.QuizTopic
import io.ktor.server.plugins.requestvalidation.RequestValidationConfig
import io.ktor.server.plugins.requestvalidation.ValidationResult

fun RequestValidationConfig.validateQuizTopic() {
    validate<QuizTopic> { quizTopic ->
        when {
            quizTopic.name.isBlank() || quizTopic.name.length < 3 -> {
                ValidationResult.Invalid (
                    reason = "Topic name must not be empty and should be at least 5 characters long "
                )
            }
            quizTopic.imageUrl.isBlank() -> {
                ValidationResult.Invalid (
                    reason = "Image url must not be empty"
                )
            }
            quizTopic.code < 0 -> {
                ValidationResult.Invalid (
                    reason = "Topic Code must be a whole number"
                )
            }
            else -> {
            ValidationResult.Valid
        }
        }
    }
}