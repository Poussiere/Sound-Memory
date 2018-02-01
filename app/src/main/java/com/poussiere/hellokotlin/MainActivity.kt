package com.poussiere.hellokotlin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.poussiere.hellokotlin.data.Card
import com.poussiere.hellokotlin.utils.CardUtils
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.*


class MainActivity : AppCompatActivity() {

    val SHAREDPREFERENCES_PLAYERS_KEY: String = "player_number"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        main_text_view.setText(R.string.main_text_view_content)
        
        main_text_view.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            player_nb.setText("")
            ActivityOptionsCompat options = ActivityOptionsCompat.  
                       makeSceneTransitionAnimation(this@MainActivity,
                         main_text_view,
                         ViewCompat.getTransitionName(main_text_view));
                        startActivity(intent, options.toBundle());  
            
            
        
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
        conteneur.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        super.onResume()
        player_nb.setText(R.string.un_joueur)
    }
}
