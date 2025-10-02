package com.synac.presentation.presentation.route.quiz_question

import com.synac.presentation.domain.model.QuizQuestion
import com.synac.presentation.domain.repository.QuizQuestionRepository
import com.synac.presentation.domain.util.onFailure
import com.synac.presentation.domain.util.onSuccess
import com.synac.presentation.presentation.util.respondWithError
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post

fun Route.upsertQuizQuestion(
    repository: QuizQuestionRepository
) {
    post(path = "/quiz/questions") {
        val question = call.receive<QuizQuestion>()
        repository.upsertQuestion(question)
            .onSuccess {
                call.respond(
                    message = "Question added successfully",
                    status = HttpStatusCode.Created
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }

    }
}