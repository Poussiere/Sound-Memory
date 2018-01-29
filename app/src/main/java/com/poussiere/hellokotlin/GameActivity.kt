package com.poussiere.hellokotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
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
    var firstCard : Boolean = false
    var previousIndex = 1
    var actualIndex = 1
    var myRecyclerViewAdapter : MyRecyclerViewAdapter?=null
    var player : Song = Song(this)

    override fun doSomethingFromActivityWhenClick(index: Int) {
        // Le clique ne va réagir que si la carte cliquée n'a pas encore été découverte et que si la lecture d'un son n'est pas en cours
        if (!cardTab[index].discovered && !songIsPlaying){

            when (firstCard){
                true -> doWhenFirstClick(index)
                false -> doWhenSecondClick(index)
            }
        }
    }


    fun doWhenFirstClick(index : Int) {

       // itemView.song_card.setBackgroundColor(ContextCompat.getColor(context, R.color.colorChecked))
        cardTab[index].checked = true;
        myRecyclerViewAdapter?.updateCardsList(cardTab)
        myRecyclerViewAdapter?.notifyItemChanged(index)
        songIsPlaying = true
        previousIndex = index
        player.play(cardTab[index].song)
        player.mediaPlayer?.setOnCompletionListener() {

            player.resetPlayer()
            songIsPlaying = false
            firstCard = false

        }
    }


    fun doWhenSecondClick(index : Int){
        songIsPlaying=true
      //  itemView.song_card.setBackgroundColor(ContextCompat.getColor(context, R.color.colorChecked))
        cardTab[index].checked = true;
        myRecyclerViewAdapter?.updateCardsList(cardTab)
        myRecyclerViewAdapter?.notifyItemChanged(index)
        actualIndex=index

        var cardsAreSimilar : Boolean = false


        if (cardTab[previousIndex].id ==cardTab[actualIndex].id){
            cardsAreSimilar = true
        }

        player.play(cardTab[actualIndex].song)
        player.mediaPlayer?.setOnCompletionListener(){

            if (cardsAreSimilar){
                cardTab[previousIndex].discovered=true
                cardTab[actualIndex].discovered=true}
            songIsPlaying=false
            cardTab[previousIndex].checked=false
            firstCard=true
            myRecyclerViewAdapter?.updateCardsList(cardTab)
           myRecyclerViewAdapter?.notifyDataSetChanged()
        }



    }


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

    override fun onDestroy() {
       player.releasePlayer()
        super.onDestroy()
    }
}
