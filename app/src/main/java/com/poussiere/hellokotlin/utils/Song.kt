package com.poussiere.hellokotlin.utils

import android.content.Context
import android.media.MediaPlayer
import android.util.Log


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

class Song (val context : Context?){


    var mediaPlayer : MediaPlayer = MediaPlayer()

    fun prepareSoundFile (song : Int) {

            val songFile = context?.getResources()?.openRawResourceFd(song) ?: return
            mediaPlayer.setDataSource(songFile.fileDescriptor, songFile.startOffset, songFile.length)
            songFile.close()

        }

        //to be called a the end of each song
        fun resetPlayer() {
           mediaPlayer.stop()
            mediaPlayer.reset()

        }

        //to be called when activity is destroyed
        fun releasePlayer() {
            mediaPlayer.release()

        }
    }



