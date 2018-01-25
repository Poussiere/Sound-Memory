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
                add(Card(1, R.raw.bateau))
                add(Card(1, R.raw.bateau))
                add(Card(2, R.raw.bateau))
                add(Card(2, R.raw.bateau))
                add(Card(3, R.raw.bateau))
                add(Card(3, R.raw.bateau))
                add(Card(4, R.raw.bateau, true))
                add(Card(4, R.raw.bateau))
                add(Card(5, R.raw.bateau))
                add(Card(5, R.raw.bateau))
                add(Card(6, R.raw.bateau))
                add(Card(6, R.raw.bateau))
                add(Card(7, R.raw.bateau))
                add(Card(7, R.raw.bateau))
                add(Card(8, R.raw.bateau))
                add(Card(8, R.raw.bateau))
                add(Card(9, R.raw.bateau))
                add(Card(9, R.raw.bateau))
                add(Card(10, R.raw.bateau))
                add(Card(10, R.raw.bateau))

            }


            return cardlist
        }
    }


}


