package com.example.presentation.route.quiz_question

import com.example.domain.model.QuizQuestion
import com.example.domain.repository.QuizQuestionRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.getQuizQuestionById(
    repository: QuizQuestionRepository
) {
    get("/quiz/questions/{questionId}") {
        val id = call.parameters["questionId"]
        if (id.isNullOrBlank()) {
            call.respond(
                message = "Question Id needed",
                status = HttpStatusCode.BadRequest
            )
            return@get
        }
        val question = repository.getQuestionById(id)
        if (question != null) {
            call.respond(
                message = question,
                status = HttpStatusCode.OK
            )
        }
        else {
            call.respond(
                message = "Question not found",
                status = HttpStatusCode.NotFound
            )
        }
    }
}