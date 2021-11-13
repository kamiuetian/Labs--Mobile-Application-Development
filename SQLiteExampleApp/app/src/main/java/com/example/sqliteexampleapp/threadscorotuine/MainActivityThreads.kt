package com.example.sqliteexampleapp.threadscorotuine

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import com.example.sqliteexampleapp.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivityThreads : AppCompatActivity() {
    val TAG1="Call1"
    val TAG2="Call2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_threads)

        Log.d("MainCall",Thread.currentThread().name)
        thread {  call1() }
        Log.d("MainCall",Thread.currentThread().name)

    }
    private fun call1() {
        var count=1
        while (count<10)
        {count ++
            Log.d(TAG1,Thread.currentThread().name)
        }
        Thread.sleep(3000)
    }
    private fun call2() {
        var count=1
        while (count<10)
        {count ++
            Log.d(TAG2,Thread.currentThread().name)
        }
        Thread.sleep(3000)
    }


}