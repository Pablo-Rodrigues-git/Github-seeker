package com.remotex.domain.pullrequestlist.interfaces

import com.remotex.domain.pullrequestlist.models.PullrequestModel

interface PullrequestRepository {
    suspend fun getPulls(owner: String, repo: String): List<PullrequestModel>
}