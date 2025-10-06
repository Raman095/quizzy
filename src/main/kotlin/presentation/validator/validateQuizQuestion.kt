package com.synac.presentation.presentation.validator

import com.synac.presentation.domain.model.QuizQuestion
import io.ktor.server.plugins.requestvalidation.RequestValidationConfig
import io.ktor.server.plugins.requestvalidation.ValidationResult

fun RequestValidationConfig.validateQuizQuestion() {
    validate<QuizQuestion> { quizQuestion ->
        when {
            quizQuestion.question.isBlank() || quizQuestion.question.length < 5 -> {
                ValidationResult.Invalid (
                    reason = "Question must not be empty and should be at least 5 characters long "
                )
            }
            quizQuestion.correctAnswer.isBlank() -> {
                ValidationResult.Invalid (
                    reason = "Correct answer must not be empty"
                )
            }
            quizQuestion.incorrectAnswer.isEmpty()  -> {
                ValidationResult.Invalid (
                    reason = "There must be at least one incorrect answer"
                )
            }
            quizQuestion.incorrectAnswer.any { it.isBlank()} -> {
                ValidationResult.Invalid(
                    reason = "Incorrect answers must not be empty"
                )
            }
            quizQuestion.explanation.isBlank() -> {
                ValidationResult.Invalid (
                    reason = "Explanation must not be empty"
                )
            }
            quizQuestion.topicCode <= 0 -> {
                ValidationResult.Invalid (
                    reason = "Topic Code must be a positive integer"
                )
            }
            else -> {
            ValidationResult.Valid
        }
        }
    }
}