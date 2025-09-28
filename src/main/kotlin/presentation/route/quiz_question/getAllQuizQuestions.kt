package com.example.presentation.route.quiz_question


import com.example.domain.repository.QuizQuestionRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.getAllQuizQuestions(
    repository: QuizQuestionRepository
) {
    get(path = "/quiz/questions") {
        val topicCode = call.queryParameters["topicCode"]?.toIntOrNull()
        val limit = call.queryParameters["limit"]?.toIntOrNull()
        val filteredQuestions = repository.getAllQuestions(topicCode, limit)
        if(filteredQuestions.isNotEmpty()) {
            call.respond(
                message = filteredQuestions,
                status = HttpStatusCode.OK
            )
        }
        else {
            call.respond(
                message = "No Question Found",
                status = HttpStatusCode.NotFound
            )
        }
    }
}