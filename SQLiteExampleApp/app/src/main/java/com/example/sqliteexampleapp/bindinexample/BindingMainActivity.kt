package com.example.sqliteexampleapp.bindinexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sqliteexampleapp.R
import com.example.sqliteexampleapp.databinding.ActivityBindingMainBinding

class BindingMainActivity : AppCompatActivity() {
    lateinit var binding:ActivityBindingMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBindingMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.textView.text="welcome"
        binding.button6.setOnClickListener({
            randomunction()
        })
    }

    private fun randomunction() {
        binding.textView.text=""
    }

}