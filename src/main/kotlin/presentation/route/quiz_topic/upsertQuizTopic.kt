package com.synac.presentation.presentation.route.quiz_topic

import com.synac.presentation.domain.model.QuizTopic
import com.synac.presentation.domain.repository.QuizTopicRepository
import com.synac.presentation.domain.util.onFailure
import com.synac.presentation.domain.util.onSuccess
import com.synac.presentation.presentation.util.respondWithError
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.routing.Route
import io.ktor.server.resources.*
import io.ktor.server.response.respond

fun Route.upsertQuizTopic(
    quizTopicRepository: QuizTopicRepository
) {
    post<QuizTopicRoutePaths> {
        val quizTopic = call.receive<QuizTopic>()
        quizTopicRepository.upsertTopic(quizTopic)
            .onSuccess {
                call.respond(
                    message = "Quiz Topic added",
                    status = HttpStatusCode.Created
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}