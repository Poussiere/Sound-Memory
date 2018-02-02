package com.poussiere.hellokotlin

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import com.poussiere.hellokotlin.MainActivity.Companion.SHAREDPREFERENCES_PLAYERS_KEY
import com.poussiere.hellokotlin.data.Card
import com.poussiere.hellokotlin.utils.CardUtils
import com.poussiere.hellokotlin.utils.Song
import kotlinx.android.synthetic.main.activity_game.*


/**
 * Created by poussiere on 23/01/18.
 */
class GameActivity : AppCompatActivity(), MyRecyclerViewAdapter.AdapterOnClickHandler {


    // retrieve a mutableList of all Cards objects
    var cardTab : MutableList<Card> = CardUtils.initCards()
    var songIsPlaying : Boolean = false
    var firstCard : Boolean = true
    var previousIndex = 1
    var actualIndex = 1
    var myRecyclerViewAdapter : MyRecyclerViewAdapter?=null
    var player : Song = Song(this)
    var playerNumber =1
    //is it player 1 turn?
    var p1Turn=true
    var p1score=0
    var p2score=0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_game)

        //we shuffle the cards
        cardTab.shuffle();

        //How many players are there?
        var prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        playerNumber = prefs.getInt(SHAREDPREFERENCES_PLAYERS_KEY, 1)
        //S'il y a deux joueurs, on va afficher le joueur dont c'est le tour en bas
        if (playerNumber==2)player_tv.setText(R.string.player_1)


        //Configure recycler view in GrilLayout
        game_board.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(this@GameActivity, 4) // 3 = number of items on each row
        game_board.setLayoutManager(gridLayoutManager)
        myRecyclerViewAdapter= MyRecyclerViewAdapter(cardTab, this, screenWidth())
        game_board.setAdapter(myRecyclerViewAdapter)


    }



    override fun doSomethingFromActivityWhenClick(index: Int) {
        // Le clique ne va réagir que si la carte cliquée n'a pas encore été découverte et que si la lecture d'un son n'est pas en cours

        if (!cardTab[index].discovered && !cardTab[index].discovered2 && !songIsPlaying){

            when (firstCard){
                true -> doWhenFirstClick(index)
                false -> {
                    if (playerNumber==1) {doWhenSecondClick(index)}else{
                        doWhenSecondClickTwoPlayer(index)
                    }

                }
            }
        }
    }


    fun doWhenFirstClick(index : Int) {
        songIsPlaying = true
        cardTab[index].checked = true;

        myRecyclerViewAdapter?.updateCardsList(cardTab)
        myRecyclerViewAdapter?.notifyItemChanged(index)

        previousIndex = index

        player.prepareSoundFile(cardTab[index].song)
        player.mediaPlayer.prepareAsync()
        player.mediaPlayer.setOnPreparedListener(){
            player.mediaPlayer.start()

         

        player.mediaPlayer.setOnCompletionListener() {
                player.resetPlayer()
                firstCard = false
                songIsPlaying = false
            }
        }
    }


    fun doWhenSecondClick(index : Int) {

        actualIndex = index
        // si on clique à nouveau sur la même case, il ne se pas rien

        if (actualIndex == previousIndex) return

        songIsPlaying = true
        cardTab[index].checked = true;
        myRecyclerViewAdapter?.updateCardsList(cardTab)
        myRecyclerViewAdapter?.notifyItemChanged(index)

        player.prepareSoundFile(cardTab[actualIndex].song)
        player.mediaPlayer.prepareAsync()
        player.mediaPlayer.setOnPreparedListener(){
            player.mediaPlayer.start()

            player.mediaPlayer.setOnCompletionListener() {
                player.resetPlayer()

                if (cardTab[previousIndex].id == cardTab[actualIndex].id) {
                    cardTab[previousIndex].discovered = true
                    cardTab[actualIndex].discovered = true
                } else {

                    cardTab[previousIndex].checked = false
                    cardTab[actualIndex].checked = false
                }


                firstCard = true


                //On actualise la vue
                myRecyclerViewAdapter?.updateCardsList(cardTab)
                myRecyclerViewAdapter?.notifyDataSetChanged()
                songIsPlaying = false
            }}

    }


    fun doWhenSecondClickTwoPlayer(index : Int) {

        actualIndex = index
        // si on clique à nouveau sur la même case, il ne se pas rien

        if (actualIndex == previousIndex) return


        songIsPlaying = true
        cardTab[index].checked = true;

        myRecyclerViewAdapter?.updateCardsList(cardTab)
        myRecyclerViewAdapter?.notifyItemChanged(index)

        player.prepareSoundFile(cardTab[actualIndex].song)
        player.mediaPlayer.prepareAsync()
        player.mediaPlayer.setOnPreparedListener(){
            player.mediaPlayer.start()

         
        player.mediaPlayer.setOnCompletionListener() {

            player.resetPlayer()

                //Si les deux cartes sont identiques
                if (cardTab[previousIndex].id == cardTab[actualIndex].id) {


                    //On va changer la couleur des cartes si elles sont découvertes
                    if (p1Turn){
                    cardTab[previousIndex].discovered = true
                    cardTab[actualIndex].discovered = true
                    p1score+=1}else{
                        cardTab[previousIndex].discovered2 = true
                        cardTab[actualIndex].discovered2 = true
                        p2score+=1
                    }

                    //Si le score cumulé des 2 joueurs est égal à 10, je jeu s'arrête. Il faut donc afficher le gagnant
                    if (p1score+p2score ==10 && p1score>p2score) {player_tv.setText(R.string.player_1_wins)}
                    else if (p1score+p2score ==10 && p2score>p1score) {player_tv.setText(R.string.player_2_wins)}
                    else{player_tv.setText(R.string.egality)}


                  //Si les deux cartes ne sont pas identiques
                } else {

                    cardTab[previousIndex].checked = false
                    cardTab[actualIndex].checked = false
                    //Les deux cartes ne sont pas identiques, on change donc de joueur
                    firstCard = true
                    if (p1Turn){
                        p1Turn=false
                        player_tv.setText(R.string.player_2)
                    }else{
                        p1Turn=true
                        player_tv.setText(R.string.player_1)
                    }
                }

                //On actualise la vue
                myRecyclerViewAdapter?.updateCardsList(cardTab)
                myRecyclerViewAdapter?.notifyDataSetChanged()
                songIsPlaying = false
            }}

    }
// On va récupérer la hauteur de l'écran en dp et la passer au constructeur du recyclerview adapter en parametre
 fun screenHeight (): Int {
       
        var displayMetrics =getResources().getDisplayMetrics();
     var height = displayMetrics.heightPixels
        return height
  
    }
     fun screenWidth (): Int {
       
        var displayMetrics =getResources().getDisplayMetrics();
     var width = displayMetrics.widthPixels
        return width
  
    }

    override fun onPause() {
        player.resetPlayer()
        super.onPause()
    }
    override fun onDestroy() {
        player.releasePlayer()
        super.onDestroy()
    }

    override fun onResume(){
        super.onResume()

    }

}
