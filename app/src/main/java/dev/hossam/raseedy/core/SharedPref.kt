package dev.hossam.raseedy.core

import android.content.Context
import android.content.SharedPreferences
import dev.hossam.raseedy.RaseedyApplication

object SharedPref {


    private lateinit var appContext: RaseedyApplication
    private const val PREF_NAME = "MyPrefs"
    private const val APP_VISIT = "visit"
    private lateinit var sharedPreferences: SharedPreferences


    fun init(appContext: RaseedyApplication) {
        this.appContext = appContext
        sharedPreferences = appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setFirstVisit(isFirstVisit: Boolean) {
        val editor = sharedPreferences.edit()
        editor?.putBoolean(APP_VISIT, isFirstVisit)?.apply()
    }

    fun getAppVisit(): Boolean = sharedPreferences.getBoolean(APP_VISIT, true)

}