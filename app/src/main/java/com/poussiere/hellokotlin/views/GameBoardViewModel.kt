package com.poussiere.hellokotlin.views

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.poussiere.hellokotlin.R
import com.poussiere.hellokotlin.model.Card
import com.poussiere.hellokotlin.repository.CardsRepository
import com.poussiere.hellokotlin.utils.Constants
import com.poussiere.hellokotlin.utils.SharedPreferencesHelper
import com.poussiere.hellokotlin.utils.SoundHelper
import com.poussiere.hellokotlin.utils.ViewModelField

class GameBoardViewModel(private val prefs: SharedPreferencesHelper,
                         private val cardsRepo: CardsRepository,
                         private val applicationContext: Context) : ViewModel() {

    val playerTv = ViewModelField("")
    val playerTvVisibility = ViewModelField(false)
    val playerTvColor = ViewModelField(R.color.colorAccent)
    val isGameFinished = ViewModelField(false)
    // retrieve a mutableList of all Cards objects
    var cardTab: MutableList<Card> = mutableListOf()
    var songIsPlaying: Boolean = false
    var firstCard: Boolean = true
    var previousIndex = 1
    var actualIndex = 1
    var myRecyclerViewAdapter: MyRecyclerViewAdapter? = null
    var playerNumber = 1
    //is it player 1 turn?
    var p1Turn = true
    var p1score = 0
    var p2score = 0
    val player: SoundHelper = SoundHelper(applicationContext)

    fun getSpanCount(): Int {
        return when (prefs.getDifficulty()) {
            Constants.Difficulty.EASY -> 3
            Constants.Difficulty.MEDIUM -> 4
            Constants.Difficulty.HARD -> 5
            Constants.Difficulty.IMPOSSIBLE -> 6
        }
    }

    fun setGameBoard() {
        isGameFinished.value = false
        cardTab = when (prefs.getDifficulty()) {
            Constants.Difficulty.EASY -> cardsRepo.getSmallCardGame()
            Constants.Difficulty.MEDIUM -> cardsRepo.getMediumCardGame()
            Constants.Difficulty.HARD -> cardsRepo.getBigCardGame()
            Constants.Difficulty.IMPOSSIBLE -> cardsRepo.getImpossibleCardGame()
        }

        playerNumber = prefs.getPlayerCount()
        //S'il y a deux joueurs, on va afficher le joueur dont c'est le tour en bas
        if (playerNumber == 2) {
            playerTvVisibility.value = true
            playerTv.value = applicationContext.getString(R.string.player_1)
            //true for pink
            playerTvColor.value = R.color.colorAccent
        }
    }

    fun onFirstCardClick(index: Int) {
        songIsPlaying = true
        cardTab[index].checked = true

        myRecyclerViewAdapter?.updateCardsList(cardTab)
        myRecyclerViewAdapter?.notifyItemChanged(index)

        previousIndex = index

        player.prepareSoundFile(cardTab[index].song)
        player.mediaPlayer.prepareAsync()
        player.mediaPlayer.setOnPreparedListener {
            player.mediaPlayer.start()

            player.mediaPlayer.setOnCompletionListener {
                player.resetPlayer()
                firstCard = false
                songIsPlaying = false
            }
        }
    }

    fun onSecondCardClickOnePlayer(index: Int) {

        actualIndex = index
        // si on clique à nouveau sur la même case, il ne se pas rien

        if (actualIndex == previousIndex) return

        songIsPlaying = true
        cardTab[index].checked = true
        myRecyclerViewAdapter?.updateCardsList(cardTab)
        myRecyclerViewAdapter?.notifyItemChanged(index)

        player.prepareSoundFile(cardTab[actualIndex].song)
        player.mediaPlayer.prepareAsync()
        player.mediaPlayer.setOnPreparedListener {
            player.mediaPlayer.start()

            player.mediaPlayer.setOnCompletionListener {
                player.resetPlayer()

                if (cardTab[previousIndex].id == cardTab[actualIndex].id) {
                    cardTab[previousIndex].discovered = true
                    cardTab[actualIndex].discovered = true
                    p1score += 1
                } else {

                    cardTab[previousIndex].checked = false
                    cardTab[actualIndex].checked = false
                }

                if(p1score == cardsRepo.getWinningScore()){
                    isGameFinished.value = true
                }
                firstCard = true

                //On actualise la vue
                myRecyclerViewAdapter?.updateCardsList(cardTab)
                myRecyclerViewAdapter?.notifyDataSetChanged()
                songIsPlaying = false
            }
        }
    }

    fun onSecondCardClickTwoPlayer(index: Int) {
        actualIndex = index
        // si on clique à nouveau sur la même case, il ne se pas rien

        if (actualIndex == previousIndex) return


        songIsPlaying = true
        cardTab[index].checked = true

        myRecyclerViewAdapter?.updateCardsList(cardTab)
        myRecyclerViewAdapter?.notifyItemChanged(index)

        player.prepareSoundFile(cardTab[actualIndex].song)
        player.mediaPlayer.prepareAsync()
        player.mediaPlayer.setOnPreparedListener {
            player.mediaPlayer.start()


            player.mediaPlayer.setOnCompletionListener {

                player.resetPlayer()

                //If cards are identical
                if (cardTab[previousIndex].id == cardTab[actualIndex].id) {
                    //We change color of discovered cards
                    if (p1Turn) {
                        cardTab[previousIndex].discovered = true
                        cardTab[actualIndex].discovered = true
                        p1score += 1
                    } else {
                        cardTab[previousIndex].discovered2 = true
                        cardTab[actualIndex].discovered2 = true
                        p2score += 1
                    }

                    firstCard = true
                    //Si le score cumulé des 2 joueurs est égal à winning score, je jeu s'arrête. Il faut donc afficher le gagnant
                    if (p1score + p2score == cardsRepo.getWinningScore()) {
                        when {
                            p1score > p2score -> {
                                playerTv.value = applicationContext.getText(R.string.player_1_wins).toString()
                            }
                            p2score > p1score -> {
                                playerTv.value = applicationContext.getText(R.string.player_2_wins).toString()
                            }
                            p2score == p1score -> {
                                playerTv.value = applicationContext.getText(R.string.egality).toString()
                            }
                        }

                        //Comme le jeu est terminé, on va faire en sorte qu'un clique n'importe où ramène à l'écran d'accueil

                        //Comme le jeu est terminé, on va faire en sorte qu'un clique n'importe où ramène à l'écran d'accueil
                        isGameFinished.value = true
                    }
                    //Si les deux cartes ne sont pas identiques
                } else {
                    cardTab[previousIndex].checked = false
                    cardTab[actualIndex].checked = false
                    //Les deux cartes ne sont pas identiques, on change donc de joueur
                    firstCard = true
                    if (p1Turn) {
                        p1Turn = false
                        playerTv.value = applicationContext.getString(R.string.player_2)
                        //true for pink
                        playerTvColor.value = R.color.colorPlayer2
                    } else {
                        p1Turn = true
                        playerTv.value = applicationContext.getString(R.string.player_1)
                        //true for pink
                        playerTvColor.value = R.color.colorAccent
                    }
                }

                //On actualise la vue
                myRecyclerViewAdapter?.updateCardsList(cardTab)
                myRecyclerViewAdapter?.notifyDataSetChanged()
                songIsPlaying = false
            }
        }
    }
/*
    fun screenWidth(): Int {
        val displayMetrics = applicationContext.resources.displayMetrics
        return displayMetrics.widthPixels
    }
    */
}