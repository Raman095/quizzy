package com.synac.presentation.presentation.config

import com.synac.presentation.data.database.DatabaseFactory
import com.synac.presentation.data.repository.QuizQuestionRepositoryImpl
import com.synac.presentation.domain.repository.QuizQuestionRepository
import com.synac.presentation.presentation.route.quiz_question.deleteQuizQuestionById
import com.synac.presentation.presentation.route.quiz_question.getAllQuizQuestions
import com.synac.presentation.presentation.route.quiz_question.upsertQuizQuestion
import com.synac.presentation.presentation.route.quiz_question.getQuizQuestionById
import com.example.presentation.route.root
import com.synac.presentation.data.repository.QuizTopicRepositoryImpl
import com.synac.presentation.domain.repository.QuizTopicRepository
import com.synac.presentation.presentation.route.quiz_topic.deleteQuizTopicById
import com.synac.presentation.presentation.route.quiz_topic.getAllQuizTopics
import com.synac.presentation.presentation.route.quiz_topic.getQuizTopicById
import com.synac.presentation.presentation.route.quiz_topic.upsertQuizTopic
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.resources.Resources
import io.ktor.server.routing.routing

fun Application.configureRouting() {

    install(Resources)

    val mongoDatabase = DatabaseFactory.create()
    val quizQuestionRepository: QuizQuestionRepository = QuizQuestionRepositoryImpl(mongoDatabase)
    val quizTopicRepository: QuizTopicRepository = QuizTopicRepositoryImpl(mongoDatabase)

    routing {

        root()

        // Quiz Question
        getAllQuizQuestions(quizQuestionRepository)
        upsertQuizQuestion(quizQuestionRepository)
        deleteQuizQuestionById(quizQuestionRepository)
        getQuizQuestionById(quizQuestionRepository)

        // Quiz Topic
        getAllQuizTopics(quizTopicRepository)
        upsertQuizTopic(quizTopicRepository)
        getQuizTopicById(quizTopicRepository)
        deleteQuizTopicById(quizTopicRepository)
    }
}