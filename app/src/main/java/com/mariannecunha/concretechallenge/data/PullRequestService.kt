package com.mariannecunha.concretechallenge.data

import com.mariannecunha.concretechallenge.model.GlobalRepository
import com.mariannecunha.concretechallenge.model.PullRequest
import retrofit2.http.GET
import retrofit2.http.Path

interface PullRequestService {
    @GET("repos/{username}/{repository}/pulls")
    suspend fun fetchPullRequests(
        @Path("username") username: String,
        @Path("repository") repository: String
    ): List<PullRequest>
}