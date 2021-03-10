package com.mariannecunha.concretechallenge.data.pullrequest

import com.mariannecunha.concretechallenge.domain.model.PullRequest
import retrofit2.http.GET
import retrofit2.http.Path

interface PullRequestService {
    @GET("repos/{username}/{repository}/pulls")
    suspend fun fetchPullRequests(
        @Path("username") username: String,
        @Path("repository") repository: String
    ): List<PullRequest>
}