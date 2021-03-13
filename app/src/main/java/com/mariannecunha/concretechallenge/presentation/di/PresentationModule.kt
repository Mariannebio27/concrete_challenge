package com.mariannecunha.concretechallenge.presentation.di

import com.mariannecunha.concretechallenge.domain.usecase.FetchPullRequests
import com.mariannecunha.concretechallenge.domain.usecase.FetchRepositories
import com.mariannecunha.concretechallenge.presentation.pullrequest.PullRequestListAdapter
import com.mariannecunha.concretechallenge.presentation.pullrequest.PullRequestListViewModel
import com.mariannecunha.concretechallenge.presentation.repositorylist.RepositoryListAdapter
import com.mariannecunha.concretechallenge.presentation.repositorylist.RepositoryListViewModel
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
