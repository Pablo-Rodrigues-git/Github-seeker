package com.remotex.feature.presentation.screens.pullrequestlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.remotex.domain.pullrequestlist.interfaces.PullrequestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PullrequestListViewModel @Inject constructor(private val repository: PullrequestRepository) : ViewModel() {

    private val _pullrequestListViewState: MutableStateFlow<PullrequestListViewState> = MutableStateFlow(
        PullrequestListViewState.Loading
    )
    val pullrequestListViewState: StateFlow<PullrequestListViewState> = _pullrequestListViewState

    fun getPulls(owner: String, repo: String) {
        viewModelScope.launch {
            try {
                val result = repository.getPulls(owner, repo)
                _pullrequestListViewState.value = PullrequestListViewState.Success(result)
            } catch (e: Exception) {
                e.printStackTrace()
                _pullrequestListViewState.value = PullrequestListViewState.Error
            }
        }
    }
}