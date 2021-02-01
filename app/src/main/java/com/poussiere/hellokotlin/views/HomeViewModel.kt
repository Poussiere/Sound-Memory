package com.poussiere.hellokotlin.views

import androidx.lifecycle.ViewModel
import android.content.Context
import com.poussiere.hellokotlin.R
import com.poussiere.hellokotlin.utils.Difficulty
import com.poussiere.hellokotlin.utils.SharedPreferencesHelper
import com.poussiere.hellokotlin.utils.ViewModelField
import io.reactivex.subjects.PublishSubject

class HomeViewModel(val applicationContext: Context, val prefs: SharedPreferencesHelper) : ViewModel() {

    val onMainViewClick = PublishSubject.create<Boolean>()
    val playerCountTextView = ViewModelField("")
    val difficultyTextView = ViewModelField("")

    init {
        when (prefs.getPlayerCount()) {
            1 -> playerCountTextView.value = applicationContext.getString(R.string.un_joueur)
            2 -> playerCountTextView.value = applicationContext.getString(R.string.deux_joueurs)
        }

        when (prefs.getDifficulty()) {
            Difficulty.EASY -> difficultyTextView.value = applicationContext.getString(R.string.easy)
            Difficulty.MEDIUM -> difficultyTextView.value = applicationContext.getString(R.string.normal)
            Difficulty.HARD -> difficultyTextView.value = applicationContext.getString(R.string.hard)
            Difficulty.IMPOSSIBLE -> difficultyTextView.value = applicationContext.getString(R.string.impossible)
        }
    }

    fun onPlayerCountClick() {
        when (prefs.getPlayerCount()) {
            1 -> {
                playerCountTextView.value = applicationContext.getString(R.string.deux_joueurs)
                prefs.setPlayerCount(2)
            }
            2 -> {
                playerCountTextView.value = applicationContext.getString(R.string.un_joueur)
                prefs.setPlayerCount(1)
            }
        }
    }

    fun onDifficultyClick() {
        when (prefs.getDifficulty()) {
            Difficulty.EASY -> {
                difficultyTextView.value = applicationContext.getString(R.string.normal)
                prefs.setDifficulty(Difficulty.MEDIUM)
            }
            Difficulty.MEDIUM -> {
                difficultyTextView.value = applicationContext.getString(R.string.hard)
                prefs.setDifficulty(Difficulty.HARD)
            }
            Difficulty.HARD -> {
                difficultyTextView.value = applicationContext.getString(R.string.impossible)
                prefs.setDifficulty(Difficulty.IMPOSSIBLE)

            }
            Difficulty.IMPOSSIBLE -> {
                difficultyTextView.value = applicationContext.getString(R.string.easy)
                prefs.setDifficulty(Difficulty.EASY)
            }
        }
    }
}