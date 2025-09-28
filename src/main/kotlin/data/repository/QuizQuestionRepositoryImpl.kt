package com.example.data.repository

import com.example.data.database.entity.QuizQuestionEntity
import com.example.data.mapper.toQuizQuestion
import com.example.domain.model.QuizQuestion
import com.example.domain.repository.QuizQuestionRepository
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.example.data.util.constant.QUESTIONS_COLLECTION_NAME

class QuizQuestionRepositoryImpl(
    mongoDatabase: MongoDatabase
): QuizQuestionRepository {

    private val questionCollection = mongoDatabase
        .getCollection<QuizQuestionEntity>(QUESTIONS_COLLECTION_NAME)

    private val quizQuestions = mutableListOf<QuizQuestion>()

    override suspend fun upsertQuestion(question: QuizQuestion) {
        questionCollection.insertOne(question.toQuizQuestion())
    }

    override suspend fun getAllQuestions(
        topicCode: Int?,
        limit: Int?
    ): List<QuizQuestion> {
        return if (topicCode != null) {
            quizQuestions
                .filter { it.topicCode == topicCode }
                .take(limit ?: quizQuestions.size)
        }
        else {
            quizQuestions.take(limit ?: quizQuestions.size)
        }
    }

    override suspend fun getQuestionById(id: String): QuizQuestion? {
        return quizQuestions.find { it.id == id }
    }

    override suspend fun deleteQuestionById(id: String?): Boolean {
        return quizQuestions.removeIf { it.id == id }
    }
}