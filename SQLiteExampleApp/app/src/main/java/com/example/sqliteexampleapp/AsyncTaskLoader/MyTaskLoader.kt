package com.example.sqliteexampleapp.AsyncTaskLoader

import android.content.Context
import android.util.Log
import androidx.loader.content.AsyncTaskLoader


 class MyTaskLoader(context: Context) : AsyncTaskLoader<String>(context) {
     override fun loadInBackground(): String? {
         for(i in 0..10)
         {
             Thread.sleep(1000)
             Log.d("progress",i.toString())
         }

         return "Task is complete"
     }
     override fun onStartLoading() {
         forceLoad()
     }

 }
/*class MyTaskLoader(context: Context) : AsyncTaskLoader<String>(context) {
    override fun loadInBackground(): String? {
        for(i in 0..20)
        {
            Thread.sleep(1000)
            Log.d("logVal",i.toString())
        }
        return "Task is complete"
    }
    override fun onStartLoading() {
        forceLoad()
    }

}*/
/*class MyTaskLoader(context: Context) : AsyncTaskLoader<String>(context) {
    override fun loadInBackground(): String? {
        for(i in 0..50)
        {
            Thread.sleep(1000)

        }
        return "My Result"
    }

    override fun onStartLoading() {
        forceLoad()
    }
}*/