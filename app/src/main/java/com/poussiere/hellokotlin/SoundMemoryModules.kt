package com.poussiere.hellokotlin

import com.poussiere.hellokotlin.datasource.CardGame
import com.poussiere.hellokotlin.repository.CardsRepository
import com.poussiere.hellokotlin.utils.SharedPreferencesHelper
import com.poussiere.hellokotlin.utils.SoundHelper
import com.poussiere.hellokotlin.views.GameBoardViewModel
import com.poussiere.hellokotlin.views.HomeViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Modules for dependency injection
 */
val soundMemoryModules = module {
    single { SoundHelper(get()) }
    single { CardGame() }
    single { CardsRepository(get()) }
    single { SharedPreferencesHelper(get())}
    viewModel { HomeViewModel(get(), get()) }
    viewModel { GameBoardViewModel(get(), get(), get()) }
}