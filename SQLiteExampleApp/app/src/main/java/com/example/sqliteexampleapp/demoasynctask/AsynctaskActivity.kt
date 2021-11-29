package com.example.sqliteexampleapp.demoasynctask

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import com.example.sqliteexampleapp.R

class AsynctaskActivity : AppCompatActivity() {
    lateinit var start_btn:Button
    lateinit var stop_btn:Button
    lateinit var prog_bar:ProgressBar
    var async_task:backgroundTask?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asynctask)
        initwidgets()
        toggleView(stop_btn,true)
        async_task=backgroundTask()
        start_btn.setOnClickListener({
            async_task?.execute(60)
        })
        stop_btn.setOnClickListener({
            async_task?.cancel(true)
        })
    }
    /*Initialize widgets*/
    private fun initwidgets() {
        start_btn=findViewById(R.id.startbtn)
        stop_btn=findViewById(R.id.stopbtn)
        prog_bar=findViewById(R.id.progressBar)
    }
    /*Toggle view*/
    fun toggleView(view:View,isActive:Boolean)
    {
        if(isActive)
        {
            view.isEnabled=false
        }
        else
        {
            view.isEnabled=true
        }
    }
    inner class backgroundTask:AsyncTask<Int,Int,Unit>()
    {
        override fun onPreExecute() {
            toggleView(start_btn,true)
            toggleView(stop_btn,false)
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: Int?) {
            if(p0.isNotEmpty())
            {
                if(p0[0]!=null)
                {
                    for (i in 0..p0[0]!!)
                    {
                        Thread.sleep(1000)
                        publishProgress(i)
                    }
                }
            }
        }

        override fun onProgressUpdate(vararg values: Int?) {
            if(values.isNotEmpty() && values[0]!=null)
                prog_bar.progress=values[0]!!
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: Unit?) {
            toggleView(start_btn,false)
            toggleView(stop_btn,true)
            super.onPostExecute(result)
        }

        override fun onCancelled() {
            toggleView(start_btn,false)
            toggleView(stop_btn,true)
            super.onCancelled()
        }
    }


    /*AsyncTask Classs*/
    /*inner class AsyncTaskClass:AsyncTask<Int,Int,Unit>()
    {
        override fun onPreExecute() {
            toggleView(start_btn,true)
            toggleView(stop_btn,false)
            super.onPreExecute()
        }
        override fun doInBackground(vararg p0: Int?): Unit {

            if(p0.isNotEmpty())
            {
                if(p0[0]!=null)
                {
                    for (i in 0..p0[0]!!)
                    {
                        Thread.sleep(1000)
                        publishProgress(i)
                    }
                }
            }
        }

        override fun onProgressUpdate(vararg values: Int?) {
            if(values.isNotEmpty() && values[0]!=null)
            prog_bar.progress=values[0]!!
            super.onProgressUpdate(*values)
        }
        override fun onPostExecute(result: Unit?) {
            toggleView(start_btn,false)
            toggleView(stop_btn,true)
            super.onPostExecute(result)
        }

        override fun onCancelled() {
            toggleView(start_btn,false)
            toggleView(stop_btn,true)
            super.onCancelled()
        }

    }*/

}














