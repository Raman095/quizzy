package com.example.presentation.config

import com.example.data.database.DatabaseFactory
import com.example.data.repository.QuizQuestionRepositoryImpl
import com.example.domain.model.QuizQuestion
import com.example.domain.repository.QuizQuestionRepository
import com.example.presentation.route.quiz_question.deleteQuizQuestionById
import com.example.presentation.route.quiz_question.getAllQuizQuestions
import com.example.presentation.route.quiz_question.upsertQuizQuestion
import com.example.presentation.route.quiz_question.getQuizQuestionById
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