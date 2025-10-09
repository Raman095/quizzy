package com.synac.presentation.di

import com.synac.presentation.data.database.DatabaseFactory
import com.synac.presentation.data.repository.IssueReportRepositoryImpl
import com.synac.presentation.data.repository.QuizQuestionRepositoryImpl
import com.synac.presentation.data.repository.QuizTopicRepositoryImpl
import com.synac.presentation.domain.repository.IssueReportRepository
import com.synac.presentation.domain.repository.QuizQuestionRepository
import com.synac.presentation.domain.repository.QuizTopicRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val koinModule = module {

    single { DatabaseFactory.create() }
    singleOf(::QuizQuestionRepositoryImpl).bind<QuizQuestionRepository>()
    singleOf(::QuizTopicRepositoryImpl).bind<QuizTopicRepository>()
    singleOf(::IssueReportRepositoryImpl).bind<IssueReportRepository>()

}