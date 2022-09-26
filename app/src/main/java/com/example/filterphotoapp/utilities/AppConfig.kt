package com.example.filterphotoapp.utilities

import android.app.Application
import com.example.filterphotoapp.di.repositoryModule
import com.example.filterphotoapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unsed")
class AppConfig : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppConfig)
        }
    }
}