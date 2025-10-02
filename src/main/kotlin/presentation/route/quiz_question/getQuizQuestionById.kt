package com.synac.presentation.presentation.route.quiz_question

import com.synac.presentation.domain.repository.QuizQuestionRepository
import com.synac.presentation.domain.util.onFailure
import com.synac.presentation.domain.util.onSuccess
import com.synac.presentation.presentation.util.respondWithError
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.getQuizQuestionById(
    repository: QuizQuestionRepository
) {
    get("/quiz/questions/{questionId}") {
        val id = call.parameters["questionId"]
        repository.getQuestionById(id)
            .onSuccess { question ->
                call.respond(
                    message = question,
                    status = HttpStatusCode.OK
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
        }
    }