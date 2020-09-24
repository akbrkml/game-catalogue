package com.badrun.gamecatalogue

import android.app.Application
import com.badrun.core.di.databaseModule
import com.badrun.core.di.networkModule
import com.badrun.core.di.repositoryModule
import com.badrun.gamecatalogue.di.useCaseModule
import com.badrun.gamecatalogue.di.viewModelModule
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    @InternalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
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