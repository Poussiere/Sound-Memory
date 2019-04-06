package com.poussiere.hellokotlin.views

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

import android.databinding.DataBindingUtil
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.poussiere.hellokotlin.R
import com.poussiere.hellokotlin.databinding.ActivityGameBinding
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_game.*
import org.koin.android.viewmodel.ext.android.viewModel

class GameBoardActivity : AppCompatActivity(), MyRecyclerViewAdapter.AdapterOnClickHandler {

    /**
     * Injected ViewModel
     */
    private val gameViewModel: GameBoardViewModel by viewModel()

    /**
     * A disposable to dispose of observers
     */
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Set databinding
        val binding = DataBindingUtil.setContentView<ActivityGameBinding>(this,
                R.layout.activity_game)
        binding.viewModel = gameViewModel

        gameViewModel.setGameBoard()

        //Configure recycler view in GrilLayout
        game_board.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(this@GameBoardActivity, gameViewModel.getSpanCount())
        game_board.layoutManager = gridLayoutManager
        gameViewModel.myRecyclerViewAdapter = MyRecyclerViewAdapter(gameViewModel.cardTab,gameViewModel.getSpanCount(), this, getScreenWidth())
        game_board.adapter=gameViewModel.myRecyclerViewAdapter

        home_img.setOnClickListener {
            onBackPressed()
        }
        disposables.add(
                gameViewModel.isGameFinished.onChange.subscribe{
                    home_img.visibility = View.VISIBLE
                }
        )
        home_img.visibility = View.GONE
    }

    override fun doSomethingFromActivityWhenClick(index: Int) {
        // Le clique ne va réagir que si la carte cliquée n'a pas encore été découverte et que si la lecture d'un son n'est pas en cours

        if (!gameViewModel.cardTab[index].discovered && !gameViewModel.cardTab[index].discovered2 && !gameViewModel.songIsPlaying) {
            when (gameViewModel.firstCard) {
                true -> gameViewModel.onFirstCardClick(index)
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

    override fun onResume() {
        super.onResume()
        //Make app full screen
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
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

    fun getScreenWidth(): Int {
        val decorView = window.decorView
        val r = Rect()
        decorView.getWindowVisibleDisplayFrame(r)
        return r.right
    }
}
