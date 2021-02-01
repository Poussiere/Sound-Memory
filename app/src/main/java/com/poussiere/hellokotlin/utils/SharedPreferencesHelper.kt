package com.poussiere.hellokotlin.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class SharedPreferencesHelper(applicationContext: Context) {

    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)

    fun incrementLaunchCount() {
        prefs.edit().putInt(SHAREDPREFERENCES_LAUNCH_COUNT_KEY, launchCount() + 1).apply()
    }

    fun launchCount() = prefs.getInt(SHAREDPREFERENCES_LAUNCH_COUNT_KEY, 0)

    fun hasRated() = prefs.getBoolean(SHAREDPREFERENCES_HAS_RATES_KEY, false)

    fun rate() = prefs.edit().putBoolean(SHAREDPREFERENCES_HAS_RATES_KEY, true).apply()

    fun getPlayerCount() = prefs.getInt(SHAREDPREFERENCES_PLAYERS_KEY, 1)

    fun setPlayerCount(playerCount: Int) {
        prefs.edit().putInt(SHAREDPREFERENCES_PLAYERS_KEY, playerCount).apply()
    }

    fun setDifficulty(difficulty: Difficulty) {
        prefs.edit().putInt(SHAREDPREFERENCES_DIFFICULTY_KEY, difficulty.ordinal).apply()
    }

    fun getDifficulty(): Difficulty {
        val ordinal = prefs.getInt(SHAREDPREFERENCES_DIFFICULTY_KEY, Difficulty.MEDIUM.ordinal)
        return Difficulty.values()[ordinal]
    }
}