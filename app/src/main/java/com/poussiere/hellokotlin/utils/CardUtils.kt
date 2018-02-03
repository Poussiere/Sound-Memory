package com.poussiere.hellokotlin.utils

import com.poussiere.hellokotlin.R
import com.poussiere.hellokotlin.data.Card

/**
 * Created by poussiere on 24/01/18.
 */
 class CardUtils{

  //Create a methode that returns a mutableList of the 20 objects + in the data class create a bolean variable to know if the card is discovered or not
  
  
  
//Methode that determine each id card and song to play
    //There are 2*10 cards.

    companion object {
        fun initCards (): MutableList<Card>{

            //instanciate an empty array
            var cardlist : MutableList<Card> = mutableListOf<Card>()

            with(cardlist){
                add(Card(1, R.raw.bang))
                add(Card(1, R.raw.bang))
                add(Card(2, R.raw.bateau))
                add(Card(2, R.raw.bateau))
                add(Card(3, R.raw.beatbox))
                add(Card(3, R.raw.beatbox))
                add(Card(4, R.raw.caisse))
                add(Card(4, R.raw.caisse))
                add(Card(5, R.raw.chant))
                add(Card(5, R.raw.chant))
                add(Card(6, R.raw.cheval))
                add(Card(6, R.raw.cheval))
                add(Card(7, R.raw.chien))
                add(Card(7, R.raw.chien))
                add(Card(8, R.raw.chouette))
                add(Card(8, R.raw.chouette))
                add(Card(9, R.raw.cloche))
                add(Card(9, R.raw.cloche))
                add(Card(10, R.raw.cocorico))
                add(Card(10, R.raw.cocorico))
                add(Card(11, R.raw.computer))
                add(Card(11, R.raw.computer))
                add(Card(12, R.raw.corde))
                add(Card(12, R.raw.corde))
                add(Card(13, R.raw.course))
                add(Card(13, R.raw.course))
                add(Card(14, R.raw.dauphin))
                add(Card(14, R.raw.dauphin))
                add(Card(15, R.raw.des))
                add(Card(15, R.raw.des))
                add(Card(16, R.raw.epee))
                add(Card(16, R.raw.epee))
                add(Card(17, R.raw.epee2))
                add(Card(17, R.raw.epee2))
                add(Card(18, R.raw.fireworks))
                add(Card(18, R.raw.fireworks))
                add(Card(19, R.raw.flute))
                add(Card(19, R.raw.flute))
                add(Card(20, R.raw.gloogloo))
                add(Card(20, R.raw.gloogloo))
                add(Card(21, R.raw.gong))
                add(Card(21, R.raw.gong))
                add(Card(22, R.raw.guitare))
                add(Card(22, R.raw.guitare))
                add(Card(23, R.raw.guitareelec))
                add(Card(23, R.raw.guitareelec))
                add(Card(24, R.raw.lazer))
                add(Card(24, R.raw.lazer))
                add(Card(25, R.raw.lutin))
                add(Card(25, R.raw.lutin))
                add(Card(26, R.raw.mer))
                add(Card(26, R.raw.mer))
                add(Card(27, R.raw.miaulement))
                add(Card(27, R.raw.miaulement))
                add(Card(28, R.raw.montre))
                add(Card(28, R.raw.montre))
                add(Card(29, R.raw.musicbox))
                add(Card(29, R.raw.musicbox))
                add(Card(30, R.raw.noel))
                add(Card(30, R.raw.noel))
                add(Card(31, R.raw.ouhia))
                add(Card(31, R.raw.ouhia))
                add(Card(32, R.raw.phoque))
                add(Card(32, R.raw.phoque))
                add(Card(33, R.raw.piano))
                add(Card(33, R.raw.piano))
                add(Card(34, R.raw.pistolet))
                add(Card(34, R.raw.pistolet))
                add(Card(35, R.raw.radio))
                add(Card(35, R.raw.radio))
                add(Card(36, R.raw.recree))
                add(Card(36, R.raw.recree))
                add(Card(37, R.raw.ressort))
                add(Card(37, R.raw.ressort))
                add(Card(38, R.raw.riresinistre))
                add(Card(38, R.raw.riresinistre))
                add(Card(39, R.raw.roue))
                add(Card(39, R.raw.roue))
                add(Card(40, R.raw.ruisseau))
                add(Card(40, R.raw.ruisseau))
                add(Card(41, R.raw.sabrelazer))
                add(Card(41, R.raw.sabrelazer))
                add(Card(42, R.raw.sansplomb))
                add(Card(42, R.raw.sansplomb))
                add(Card(43, R.raw.scie))
                add(Card(43, R.raw.scie))
                add(Card(44, R.raw.siffle))
                add(Card(44, R.raw.siffle))
                add(Card(45, R.raw.sncf))
                add(Card(45, R.raw.sncf))
                add(Card(46, R.raw.sonic))
                add(Card(46, R.raw.sonic))
                add(Card(47, R.raw.toctoc))
                add(Card(47, R.raw.toctoc))
                add(Card(48, R.raw.vaisseau))
                add(Card(48, R.raw.vaisseau))
                add(Card(49, R.raw.violon))
                add(Card(49, R.raw.violon))
                add(Card(50, R.raw.whee))
                add(Card(50, R.raw.whee))
                add(Card(51, R.raw.wilem))
                add(Card(51, R.raw.wilem))


            }


            return cardlist
        }
    }


}


