package com.synac.presentation.presentation.route.quiz_topic

import com.synac.presentation.domain.repository.QuizTopicRepository
import com.synac.presentation.domain.util.onFailure
import com.synac.presentation.domain.util.onSuccess
import com.synac.presentation.presentation.util.respondWithError
import io.ktor.http.HttpStatusCode
import io.ktor.server.routing.Route
import io.ktor.server.resources.*
import io.ktor.server.response.respond

fun Route.getQuizTopicById(
    repository: QuizTopicRepository
) {
    get<QuizTopicRoutePaths.ById> { path ->
        repository.getTopicById(path.topicId)
            .onSuccess { quizTopic ->
                call.respond(
                    message = quizTopic,
                    status = HttpStatusCode.OK
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}