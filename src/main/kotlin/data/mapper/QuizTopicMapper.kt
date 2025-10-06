package com.synac.presentation.data.mapper

import com.synac.presentation.data.database.entity.QuizTopicEntity
import com.synac.presentation.domain.model.QuizTopic

fun QuizTopicEntity.toQuizTopic()= QuizTopic(
    id = _id,
    name = name,
    imageUrl = imageUrl,
    code = code
)

fun QuizTopic.toQuizTopicEntity()= QuizTopicEntity(
    name = name,
    imageUrl = imageUrl,
    code = code
)