package com.mariannecunha.concretechallenge.data

import com.mariannecunha.concretechallenge.model.GlobalRepository
import com.mariannecunha.concretechallenge.model.Repository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryService {
    @GET("search/repositories?q=language:Java&sort=stars")
    suspend fun fetchRepositories(@Query("page") page: Int): GlobalRepository
}