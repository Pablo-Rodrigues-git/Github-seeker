package com.remotex.data.repositorylist.repository

import com.remotex.data.repositorylist.mapper.ReposMapper
import com.remotex.data.repositorylist.service.RepositoryService
import com.remotex.domain.repositorylist.interfaces.ReposRepository
import com.remotex.domain.repositorylist.models.RepositoryItemsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReposRepositoryImpl(
    private val service: RepositoryService,
    private val mapper: ReposMapper
) : ReposRepository {

    override suspend fun getRepos(currentPage: Int): RepositoryItemsModel = withContext(Dispatchers.IO) {
        val response = service.getRepos(page = currentPage)
        mapper.toReposItemsModel(response)
    }
}
