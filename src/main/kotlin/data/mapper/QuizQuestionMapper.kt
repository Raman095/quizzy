package com.synac.presentation.data.mapper

import data.database.entity.QuizQuestionEntity
import com.synac.presentation.domain.model.QuizQuestion

fun QuizQuestionEntity.toQuizQuestion() = QuizQuestion(
    id =  _id,
    question = question,
    correctAnswer = correctAnswer,
    incorrectAnswer = incorrectAnswer,
    explanation = explanation,
    topicCode = topicCode
)

fun QuizQuestion.toQuizQuestion() = QuizQuestionEntity(
    question = question,
    correctAnswer = correctAnswer,
    incorrectAnswer = incorrectAnswer,
    explanation = explanation,
    topicCode = topicCode
)