package com.synac.presentation.domain.repository

import com.synac.presentation.domain.model.QuizQuestion
import com.synac.presentation.domain.util.DataError
import com.synac.presentation.domain.util.Result

interface QuizQuestionRepository {

    suspend fun upsertQuestion (
        question: QuizQuestion
    ): Result<Unit, DataError>

    suspend fun getAllQuestions (
        topicCode: Int?,
        limit: Int?
    ): Result<List<QuizQuestion>, DataError>

    suspend fun getQuestionById (
        id: String?
    ): Result<QuizQuestion, DataError>

    suspend fun deleteQuestionById (
        id: String?
    ): Result<Unit, DataError>

}