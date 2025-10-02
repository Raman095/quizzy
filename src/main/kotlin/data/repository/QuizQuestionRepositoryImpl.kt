package com.synac.presentation.data.repository

import data.database.entity.QuizQuestionEntity
import com.synac.presentation.data.mapper.toQuizQuestion
import com.synac.presentation.domain.model.QuizQuestion
import com.synac.presentation.domain.repository.QuizQuestionRepository
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.synac.presentation.data.util.constant.QUESTIONS_COLLECTION_NAME
import com.synac.presentation.domain.util.DataError
import com.synac.presentation.domain.util.Result
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class QuizQuestionRepositoryImpl(
    mongoDatabase: MongoDatabase
): QuizQuestionRepository {

    private val questionCollection = mongoDatabase
        .getCollection<QuizQuestionEntity>(QUESTIONS_COLLECTION_NAME)


    override suspend fun upsertQuestion(
        question: QuizQuestion
    ): Result<Unit, DataError> {
        return try {
            if (question.id == null) {
                questionCollection.insertOne(question.toQuizQuestion())
            } else {
                val filterQuery = Filters.eq(
                    QuizQuestionEntity::_id.name, question.id
                )
                val updateQuery = Updates.combine(
                    Updates.set(QuizQuestionEntity::question.name, question.question),
                    Updates.set(QuizQuestionEntity::correctAnswer.name, question.correctAnswer),
                    Updates.set(QuizQuestionEntity::incorrectAnswer.name, question.incorrectAnswer),
                    Updates.set(QuizQuestionEntity::explanation.name, question.explanation),
                    Updates.set(QuizQuestionEntity::topicCode.name, question.topicCode)
                )
                val updateResult = questionCollection.updateOne(filterQuery, updateQuery)
                if (updateResult.modifiedCount == 0L) {
                    return Result.Failure(DataError.NotFound)
                }
            }
            Result.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun getAllQuestions(
        topicCode: Int?,
        limit: Int?
    ): Result<List<QuizQuestion>, DataError> {
        return try {
            val questionLimit = limit?.takeIf { it > 0 } ?: 10

            val filterQuery = topicCode?.let {
                Filters.eq(QuizQuestionEntity::topicCode.name, it)
            } ?: Filters.empty()

            val questions = questionCollection
                .find(filter = filterQuery)
                .limit(questionLimit)
                .map { it.toQuizQuestion() }
                .toList()

            if(questions.isNotEmpty()) {
                Result.Success(questions)
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch(e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun getQuestionById(
        id: String?
    ): Result<QuizQuestion, DataError> {
        if (id.isNullOrBlank()) {
            return Result.Failure(DataError.Validation)
        }
        return try {
            val filterQuery = Filters.eq(
                QuizQuestionEntity::_id.name, id
            )
            val questionEntity = questionCollection
                .find(filter = filterQuery)
                .firstOrNull()
            if (questionEntity != null) {
                val question = questionEntity.toQuizQuestion()
                Result.Success(question)
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch(e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun deleteQuestionById(
        id: String?
    ): Result<Unit, DataError> {
        if (id.isNullOrBlank()) {
            return Result.Failure(DataError.Validation)
        }
        return try {
            val filterQuery = Filters.eq (
            QuizQuestionEntity::_id.name, id
            )
            val deleteResult = questionCollection
                .deleteOne(
                    filter = filterQuery
                )
            if(deleteResult.deletedCount > 0) {
                Result.Success(Unit)
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch(e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }
}