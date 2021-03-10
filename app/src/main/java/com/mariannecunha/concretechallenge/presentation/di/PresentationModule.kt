package com.mariannecunha.concretechallenge.presentation.di

import com.mariannecunha.concretechallenge.data.PullRequestRepositoryImpl
import com.mariannecunha.concretechallenge.data.RepositoryRepositoryImpl
import com.mariannecunha.concretechallenge.domain.usecase.FetchPullRequests
import com.mariannecunha.concretechallenge.domain.usecase.FetchRepositories
import com.mariannecunha.concretechallenge.presentation.PullRequestListAdapter
import com.mariannecunha.concretechallenge.presentation.PullRequestListViewModel
import com.mariannecunha.concretechallenge.presentation.RepositoryListAdapter
import com.mariannecunha.concretechallenge.presentation.RepositoryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        RepositoryListViewModel(
            get<FetchRepositories>()
        )
    }

    viewModel {
        PullRequestListViewModel(
            get<FetchPullRequests>()
        )
    }

    factory {
        PullRequestListAdapter()
    }

    factory {
        RepositoryListAdapter()
    }


}