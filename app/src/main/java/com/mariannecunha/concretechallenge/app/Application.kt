package com.mariannecunha.concretechallenge.app

import android.app.Application
import com.mariannecunha.concretechallenge.BuildConfig
import com.mariannecunha.concretechallenge.data.di.dataModule
import com.mariannecunha.concretechallenge.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.android.logger.AndroidLogger
import org.koin.core.logger.EmptyLogger
import org.koin.core.logger.Logger


class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        setUpKoin()
    }

    private fun setUpKoin() {
        startKoin {
            androidContext(this@Application)
            logger(setupDependencyInjectionLogger())
            modules(
                listOf(
                    presentationModule,
                    dataModule
                )
            )
        }
    }

    private fun setupDependencyInjectionLogger(): Logger =
        if (BuildConfig.DEBUG) AndroidLogger() else EmptyLogger()
}
