package com.example.sqliteexampleapp.preferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import com.example.sqliteexampleapp.R

class MainActivityPreferences : AppCompatActivity() {
    lateinit var btnLoad:Button
    lateinit var btnSave:Button
    lateinit var etName:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_preferences)
        initwisgets()
        btnSave.setOnClickListener{
            savePreferences()}
        btnLoad.setOnClickListener {
            loadPrefereces()
        }
    }

    private fun loadPrefereces() {

        val prefs=
            getSharedPreferences(resources.getString(R.string.prefFile), MODE_PRIVATE)
        val value=prefs.getString("name","")
        etName.setText(value)

    }

    private fun savePreferences() {
        var prefs=getSharedPreferences(resources.getString(R.string.prefFile), MODE_PRIVATE)
        var editor=prefs.edit()
        editor.putString("name",etName.text.toString())
        editor.apply()

    }

    private fun initwisgets() {
        btnLoad=findViewById(R.id.load)
        btnSave=findViewById(R.id.save)
        etName=findViewById(R.id.etPrefName)
    }


    /*private fun loadPreferences() {
    val prefs=getSharedPreferences(resources.getString(R.string.prefFile), MODE_PRIVATE)
        val value=prefs.getString("name","")
        name.setText(value)
    }

    private fun savePreferences() {
    val prefs=getSharedPreferences(resources.getString(R.string.prefFile),MODE_PRIVATE)
    val editor=prefs.edit()
        editor.putString("name",name.text.toString())
            .apply()
    }

    private fun initiwidget() {
        name=findViewById<EditText>(R.id.etPrefName)
        btnSave=findViewById<Button>(R.id.save)
        btnLoad=findViewById<Button>(R.id.load)
    }*/
}