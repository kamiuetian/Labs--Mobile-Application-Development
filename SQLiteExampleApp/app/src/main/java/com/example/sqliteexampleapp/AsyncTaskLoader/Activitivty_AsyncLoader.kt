package com.example.sqliteexampleapp.AsyncTaskLoader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import com.example.sqliteexampleapp.R

class Activitivty_AsyncLoader : AppCompatActivity(),LoaderManager.LoaderCallbacks<String> {
    lateinit var start_btn: Button
    lateinit var stop_btn: Button
    lateinit var tView:TextView
    lateinit var loaderManager: LoaderManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activitivty_async_loader)
        initwidgets()
       loaderManager= LoaderManager.getInstance(this)
        start_btn.setOnClickListener({
            loaderManager.initLoader(1,null,this)
        })
    }
    /*Initialize widgets*/
    private fun initwidgets() {
        start_btn=findViewById(R.id.startbtn)
        stop_btn=findViewById(R.id.stopbtn)
        tView=findViewById(R.id.tView)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {
        return MyTaskLoader(this)
    }

    override fun onLoadFinished(loader: Loader<String>, data: String?) {
        tView.setText(data)
    }

    override fun onLoaderReset(loader: Loader<String>) {
        TODO("Not yet implemented")
    }


}