package com.example.sqliteexampleapp.servicesdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

import android.telephony.TelephonyManager




class CallStatusReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        /*Complete this class to display different call*/
        val state: String = intent?.getStringExtra(TelephonyManager.EXTRA_STATE).toString()

        if (state == TelephonyManager.EXTRA_STATE_RINGING) {
            Toast.makeText(context, "Phone Is Ringing", Toast.LENGTH_LONG).show()
        }

        if (state == TelephonyManager.EXTRA_STATE_OFFHOOK) {
            Toast.makeText(context, "Call Recieved", Toast.LENGTH_LONG).show()
        }

        if (state == TelephonyManager.EXTRA_STATE_IDLE) {
            Toast.makeText(context, "Phone Is Idle", Toast.LENGTH_LONG).show()
        }
    }
}