package com.synac.presentation.domain.repository

import com.synac.presentation.domain.model.QuizTopic
import com.synac.presentation.domain.util.DataError
import com.synac.presentation.domain.util.Result

interface QuizTopicRepository {

    suspend fun getAllTopics(): Result<List<QuizTopic>, DataError>
    suspend fun upsertTopic(topic: QuizTopic): Result<Unit, DataError>
    suspend fun getTopicById(id: String?): Result<QuizTopic, DataError>
    suspend fun deleteTopicById(id: String?): Result<Unit, DataError>
}