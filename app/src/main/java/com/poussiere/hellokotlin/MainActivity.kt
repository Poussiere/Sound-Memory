package com.poussiere.hellokotlin

import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.MainThread
import android.support.v7.app.AppCompatActivity
import com.jakewharton.rxbinding.view.RxView
import com.poussiere.hellokotlin.utils.Song
import kotlinx.android.synthetic.main.activity_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by poussiere on 18/12/1

        **/

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        mainTextView.setText(R.string.main_text_view_content)


        val song = Song(this);
        RxView.clicks(songTextView).subscribe{
        song.play()
        }



    }




}