package com.poussiere.hellokotlin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.constraint.ConstraintLayout
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.ActivityOptionsCompat.makeSceneTransitionAnimation
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.poussiere.hellokotlin.data.Card
import com.poussiere.hellokotlin.utils.CardUtils
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recycler_element.view.*
import kotlin.collections.*


class MainActivity : AppCompatActivity() {

    companion object {
        val SHAREDPREFERENCES_PLAYERS_KEY: String = "player_number"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        main_text_view.setText(R.string.main_text_view_content)

        
        main_text_view.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            player_nb.setText("")
            var options : ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity,
                         main_text_view,
                         ViewCompat.getTransitionName(main_text_view));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                startActivity(intent, options.toBundle())
            };  else startActivity(intent)
            
            
        
        }
        var prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        var playerNumber: Int = prefs.getInt(SHAREDPREFERENCES_PLAYERS_KEY, 1)

        when (playerNumber) {
            1 -> player_nb.setText(R.string.un_joueur)
            2 -> player_nb.setText(R.string.deux_joueurs)
        }


        player_nb.setOnClickListener() {
            playerNumber = prefs.getInt(SHAREDPREFERENCES_PLAYERS_KEY, 1)

            if (playerNumber == 1) {

                player_nb.setText(R.string.deux_joueurs)
                prefs.edit().putInt(SHAREDPREFERENCES_PLAYERS_KEY, 2).apply()
            } else if (playerNumber == 2) {
                player_nb.setText(R.string.un_joueur)
                prefs.edit().putInt(SHAREDPREFERENCES_PLAYERS_KEY, 1).apply()
            }
        }

    }

    override fun onResume() {
        player_nb.setText(R.string.un_joueur)
        super.onResume()

    }

}
