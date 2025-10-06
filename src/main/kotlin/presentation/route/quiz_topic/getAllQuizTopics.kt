package com.synac.presentation.presentation.route.quiz_topic

import com.synac.presentation.domain.repository.QuizTopicRepository
import com.synac.presentation.domain.util.onFailure
import com.synac.presentation.domain.util.onSuccess
import com.synac.presentation.presentation.util.respondWithError
import io.ktor.http.HttpStatusCode
import io.ktor.server.routing.Route
import io.ktor.server.resources.*
import io.ktor.server.response.*

fun Route.getAllQuizTopics(
    quizTopicRepository: QuizTopicRepository
) {
    get<QuizTopicRoutePaths> {
        quizTopicRepository.getAllTopics()
            .onSuccess { topics ->
                call.respond(
                    message = topics,
                    status = HttpStatusCode.OK
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}