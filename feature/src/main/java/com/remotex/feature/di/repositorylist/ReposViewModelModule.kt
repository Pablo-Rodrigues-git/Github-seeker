package com.remotex.feature.di.repositorylist

import com.remotex.domain.repositorylist.interfaces.ReposRepository
import com.remotex.feature.presentation.screens.repositorylist.RepositoryListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ReposViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideViewModel(repository: ReposRepository): RepositoryListViewModel {
        return RepositoryListViewModel(repository)
    }
}