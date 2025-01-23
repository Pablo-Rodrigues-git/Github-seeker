package com.remotex.feature.presentation.screens.pullrequestlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.remotex.domain.pullrequestlist.models.PullrequestModel
import com.remotex.domain.pullrequestlist.models.PullrequestUserModel
import com.remotex.feature.R

@Composable
fun PullrequestListScreen(
    navController: NavController,
    viewModel: PullrequestListViewModel,
    owner: String,
    repo: String
) {

    val state by viewModel.pullrequestListViewState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getPulls(owner, repo)
    }

    Scaffold(
        topBar = { Header(repo = repo, navController = navController) }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            when (state) {
                is PullrequestListViewState.Success -> SetupSuccess(items = (state as PullrequestListViewState.Success).pulls)
                is PullrequestListViewState.Empty -> SetupEmpty()
                is PullrequestListViewState.Error -> SetupError(tryAgain = { viewModel.getPulls(owner, repo) } )
                is PullrequestListViewState.Loading -> SetupLoading()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(
    repo: String,
    navController: NavController
) {
    TopAppBar(
        title = {
            Text(text = repo)
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.go_back_to_the_repository_list)
                )
            }
        }
    )
}

@Composable
fun SetupSuccess(items: List<PullrequestModel>) {
    LazyColumn {
        items(items) { item ->
            PullrequestListItem(
                pullrequestModel = item
            )
        }
    }
}

@Composable
fun SetupEmpty() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.no_pull_request_found)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SetupSuccessPreview() {
    SetupSuccess(
        items = listOf(
            PullrequestModel(
                title = stringResource(R.string.pull_request_1),
                body = stringResource(R.string.pull_request_description),
                createdAt = stringResource(R.string.fake_date),
                htmlUrl = "",
                user = PullrequestUserModel(
                    login = stringResource(R.string.test_user),
                    avatarUrl = "https://avatars.githubusercontent.com/u/76755413?v=4"
                )
            ),
            PullrequestModel(
                title = stringResource(R.string.pull_request_1),
                body = stringResource(R.string.pull_request_description),
                createdAt = stringResource(R.string.fake_date),
                htmlUrl = "",
                user = PullrequestUserModel(
                    login = stringResource(R.string.test_user),
                    avatarUrl = "https://avatars.githubusercontent.com/u/76755413?v=4"
                )
            ),
            PullrequestModel(
                title =stringResource(R.string.pull_request_1),
                body = stringResource(R.string.pull_request_description),
                createdAt =stringResource(R.string.fake_date),
                htmlUrl = "",
                user = PullrequestUserModel(
                    login = stringResource(R.string.test_user),
                    avatarUrl = "https://avatars.githubusercontent.com/u/76755413?v=4"
                )
            ),
            PullrequestModel(
                title =stringResource(R.string.pull_request_1),
                body = stringResource(R.string.pull_request_description),
                createdAt =stringResource(R.string.fake_date),
                htmlUrl = "",
                user = PullrequestUserModel(
                    login = stringResource(R.string.test_user),
                    avatarUrl = "https://avatars.githubusercontent.com/u/76755413?v=4"
                )
            )
        )
    )
}

@Composable
fun SetupError(tryAgain: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.TopCenter),
            onClick = { tryAgain.invoke() }
        ) {
            Icon(
                modifier = Modifier.size(48.dp),
                imageVector = Icons.Filled.Refresh,
                contentDescription = stringResource(R.string.menu)
            )
        }
        Text(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = stringResource(R.string.failed_to_load_list)
        )
    }
}

@Composable
fun SetupLoading() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
        Text(
            modifier = Modifier.align(Alignment.BottomCenter),
            text =  stringResource(R.string.loading_pull_request_list)
        )
    }
}