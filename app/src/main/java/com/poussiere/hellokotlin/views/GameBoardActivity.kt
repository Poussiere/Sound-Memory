package com.poussiere.hellokotlin.views

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.poussiere.hellokotlin.R
import com.poussiere.hellokotlin.databinding.ActivityGameBinding
import kotlinx.android.synthetic.main.activity_game.*
import org.koin.android.viewmodel.ext.android.viewModel


/*Copyright (C) <2018>  <Nicolas BOUTIN>
        This program is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.
        This program is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.
        You should have received a copy of the GNU General Public License
        along with this program.  If not, see <http://www.gnu.org/licenses/
        contact us : von.linnasta@gmail.com
*/

class GameBoardActivity : AppCompatActivity(), MyRecyclerViewAdapter.AdapterOnClickHandler {

    /**
     * Injected ViewModel
     */
    private val gameViewModel: GameBoardViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Set databinding
        val binding = DataBindingUtil.setContentView<ActivityGameBinding>(this,
                R.layout.activity_game)
        binding.viewModel = gameViewModel

        gameViewModel.setGameBoard()

        //Configure recycler view in GrilLayout
        game_board.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(this@GameBoardActivity, 4) // 3 = number of items on each row
        game_board.layoutManager = gridLayoutManager
        gameViewModel.myRecyclerViewAdapter = MyRecyclerViewAdapter(gameViewModel.cardTab, this, gameViewModel.screenWidth())
        game_board.adapter=gameViewModel.myRecyclerViewAdapter
    }

    override fun doSomethingFromActivityWhenClick(index: Int) {
        // Le clique ne va réagir que si la carte cliquée n'a pas encore été découverte et que si la lecture d'un son n'est pas en cours

        if (!gameViewModel.cardTab[index].discovered && !gameViewModel.cardTab[index].discovered2 && !gameViewModel.songIsPlaying) {
            when (gameViewModel.firstCard) {
                true -> gameViewModel.onFirstCartdClick(index)
                false -> {
                    if (gameViewModel.playerNumber == 1) {
                        gameViewModel.onSecondCardClickOnePlayer(index)
                    } else {
                        gameViewModel.onSecondCardClickTwoPlayer(index)
                    }
                }
            }
        }
    }

    override fun onPause() {
        if (gameViewModel.player.mediaPlayer.isPlaying){
            gameViewModel.player.resetPlayer()
        }
        super.onPause()
    }

    override fun onDestroy() {
        gameViewModel.player.releasePlayer()
        super.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
       // gameViewModel.setGameBoard()
        player_tv.visibility = View.INVISIBLE
    }

    override fun onResume() {
        super.onResume()
    }

}
