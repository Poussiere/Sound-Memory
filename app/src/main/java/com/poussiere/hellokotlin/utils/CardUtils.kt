package com.poussiere.hellokotlin.utils

import com.poussiere.hellokotlin.R
import com.poussiere.hellokotlin.data.Card

/**
 * Created by poussiere on 24/01/18.
 */
 class CardUtils{

//Methode that determine each id card and song to play
    //There are 2*10 cards.
    fun createCard (id : String): Card? {

        when (id) {
            "a" -> return Card(1, R.raw.bateau)
            "b" -> return Card(1, R.raw.bateau)
            "c" -> return Card(2, R.raw.bateau)
            "c" -> return Card(2, R.raw.bateau)
            "d" -> return Card(3, R.raw.bateau)
            "d" -> return Card(3, R.raw.bateau)
            "e" -> return Card(4, R.raw.bateau)
            "e" -> return Card(4, R.raw.bateau)
            "f" -> return Card(5, R.raw.bateau)
            "g" -> return Card(5, R.raw.bateau)
            "h" -> return Card(6, R.raw.bateau)
            "i" -> return Card(6, R.raw.bateau)
            "j" -> return Card(7, R.raw.bateau)
            "k" -> return Card(7, R.raw.bateau)
            "l" -> return Card(8, R.raw.bateau)
            "m" -> return Card(8, R.raw.bateau)
            "n" -> return Card(9, R.raw.bateau)
            "o" -> return Card(9, R.raw.bateau)
            "p" -> return Card(10, R.raw.bateau)
            "q" -> return Card(10, R.raw.bateau)

        }

    return null
        }



    }


