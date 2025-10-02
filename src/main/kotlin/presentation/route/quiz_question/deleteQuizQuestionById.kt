package com.synac.presentation.presentation.route.quiz_question

import com.synac.presentation.domain.repository.QuizQuestionRepository
import com.synac.presentation.domain.util.onFailure
import com.synac.presentation.domain.util.onSuccess
import com.synac.presentation.presentation.util.respondWithError
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete

fun Route.deleteQuizQuestionById (
    repository: QuizQuestionRepository
) {
    delete("/quiz/questions/{questionId}") {
        val id = call.parameters["questionId"]
        repository.deleteQuestionById(id)
            .onSuccess {
                call.respond(
                    HttpStatusCode.NoContent
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
        }
    }