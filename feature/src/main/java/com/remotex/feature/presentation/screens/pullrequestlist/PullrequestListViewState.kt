package com.remotex.feature.presentation.screens.pullrequestlist

import com.remotex.domain.pullrequestlist.models.PullrequestModel

sealed class PullrequestListViewState {
    data class Success(val pulls: List<PullrequestModel>) : PullrequestListViewState()
    data object Empty : PullrequestListViewState()
    data object Error : PullrequestListViewState()
    data object Loading : PullrequestListViewState()
}