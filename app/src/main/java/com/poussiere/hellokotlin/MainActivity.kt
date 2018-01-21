package com.poussiere.hellokotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.poussiere.hellokotlin.utils.Song
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        mainTextView.setText(R.string.main_text_view_content)


        val song = Song(this);

        songTextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                song.play();
            }


        })

    }


}