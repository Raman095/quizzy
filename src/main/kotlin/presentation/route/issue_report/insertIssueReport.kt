package com.synac.presentation.presentation.route.issue_report

import com.synac.presentation.domain.model.IssueReport
import com.synac.presentation.domain.repository.IssueReportRepository
import com.synac.presentation.domain.util.onFailure
import com.synac.presentation.domain.util.onSuccess
import com.synac.presentation.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route

fun Route.insertIssueReport(
    repository: IssueReportRepository
) {
    post<IssueReportRoutesPath> {
        val report = call.receive<IssueReport>()
        repository.insertIssueReport(report)
            .onSuccess {
                call.respond(
                    message = "Report submitted successfully",
                    status = HttpStatusCode.Created
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
        }
    }