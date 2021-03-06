package com.gmg.user

import android.app.Application
import com.gmg.user.di.appModule
import com.gmg.user.di.repoModule
import com.gmg.user.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GMGApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@GMGApp)
            modules(appModule, repoModule, viewModelModule)
        }
    }

}