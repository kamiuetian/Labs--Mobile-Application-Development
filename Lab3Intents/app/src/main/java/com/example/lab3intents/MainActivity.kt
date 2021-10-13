package com.example.lab3intents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //LifeCycle Method::when Activity is created
    val TAG="MAIN_ACTIVITY"
    var count=0
    lateinit var counter:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,"onCreate")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn=findViewById<Button>(R.id.button)
        val btn_browser=findViewById<Button>(R.id.button2)
       val btn_up=findViewById<Button>(R.id.countUp)
        btn_browser.setOnClickListener { launchBrowser() }
        btn_up.setOnClickListener { counterUp() }
        btn.setOnClickListener { launchSecond() }
        counter=findViewById<TextView>(R.id.counterText).apply {
            text=count.toString()
        }
        /*Register for Context Menu*/
        registerForContextMenu(counter)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)

    }

    /*Options Menu Selection */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                Toast.makeText(this, "click on setting", Toast.LENGTH_LONG).show()
                true
            }
            R.id.share ->{
                Toast.makeText(applicationContext, "click on share", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.exit ->{
                Toast.makeText(applicationContext, "click on exit", Toast.LENGTH_LONG).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    /*Context Menu*/
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.contextmenu,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
            Toast.makeText(this,"Copied",Toast.LENGTH_LONG).show()
        return true
    }
    override fun onSaveInstanceState(outState: Bundle,) {
       Log.d(TAG,"saving")
       // outState.putInt("count",count)
        outState.putString("message","hello")
        super.onSaveInstanceState(outState)

    }

    override fun onRestoreInstanceState(outState: Bundle) {
        Log.d(TAG,"Restoring")
        super.onRestoreInstanceState(outState)
        count=outState.getInt("count",0)
        counter.text=count.toString()
    }
    private fun counterUp() {
        count++
        counter.text=count.toString()
    }

     fun onStart(savedInstanceState: Bundle?) {
        super.onStart()
        Log.d(TAG,"onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.d(TAG,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy")
    }

    private fun launchBrowser() {
        val uri:Uri = Uri.parse("http://www.google.com")
        val it=Intent(Intent.ACTION_VIEW,uri)
        startActivity(it)
    }

    private fun launchSecond() {
    val message="This is a message from Main Avtivity"
    val it=Intent(this,secondActivity::class.java)
        it.putExtra("message","Hello from Activity 1")
        startActivity(it)

    }
}