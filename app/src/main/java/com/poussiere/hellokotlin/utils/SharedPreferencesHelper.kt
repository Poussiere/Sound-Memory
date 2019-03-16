package com.poussiere.hellokotlin.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class SharedPreferencesHelper(private val applicationContext: Context) {

    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)

    fun getPlayerCount(): Int{
        return prefs.getInt(Constants.SHAREDPREFERENCES_PLAYERS_KEY, 1)
    }

    fun setPlayerCount(playerCount: Int){
        prefs.edit().putInt(Constants.SHAREDPREFERENCES_PLAYERS_KEY, playerCount).apply()
    }

    fun setDifficulty(difficulty: Constants.Difficulty){
        prefs.edit().putInt(Constants.SHAREDPREFERENCES_DIFFICULTY_KEY, difficulty.ordinal).apply()
    }

    fun getDifficulty(): Constants.Difficulty{
        val ordinal = prefs.getInt(Constants.SHAREDPREFERENCES_DIFFICULTY_KEY, Constants.Difficulty.MEDIUM.ordinal)
        return Constants.Difficulty.values()[ordinal]
    }
}