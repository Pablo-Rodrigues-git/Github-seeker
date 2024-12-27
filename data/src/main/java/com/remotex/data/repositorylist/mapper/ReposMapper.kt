package com.remotex.data.repositorylist.mapper

import com.remotex.data.repositorylist.dto.RepositoryDTO
import com.remotex.data.repositorylist.dto.RepositoryItemsDTO
import com.remotex.data.repositorylist.dto.RepositoryOwnerDTO
import com.remotex.domain.repositorylist.models.RepositoryItemsModel
import com.remotex.domain.repositorylist.models.RepositoryModel
import com.remotex.domain.repositorylist.models.RepositoryOwnerModel


class ReposMapper {

    fun toReposItemsModel(repositoryItemsDTO: RepositoryItemsDTO): RepositoryItemsModel {
        return RepositoryItemsModel(
            items = repositoryItemsDTO.items.map {
                it.let(::toReposModel)
            }
        )
    }

    private fun toReposModel(repositoryDTO: RepositoryDTO): RepositoryModel {
        return RepositoryModel(
            name = repositoryDTO.name,
            description = repositoryDTO.description,
            stargazersCount = repositoryDTO.stargazersCount,
            forksCount = repositoryDTO.forksCount,
            owner = repositoryDTO.owner.let(::toReposOwnerModel)
        )
    }

    private fun toReposOwnerModel(repositoryOwnerDTO: RepositoryOwnerDTO): RepositoryOwnerModel {
        return RepositoryOwnerModel(
            login = repositoryOwnerDTO.login,
            avatarUrl = repositoryOwnerDTO.avatarUrl
        )
    }
}
