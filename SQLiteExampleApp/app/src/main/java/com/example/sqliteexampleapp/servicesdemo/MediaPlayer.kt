package com.example.sqliteexampleapp.servicesdemo

import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import com.example.sqliteexampleapp.R
import android.widget.Toast

import com.example.sqliteexampleapp.sqlite.MainActivity

import android.content.BroadcastReceiver
import android.content.Context
import android.telephony.TelephonyManager


class MediaPlayroom : AppCompatActivity() {
    lateinit var receiver:MyReceiver
    lateinit var callreceiver:CallStatusReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)
        var start_btn=findViewById<Button>(R.id.button2)
        var stop_btn=findViewById<Button>(R.id.button3)
        var reg_recv=findViewById<Button>(R.id.button4)
       receiver= MyReceiver()

       IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {

            registerReceiver(receiver, it)
        }
        start_btn.setOnClickListener({
            startService(Intent(this,mediaplayerservice::class.java))
        })
        stop_btn.setOnClickListener({
            stopService(Intent(this,mediaplayerservice::class.java))
        })
        reg_recv.setOnClickListener({
            IntentFilter("com.example.custom_intent").also {
                registerReceiver(mMessageReceiver,it)
            }
        })
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }
    fun broadcast(view: View) {
        var intent=Intent()
        intent.setAction("com.example.custom_intent")
        intent.putExtra("key","value")
        sendBroadcast(intent)
    }
    private val mMessageReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // Extract data included in the Intent
            val message = intent.action
            Toast.makeText(this@MediaPlayroom, message, Toast.LENGTH_LONG).show()
        }
    }
}