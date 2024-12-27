package com.remotex.domain.repositorylist.interfaces

import com.remotex.domain.repositorylist.models.RepositoryItemsModel

interface ReposRepository {
    suspend fun getRepos(currentPage: Int = 0): RepositoryItemsModel
}