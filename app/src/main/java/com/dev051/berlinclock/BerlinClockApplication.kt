package com.dev051.berlinclock

import android.app.Application
import com.dev051.berlinclock.inject.berlinClockModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BerlinClockApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BerlinClockApplication)
            modules(berlinClockModule)
        }
    }
}
