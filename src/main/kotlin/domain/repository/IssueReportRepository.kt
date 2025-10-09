package com.synac.presentation.domain.repository

import com.synac.presentation.domain.model.IssueReport
import com.synac.presentation.domain.util.DataError
import com.synac.presentation.domain.util.Result

interface IssueReportRepository {

    suspend fun getAllIssueReports(): Result<List<IssueReport>, DataError>
    suspend fun insertIssueReport(report: IssueReport): Result<Unit, DataError>
    suspend fun deleteIssueReportById(id: String?): Result<Unit, DataError>
}