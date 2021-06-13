@file:Suppress("unused")

package com.fadelpamungkas.moviedatabase

import android.app.Application
import com.fadelpamungkas.core.di.databaseModule
import com.fadelpamungkas.core.di.networkModule
import com.fadelpamungkas.core.di.repositoryModule
import com.fadelpamungkas.moviedatabase.di.useCaseModule
import com.fadelpamungkas.moviedatabase.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MovieApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}