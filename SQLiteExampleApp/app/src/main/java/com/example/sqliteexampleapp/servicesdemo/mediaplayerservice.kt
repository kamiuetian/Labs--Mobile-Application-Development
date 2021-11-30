package com.example.sqliteexampleapp.servicesdemo

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import com.example.sqliteexampleapp.R

class mediaplayerservice: Service(){
    lateinit var player:MediaPlayer
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var uri= Uri.parse("file:///android_res/raw/file.mp3");
        player= MediaPlayer.create(this, R.raw.file)
        player.isLooping=true
        player.start()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        player.stop()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

}