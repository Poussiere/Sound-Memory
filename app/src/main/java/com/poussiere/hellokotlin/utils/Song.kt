package com.poussiere.hellokotlin.utils

import android.content.Context
import android.media.MediaPlayer
import com.poussiere.hellokotlin.R

class Song (val context : Context){

    var mediaPlayer : MediaPlayer?=null;



    fun play (){
       if (mediaPlayer==null) {
           mediaPlayer = MediaPlayer.create(context, R.raw.bateau)
           mediaPlayer?.start()
           mediaPlayer?.setOnCompletionListener(MediaPlayer.OnCompletionListener {
               mediaPlayer?.stop()
               mediaPlayer?.release()
               mediaPlayer = null;
           })

       }
    }

}

