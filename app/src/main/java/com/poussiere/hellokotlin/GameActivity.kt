package com.poussiere.hellokotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_game.*

/**
 * Created by poussiere on 23/01/18.
 */
class GameActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_game)

        //Configure recycler view in GrilLayout
        game_board.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(this@GameActivity, 3) // 3 = number of items on each row
        game_board.setLayoutManager(gridLayoutManager)
        val myRecyclerViewAdapter = MyRecyclerViewAdapter()
        game_board.setAdapter(myRecyclerViewAdapter)
    }

}