package com.remotex.feature.presentation.screens.repositorylist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.remotex.domain.repositorylist.models.RepositoryModel
import com.remotex.domain.repositorylist.models.RepositoryOwnerModel
import com.remotex.feature.R

@Composable
fun ReposScreen(
    viewModel: RepositoryListViewModel,
    navController: NavHostController
) {

    val state by viewModel.repositoryListViewState.collectAsState()
    val currentItems = remember { mutableListOf<RepositoryModel>() }

    LaunchedEffect(key1 = Unit) {
        viewModel.getRepos()
    }

    Scaffold(
        topBar = { Header() }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            when (state) {
                is RepositoryListViewState.Success -> {
                    currentItems.addAll((state as RepositoryListViewState.Success).repos.items)
                    SetupSuccess(
                        items = currentItems,
                        loadMore = viewModel::loadMore,
                        navController = navController
                    )
                }

                is RepositoryListViewState.LoadMore -> {
                    currentItems.addAll((state as RepositoryListViewState.LoadMore).repos.items)
                    SetupSuccess(
                        items = currentItems,
                        loadMore = viewModel::loadMore,
                        navController = navController
                    )
                }

                is RepositoryListViewState.Empty -> SetupEmpty()
                is RepositoryListViewState.Error -> SetupError(viewModel::getRepos)
                is RepositoryListViewState.Loading -> SetupLoading()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Header() {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.repository_list))
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu"
                )
            }
        }
    )
}

@Composable
fun SetupSuccess(
    loadMore: () -> Unit,
    items: List<RepositoryModel>,
    navController: NavHostController
) {
    val listState = rememberLazyListState()

    val reachedBottom: Boolean by remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index != 0 &&
                    lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - 1
        }
    }

    LaunchedEffect(reachedBottom) {
        if (reachedBottom) loadMore()
    }

    LazyColumn(
        state = listState
    ) {
        items(items) { item ->
            ReposListItem(
                repositoryModel = item,
                navController = navController
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
            text = stringResource(R.string.no_repository_found)
        )
    }
}

private const val STARGAZERS_COUNT = 100
private const val FORKS_COUNT = 50

@Preview(showBackground = true)
@Composable
fun SetupSuccessPreview() {
    val items = listOf(
        RepositoryModel(
            name = stringResource(R.string.github_api_android),
            description = LoremIpsum(15).values.joinToString(" "),
            stargazersCount = STARGAZERS_COUNT,
            forksCount = FORKS_COUNT,
            owner = RepositoryOwnerModel(
                login = stringResource(R.string.test_user),
                avatarUrl = "https://avatars.githubusercontent.com/u/26586900?v=4"
            )
        ),
        RepositoryModel(
            name = stringResource(R.string.github_api_android),
            description = LoremIpsum(15).values.joinToString(" "),
            stargazersCount = STARGAZERS_COUNT,
            forksCount = FORKS_COUNT,
            owner = RepositoryOwnerModel(
                login = stringResource(R.string.test_user),
                avatarUrl = "https://avatars.githubusercontent.com/u/26586900?v=4"
            )
        ),
        RepositoryModel(
            name = stringResource(R.string.github_api_android),
            description = LoremIpsum(15).values.joinToString(" "),
            stargazersCount = STARGAZERS_COUNT,
            forksCount = FORKS_COUNT,
            owner = RepositoryOwnerModel(
                login = stringResource(R.string.test_user),
                avatarUrl = "https://avatars.githubusercontent.com/u/26586900?v=4"
            )
        )
    )

    SetupSuccess(
        loadMore = { },
        items = items,
        navController = rememberNavController()
    )
}

@Composable
fun SetupError(tryAgain: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.TopCenter),
            onClick = { tryAgain.invoke() }
        ) {
            Icon(
                modifier = Modifier
                    .size(48.dp),
                imageVector = Icons.Filled.Refresh,
                contentDescription = stringResource(R.string.menu),

                )
        }
        Text(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = stringResource(R.string.failed_to_load_the_list_click_to_try_again)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SetupErrorPreview() {
    SetupError(tryAgain = {})
}

@Preview(showBackground = true)
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
            text = stringResource(R.string.loading_repository_list)
        )
    }
}