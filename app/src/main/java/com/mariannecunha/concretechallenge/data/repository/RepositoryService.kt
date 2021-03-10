package com.mariannecunha.concretechallenge.data.repository

import com.mariannecunha.concretechallenge.domain.model.GlobalRepository
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryService {
    @GET("search/repositories?q=language:Java&sort=stars")
    suspend fun fetchRepositories(@Query("page") page: Int): GlobalRepository
}