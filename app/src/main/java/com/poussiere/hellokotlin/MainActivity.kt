package com.poussiere.hellokotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.poussiere.hellokotlin.utils.Song
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        mainTextView.setText(R.string.main_text_view_content)

        // retrieve a mutableList of all Cards objects instead
        var cardTab : MutableList<String> = mutableListOf("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q")
       var randomCardTab : MutableList<String> =Collections.shuffle(cardTab)

    }


}
