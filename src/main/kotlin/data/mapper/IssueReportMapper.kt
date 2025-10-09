package com.synac.presentation.data.mapper

import com.synac.presentation.data.database.entity.IssueReportEntity
import com.synac.presentation.domain.model.IssueReport

fun IssueReportEntity.toIssueReport()= IssueReport(
    id = _id,
    questionId = questionId,
    issueType = issueType,
    additionalComment = additionalComment,
    userEmail = userEmail,
    timestamp = timestamp
)

fun IssueReport.toIssueReportEntity()= IssueReportEntity(
    questionId = questionId,
    issueType = issueType,
    additionalComment = additionalComment,
    userEmail = userEmail,
    timestamp = timestamp
)