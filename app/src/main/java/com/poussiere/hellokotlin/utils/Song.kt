package com.poussiere.hellokotlin.utils

import android.content.Context
import android.media.MediaPlayer
import android.util.Log


class Song (val context : Context?){


    var mediaPlayer : MediaPlayer = MediaPlayer()

    fun prepareSoundFile (song : Int) {

            val songFile = context?.getResources()?.openRawResourceFd(song) ?: return
            mediaPlayer?.setDataSource(songFile.fileDescriptor, songFile.startOffset, songFile.length)
            songFile.close()

        }

        //to be called a the end of each song
        fun resetPlayer() {
           mediaPlayer?.stop()
            mediaPlayer?.reset()

        }

        //to be called when activity is destroyed
        fun releasePlayer() {
            mediaPlayer?.release()
            mediaPlayer=null
        }
    }



