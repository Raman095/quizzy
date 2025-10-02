package com.synac.presentation.presentation.config

import com.synac.presentation.data.database.DatabaseFactory
import com.synac.presentation.data.repository.QuizQuestionRepositoryImpl
import com.synac.presentation.domain.repository.QuizQuestionRepository
import com.synac.presentation.presentation.route.quiz_question.deleteQuizQuestionById
import com.synac.presentation.presentation.route.quiz_question.getAllQuizQuestions
import com.synac.presentation.presentation.route.quiz_question.upsertQuizQuestion
import com.synac.presentation.presentation.route.quiz_question.getQuizQuestionById
import com.example.presentation.route.root
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.configureRouting() {

    val mongoDatabase = DatabaseFactory.create()
    val quizQuestionRepository: QuizQuestionRepository = QuizQuestionRepositoryImpl(mongoDatabase)

    routing {

        root()

        getAllQuizQuestions(quizQuestionRepository)

        upsertQuizQuestion(quizQuestionRepository)

        deleteQuizQuestionById(quizQuestionRepository)

        getQuizQuestionById(quizQuestionRepository)

    }
}