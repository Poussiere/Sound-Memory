package com.poussiere.hellokotlin

import com.poussiere.hellokotlin.datasource.CardGame
import com.poussiere.hellokotlin.repository.CardsRepository
import com.poussiere.hellokotlin.utils.SharedPreferencesHelper
import com.poussiere.hellokotlin.views.GameBoardViewModel
import com.poussiere.hellokotlin.views.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


/**
 * Modules for dependency injection
 */
val soundMemoryModules = module {
    factoryOf(::CardGame)
    factoryOf(::CardsRepository)
    singleOf(::SharedPreferencesHelper)
    viewModelOf(::HomeViewModel)
    viewModelOf(::GameBoardViewModel)
}