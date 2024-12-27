package com.remotex.github_seeker.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.remotex.feature.presentation.screens.pullrequestlist.PullrequestListScreen
import com.remotex.feature.presentation.screens.pullrequestlist.PullrequestListViewModel
import com.remotex.feature.presentation.screens.repositorylist.ReposScreen
import com.remotex.feature.presentation.screens.repositorylist.RepositoryListViewModel

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {

    NavHost(navController = navController, startDestination = "repos") {
        composable("repos") {
            val viewModel = hiltViewModel<RepositoryListViewModel>()
            ReposScreen(viewModel = viewModel, navController)
        }
        composable("pulls/{owner}/{repo}") { backStackEntry ->
            val viewModel = hiltViewModel<PullrequestListViewModel>()
            PullrequestListScreen(
                navController = navController,
                viewModel = viewModel,
                owner = backStackEntry.arguments?.getString("owner") ?: "",
                repo = backStackEntry.arguments?.getString("repo") ?: ""
            )
        }
    }
}