package com.poussiere.hellokotlin.utils

import android.content.Context
import android.media.MediaPlayer



class Song (val context : Context?){


    var mediaPlayer : MediaPlayer = MediaPlayer()


    fun play (song : Int) {

        try {
            val songFile = context?.getResources()?.openRawResourceFd(song) ?: return
            mediaPlayer.setDataSource(songFile.fileDescriptor, songFile.startOffset, songFile.length)
            songFile.close()
        } catch (exeption: Exception) {//do nothing
        }

            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener(){
            mediaPlayer.start()
         }
            

        }

        //to be called a the end of each song
        fun resetPlayer() {
           mediaPlayer.stop()
            mediaPlayer.reset()
        }

        //to be called when activity is destroyed
        fun releasePlayer() {
            mediaPlayer.stop()
            mediaPlayer.reset()
            mediaPlayer.release()
        }
    }



