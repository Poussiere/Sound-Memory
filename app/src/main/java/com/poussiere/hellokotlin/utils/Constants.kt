package com.poussiere.hellokotlin.utils

object Constants {

    /**
     * Key for shared prefs
     */
    val SHAREDPREFERENCES_PLAYERS_KEY = "player_number"
    val SHAREDPREFERENCES_DIFFICULTY_KEY = "difficulty"

    enum class Difficulty{
        EASY, MEDIUM, HARD, IMPOSSIBLE
    }
}