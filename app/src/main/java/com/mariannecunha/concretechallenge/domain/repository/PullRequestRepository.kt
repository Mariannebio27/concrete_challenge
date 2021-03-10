package com.mariannecunha.concretechallenge.domain.repository

import com.mariannecunha.concretechallenge.domain.model.PullRequest
import com.mariannecunha.concretechallenge.domain.model.Repository

interface PullRequestRepository {

    suspend fun fetchPullRequests(repository: Repository?): List<PullRequest>?

}