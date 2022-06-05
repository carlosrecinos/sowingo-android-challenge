package com.recinos.sowingo_android_challenge.main

import android.app.Application
import android.content.Context
import com.recinos.sowingo_android_challenge.main.di.ApplicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppDelegate: Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AppDelegate)
            androidFileProperties()
            koin.loadModules(modules = listOf(ApplicationModule(this@AppDelegate).appModule))
        }
    }
}