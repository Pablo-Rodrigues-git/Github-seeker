package com.remotex.feature.pullrequestlist

import com.remotex.domain.pullrequestlist.interfaces.PullrequestRepository
import com.remotex.domain.pullrequestlist.models.PullrequestModel
import com.remotex.domain.pullrequestlist.models.PullrequestUserModel
import com.remotex.feature.presentation.screens.pullrequestlist.PullrequestListViewModel
import com.remotex.feature.presentation.screens.pullrequestlist.PullrequestListViewState
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
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

@OptIn(ExperimentalCoroutinesApi::class)
class PullrequestViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private val repository = mockk<PullrequestRepository>()
    private val viewModel = PullrequestListViewModel(repository)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Success get pull-requests`() = runTest {
        val pulls = getListOfPulls()
        coEvery { repository.getPulls(any(), any()) } returns pulls

        viewModel.getPulls("owner", "repo")

        advanceUntilIdle()

        val state = viewModel.pullrequestListViewState.first()
        assertEquals(state, PullrequestListViewState.Success(pulls))
    }

    @Test
    fun `Error geting pull-requests`() = runTest {
        val errorMessage = "Network error"
        coEvery { repository.getPulls(any(), any()) } throws Exception(errorMessage)

        viewModel.getPulls("owner", "repo")

        advanceUntilIdle()

        val state = viewModel.pullrequestListViewState.first()
        assertEquals(state, PullrequestListViewState.Error)
    }

    private fun getListOfPulls(): List<PullrequestModel> = listOf(
        PullrequestModel(
            title = "Pull request 1",
            body = "Descrição da pull request",
            createdAt = "2023-06-01T12:00:00Z",
            htmlUrl = "",
            user = PullrequestUserModel(
                login = "pablo-rodrigues-git",
                avatarUrl = "https://avatars.githubusercontent.com/u/385686000?v=4"
            )
        ),
        PullrequestModel(
            title = "Pull request 1",
            body = "Descrição da pull request",
            createdAt = "2023-06-01T12:00:00Z",
            htmlUrl = "",
            user = PullrequestUserModel(
                login = "pablo-rodrigues-git",
                avatarUrl = "https://avatars.githubusercontent.com/u/385686000?v=4"
            )
        ),
        PullrequestModel(
            title = "Pull request 1",
            body = "Descrição da pull request",
            createdAt = "2023-06-01T12:00:00Z",
            htmlUrl = "",
            user = PullrequestUserModel(
                login = "pablo-rodrigues-git",
                avatarUrl = "https://avatars.githubusercontent.com/u/385686000?v=4"
            )
        ),
        PullrequestModel(
            title = "Pull request 1",
            body = "Descrição da pull request",
            createdAt = "2023-06-01T12:00:00Z",
            htmlUrl = "",
            user = PullrequestUserModel(
                login = "pablo-rodrigues-git",
                avatarUrl = "https://avatars.githubusercontent.com/u/385686000?v=4"
            )
        )
    )
}