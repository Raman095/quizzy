package com.synac.presentation.presentation.config

import com.synac.presentation.domain.repository.QuizQuestionRepository
import com.synac.presentation.presentation.route.quiz_question.deleteQuizQuestionById
import com.synac.presentation.presentation.route.quiz_question.getAllQuizQuestions
import com.synac.presentation.presentation.route.quiz_question.upsertQuizQuestion
import com.synac.presentation.presentation.route.quiz_question.getQuizQuestionById
import com.example.presentation.route.root
import com.synac.presentation.domain.repository.IssueReportRepository
import com.synac.presentation.domain.repository.QuizTopicRepository
import com.synac.presentation.presentation.route.issue_report.deleteIssueReportById
import com.synac.presentation.presentation.route.issue_report.getAllIssueReports
import com.synac.presentation.presentation.route.issue_report.insertIssueReport
import com.synac.presentation.presentation.route.quiz_topic.deleteQuizTopicById
import com.synac.presentation.presentation.route.quiz_topic.getAllQuizTopics
import com.synac.presentation.presentation.route.quiz_topic.getQuizTopicById
import com.synac.presentation.presentation.route.quiz_topic.upsertQuizTopic
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.http.content.staticResources
import io.ktor.server.resources.Resources
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    install(Resources)

    val quizQuestionRepository: QuizQuestionRepository by inject()
    val quizTopicRepository: QuizTopicRepository by inject()
    val issueReportRepository: IssueReportRepository by inject()

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

        // Issue Report
        deleteIssueReportById(issueReportRepository)
        getAllIssueReports(issueReportRepository)
        insertIssueReport(issueReportRepository)

        staticResources(
            remotePath = "/images",
            basePackage = "images"
        )
    }
}