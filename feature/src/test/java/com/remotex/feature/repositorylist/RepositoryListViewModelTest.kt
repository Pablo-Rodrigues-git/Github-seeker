package com.remotex.feature.repositorylist

import com.remotex.domain.repositorylist.interfaces.ReposRepository
import com.remotex.feature.presentation.screens.repositorylist.RepositoryListViewModel
import com.remotex.feature.presentation.screens.repositorylist.RepositoryListViewState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private val repository = mockk<ReposRepository>()
    private val viewModel = RepositoryListViewModel(repository)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN repository returns success WHEN getting list of repositories THEN state is Success`() = runTest {
        val repos = RepositoryTestHelper.getListOfRepos()
        coEvery { repository.getRepos(any()) } returns repos

        viewModel.getRepos()

        advanceUntilIdle()

        val state = viewModel.repositoryListViewState.first()
        assertEquals(state, RepositoryListViewState.Success(repos))
    }

    @Test
    fun `GIVEN repository throws error WHEN getting list of repositories THEN state is Error`() = runTest {
        val errorMessage = "Network error"
        coEvery { repository.getRepos(any()) } throws Exception(errorMessage)

        viewModel.getRepos()

        advanceUntilIdle()

        val state = viewModel.repositoryListViewState.first()
        assertEquals(state, RepositoryListViewState.Error)
    }
}