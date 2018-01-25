package com.poussiere.hellokotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.poussiere.hellokotlin.data.Card
import com.poussiere.hellokotlin.utils.CardUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        mainTextView.setText(R.string.main_text_view_content)
        mainTextView.setOnClickListener{
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            startActivity(intent)
        }


    }


}
