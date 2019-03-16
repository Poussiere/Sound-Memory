package com.poussiere.hellokotlin.repository

import com.poussiere.hellokotlin.datasource.CardGame
import com.poussiere.hellokotlin.model.Card

class CardsRepository(private val cardGame: CardGame) {

    //Score that has to be reached by the two player to finish game
    private var winningScore = 10

    fun getWinningScore(): Int {
        return winningScore
    }

    /**
     * Get small card game (for easy mode)
     */
    fun getSmallCardGame(): MutableList<Card> {
        winningScore = 6
        //Get full card list from cart game data source
        val cardList = cardGame.getCardList()
        //Mix the cards
        cardList.shuffle()
        //Take the first 5 cards of the mixed list
        val smallCardList: MutableList<Card> = cardList.subList(0, 6)
        for (i in 0..5) {
            smallCardList.add(cardList[i])
        }
        smallCardList.shuffle()
        return smallCardList
    }

    /**
     * Get medium card game (for medium mode)
     */
    fun getMediumCardGame(): MutableList<Card> {
        winningScore = 10
        //Get full card list from cart game data source
        val cardList = cardGame.getCardList()
        //Mix the cards
        cardList.shuffle()
        //Take the first 10 cards of the mixed list
        val mediumCardList: MutableList<Card> = cardList.subList(0, 10)
        //duplicate the first 10 cards
        for (i in 0..9) {
            mediumCardList.add(cardList[i])
        }
        mediumCardList.shuffle()
        return mediumCardList
    }

    /**
     * Get big card game (for hard mode)
     */
    fun getBigCardGame(): MutableList<Card> {
        winningScore = 15
        //Get full card list from cart game data source
        val cardList = cardGame.getCardList()
        //Mix the cards
        cardList.shuffle()
        //Take the first 15 cards of the mixed list
        val bigCardList: MutableList<Card> = cardList.subList(0, 15)
        for (i in 0..14) {
            bigCardList.add(cardList[i])
        }
        bigCardList.shuffle()
        return bigCardList
    }

    /**
     * Get big card game (for hard mode)
     */
    fun getImpossibleCardGame(): MutableList<Card> {
        winningScore = 21
        //Get full card list from cart game data source
        val cardList = cardGame.getCardList()
        //Mix the cards
        cardList.shuffle()
        //Take the first 15 cards of the mixed list
        val impossibleCardList: MutableList<Card> = cardList.subList(0, 21)
        for (i in 0..20) {
            impossibleCardList.add(cardList[i])
        }
        impossibleCardList.shuffle()
        return impossibleCardList
    }
}