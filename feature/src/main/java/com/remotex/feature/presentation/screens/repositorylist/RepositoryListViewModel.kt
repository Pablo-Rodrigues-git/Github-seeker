package com.remotex.feature.presentation.screens.repositorylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.remotex.domain.repositorylist.interfaces.ReposRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryListViewModel @Inject constructor(private val repository: ReposRepository) : ViewModel() {

    private val _repositoryListViewState: MutableStateFlow<RepositoryListViewState> = MutableStateFlow(
        RepositoryListViewState.Loading
    )
    val repositoryListViewState: StateFlow<RepositoryListViewState> = _repositoryListViewState

    private var currentPage: Int = 0

    fun getRepos() {
        viewModelScope.launch {
            _repositoryListViewState.value = RepositoryListViewState.Loading
            try {
                val result = repository.getRepos(currentPage)
                _repositoryListViewState.value = RepositoryListViewState.Success(result)
            } catch (e: Exception) {
                e.printStackTrace()
                _repositoryListViewState.value = RepositoryListViewState.Error
            }
        }
    }

    fun loadMore() {
        currentPage++
        viewModelScope.launch {
            try {
                val result = repository.getRepos(currentPage)
                _repositoryListViewState.value = RepositoryListViewState.LoadMore(result)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}