package com.poussiere.hellokotlin.utils

/**
 * * Key for shared prefs
 */
val SHAREDPREFERENCES_PLAYERS_KEY = "player_number"
val SHAREDPREFERENCES_DIFFICULTY_KEY = "difficulty"
val SHAREDPREFERENCES_LAUNCH_COUNT_KEY = "launch_count"
val SHAREDPREFERENCES_HAS_RATES_KEY = "has_rated"

enum class Difficulty {
    EASY, MEDIUM, HARD, IMPOSSIBLE
}
