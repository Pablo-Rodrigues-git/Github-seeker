package com.remotex.data.pullrequestlist.repository

import com.remotex.data.pullrequestlist.mapper.PullsMapper
import com.remotex.data.pullrequestlist.service.PullsService
import com.remotex.domain.pullrequestlist.interfaces.PullrequestRepository
import com.remotex.domain.pullrequestlist.models.PullrequestModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PullrequestRepositoryImpl(
    private val service: PullsService,
    private val mapper: PullsMapper
): PullrequestRepository {
    override suspend fun getPulls(owner: String, repo: String): List<PullrequestModel> =
        withContext(Dispatchers.IO) {
            val response = service.getPulls(owner, repo)
            mapper.toPullsModel(response)
        }
}