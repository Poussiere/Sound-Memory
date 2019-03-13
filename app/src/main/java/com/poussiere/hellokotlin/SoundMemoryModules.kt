package com.poussiere.hellokotlin

import com.poussiere.hellokotlin.datasource.CardGame
import com.poussiere.hellokotlin.repository.CardsRepository
import com.poussiere.hellokotlin.utils.SoundHelper
import org.koin.dsl.module.module

/**
 * Modules for dependency injection
 */
val soundMemoryModules = module {
    single { SoundHelper(get()) }
    single { CardGame() }
    single { CardsRepository(get()) }
}