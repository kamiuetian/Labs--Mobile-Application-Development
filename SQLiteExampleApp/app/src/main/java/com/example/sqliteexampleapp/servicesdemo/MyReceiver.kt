package com.example.sqliteexampleapp.servicesdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast

import com.example.sqliteexampleapp.sqlite.MainActivity




class MyReceiver : BroadcastReceiver() {
var state:String?=null
    override fun onReceive(context: Context?, intent: Intent?) {
         val isAirplaneModeEnabled = intent?.getBooleanExtra("state", false) ?: return

        // checking whether airplane mode is enabled or not
        if (isAirplaneModeEnabled) {
            // showing the toast message if airplane mode is enabled
            Toast.makeText(context, "Airplane Mode Enabled", Toast.LENGTH_LONG).show()
        } else {
            // showing the toast message if airplane mode is disabled
            Toast.makeText(context, "Airplane Mode Disabled", Toast.LENGTH_LONG).show()
        }
    }

}



