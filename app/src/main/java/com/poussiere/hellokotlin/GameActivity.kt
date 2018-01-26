package com.poussiere.hellokotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.poussiere.hellokotlin.data.Card
import com.poussiere.hellokotlin.utils.CardUtils
import kotlinx.android.synthetic.main.activity_game.*

/**
 * Created by poussiere on 23/01/18.
 */
class GameActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_game)



        // retrieve a mutableList of all Cards objects instead
        // var cardTab : MutableList<String> = mutableListOf("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q")
        var cardTab : MutableList<Card> = CardUtils.initCards()
        cardTab.shuffle();

        //Configure recycler view in GrilLayout
        game_board.setHasFixedSize(true)
        game_borad.setHasStableIds(true)
        val gridLayoutManager = GridLayoutManager(this@GameActivity, 4) // 3 = number of items on each row
        game_board.setLayoutManager(gridLayoutManager)
        val myRecyclerViewAdapter = MyRecyclerViewAdapter(cardTab)
        game_board.setAdapter(myRecyclerViewAdapter)
    }

}
