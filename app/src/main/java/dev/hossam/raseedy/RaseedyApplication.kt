package dev.hossam.raseedy

import android.app.Application
import dev.hossam.raseedy.core.SharedPref

class RaseedyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPref.init(this)
    }
}