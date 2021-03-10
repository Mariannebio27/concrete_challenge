package com.mariannecunha.concretechallenge.data.di

import com.mariannecunha.concretechallenge.data.pullrequest.PullRequestRepositoryImpl
import com.mariannecunha.concretechallenge.data.pullrequest.PullRequestService
import com.mariannecunha.concretechallenge.data.repository.RepositoryRepositoryImpl
import com.mariannecunha.concretechallenge.data.repository.RepositoryService
import com.mariannecunha.concretechallenge.domain.repository.PullRequestRepository
import com.mariannecunha.concretechallenge.domain.repository.RepositoryRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {

    factory {
        PullRequestRepositoryImpl(
            get<PullRequestService>()
        ) as PullRequestRepository
    }

    factory {
        RepositoryRepositoryImpl(
            get<RepositoryService>()
        ) as RepositoryRepository
    }

    factory {
        createRepositoryService(
            get<Retrofit>()
        )
    }

    factory {
        createPullRequestService(
            get<Retrofit>()
        )
    }

    single {
        createRetrofit(
            get<OkHttpClient>()
        )
    }

    factory {
        createOkHttpClient()
    }


}

private fun createPullRequestService(retrofit: Retrofit): PullRequestService {
    return retrofit.create(PullRequestService::class.java)
}

private fun createRepositoryService(retrofit: Retrofit): RepositoryService {
    return retrofit.create(RepositoryService::class.java)
}

private fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {

    return Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun createOkHttpClient(): OkHttpClient {
    val timeoutInSeconds = 10L
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

    return OkHttpClient.Builder()
        .connectTimeout(timeoutInSeconds, TimeUnit.SECONDS)
        .readTimeout(timeoutInSeconds, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}
