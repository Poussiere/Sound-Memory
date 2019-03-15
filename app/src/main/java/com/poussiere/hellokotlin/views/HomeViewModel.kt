package com.poussiere.hellokotlin.views

import android.arch.lifecycle.ViewModel
import android.content.Context
import com.poussiere.hellokotlin.R
import com.poussiere.hellokotlin.utils.SharedPreferencesHelper
import com.poussiere.hellokotlin.utils.ViewModelField
import io.reactivex.subjects.PublishSubject

class HomeViewModel(val applicationContext: Context, val prefs: SharedPreferencesHelper): ViewModel() {

    val onMainViewClick = PublishSubject.create<Boolean>()
    val playerCountTextView = ViewModelField("")

    init{
        when (prefs.getPlayerCount()) {
            1 -> playerCountTextView.value = applicationContext.getString(R.string.un_joueur)
            2 -> playerCountTextView.value = applicationContext.getString(R.string.deux_joueurs)
        }
    }

    fun onPlayerCountClick(){
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
}