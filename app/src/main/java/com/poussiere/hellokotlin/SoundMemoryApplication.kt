package com.poussiere.hellokotlin

import android.app.Application
import org.koin.android.ext.android.startKoin

class SoundMemoryApplication : Application() {
    override fun onCreate(){
        super.onCreate()
        // start Koin!
        startKoin(this, listOf(soundMemoryModules))

    }
}