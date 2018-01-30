package com.poussiere.hellokotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_game)

        //And we shuffle the cards
        cardTab.shuffle();

        //Configure recycler view in GrilLayout
        game_board.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(this@GameActivity, 4) // 3 = number of items on each row
        game_board.setLayoutManager(gridLayoutManager)
        myRecyclerViewAdapter= MyRecyclerViewAdapter(cardTab, this)
        // myRecyclerViewAdapter!!.setHasStableIds(true)
        game_board.setAdapter(myRecyclerViewAdapter)


    }


    override fun doSomethingFromActivityWhenClick(index: Int) {
        // Le clique ne va réagir que si la carte cliquée n'a pas encore été découverte et que si la lecture d'un son n'est pas en cours

       Log.i("GameActivity", "interface click entree")
        if (!cardTab[index].discovered && !songIsPlaying){
            Log.i("GameActivity", "carte non découverte et son n'est pas entrain d'être lu")
            when (firstCard){
                true -> doWhenFirstClick(index)
                false -> doWhenSecondClick(index)
            }
        }
    }


    fun doWhenFirstClick(index : Int) {
        songIsPlaying = true
        Log.i("GameActivity", "dowhenfirstclickdémaré")
       // itemView.song_card.setBackgroundColor(ContextCompat.getColor(context, R.color.colorChecked))
        cardTab[index].checked = true;
        Log.i("GameActivity", "first click cardtab mis sur true")
        myRecyclerViewAdapter?.updateCardsList(cardTab)
        myRecyclerViewAdapter?.notifyItemChanged(index)
        Log.i("GameActivity", "recycler notified 1")
        previousIndex = index
        Log.i("GameActivity", "Attention le son va être lu 1")
        player.prepareSoundFile(cardTab[index].song)
        player.mediaPlayer.prepareAsync()
        player.mediaPlayer.setOnPreparedListener(){
            mediaPlayer?.start()
                Log.i("song kt", "Le mediaplayer a été starté")
         

        player.mediaPlayer.setOnCompletionListener() {
            Log.i("GameActivity", "setOnClickListenerEntrée")
                player.resetPlayer()
                firstCard = false
                songIsPlaying = false
            }
        }
    }


    fun doWhenSecondClick(index : Int) {

        Log.i("GameAcitivity", "do when second click entered")
        actualIndex = index
        // si on clique à nouveau sur la même case, il ne se pas rien

        if (actualIndex == previousIndex) return
        Log.i("GameAcitivity", "conditon 1 passée")

        songIsPlaying = true
        //  itemView.song_card.setBackgroundColor(ContextCompat.getColor(context, R.color.colorChecked))
        cardTab[index].checked = true;
        Log.i("GameAcitivity", "ColorChecked passed")
        myRecyclerViewAdapter?.updateCardsList(cardTab)
        myRecyclerViewAdapter?.notifyItemChanged(index)
        Log.i("GameAcitivity", "recycler notifies")

        player.prepareSoundFile(cardTab[actualIndex].song)
        player.mediaPlayer.prepareAsync()
        player.mediaPlayer.setOnPreparedListener(){
            player.mediaPlayer.start()
                Log.i("song kt", "Le mediaplayer a été starté")
         
        player.mediaPlayer.setOnCompletionListener() {
            Log.i("GameActivity", "setOnClickListenerEntrée")


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


    override fun onDestroy() {
       player.releasePlayer()
        super.onDestroy()
    }
}
