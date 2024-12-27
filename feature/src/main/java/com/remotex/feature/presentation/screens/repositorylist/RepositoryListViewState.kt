package com.remotex.feature.presentation.screens.repositorylist

import com.remotex.domain.repositorylist.models.RepositoryItemsModel

sealed class RepositoryListViewState {
    data class Success(val repos: RepositoryItemsModel) : RepositoryListViewState()
    data class LoadMore(val repos: RepositoryItemsModel) : RepositoryListViewState()
    data object Empty : RepositoryListViewState()
    data object Error : RepositoryListViewState()
    data object Loading : RepositoryListViewState()
}
