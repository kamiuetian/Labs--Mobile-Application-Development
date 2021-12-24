package com.example.sqliteexampleapp.volleyapp

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.*
import com.example.sqliteexampleapp.R
import org.json.JSONArray
import org.json.JSONObject
import java.nio.charset.Charset

class volleyMainActivity : AppCompatActivity() {
    lateinit var btnStr:Button
    lateinit var btnJsonArr:Button
    lateinit var btnJsonObj:Button
    lateinit var tresp:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volley_main)
        initwidget()
/*String reposne*/
        btnStr.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val url = "https://run.mocky.io/v3/25a9e93e-2476-42e6-8db6-f6f1d5a70a02"
            val stringrequest =StringRequest(Request.Method.GET, url,
                { response ->
                    tresp.text = response
                },
                {error-> Log.d("Error",error.message) })
            queue.add(stringrequest)
        }
        btnJsonObj.setOnClickListener{
            val params=HashMap<String,String>()
            val url="https://ptsv2.com/t/xwtrv-1639465957/post"
            params.put("name","kamran")
            params.put("password","123")
            val jsonObject = JSONObject(params)
            val queue=Volley.newRequestQueue(this)
            val objreq=JsonObjectRequest(Request.Method.POST,url,jsonObject,
            Response.Listener { response ->
                              tresp.text=response.toString()
            },
            Response.ErrorListener {error ->
                tresp.text=error.message.toString()
            })
            queue.add(objreq)
        }

    }



    private fun initwidget() {
        btnStr=findViewById(R.id.btnStr)
        btnJsonArr=findViewById(R.id.jsonArr)
        btnJsonObj=findViewById(R.id.jsonObj)
        tresp=findViewById(R.id.tresp)
    }
}