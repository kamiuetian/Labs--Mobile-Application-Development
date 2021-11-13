package com.example.sqliteexampleapp.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import com.example.sqliteexampleapp.R

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val pref=PreferenceManager.getDefaultSharedPreferences(this)
        val sig=pref.getString("signature","")
        Log.d("Signature",sig)
        }
}