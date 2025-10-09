package com.synac.presentation.presentation.route.quiz_topic

import io.ktor.resources.Resource

@Resource("/quiz/topics")
class QuizTopicRoutePaths {

    @Resource(path = "{topicId}")
    data class ById(
        val parent: QuizTopicRoutePaths = QuizTopicRoutePaths(),
        val topicId: String
    )

}