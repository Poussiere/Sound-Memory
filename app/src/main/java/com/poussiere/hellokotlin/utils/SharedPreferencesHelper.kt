package com.poussiere.hellokotlin.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class SharedPreferencesHelper(val applicationContext: Context) {
    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)

    fun getPlayerCount(): Int{
        return prefs.getInt(Constants.SHAREDPREFERENCES_PLAYERS_KEY, 1)
    }

    fun setPlayerCount(playerCount: Int){
        prefs.edit().putInt(Constants.SHAREDPREFERENCES_PLAYERS_KEY, playerCount).apply()
    }
}