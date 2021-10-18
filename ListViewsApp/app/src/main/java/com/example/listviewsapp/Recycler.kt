package com.example.listviewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Recycler : AppCompatActivity() {
    val names= listOf<String>("Kamran","Ali","Mohid","Qasim")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        val rView=findViewById<RecyclerView>(R.id.recycler)

      var adapter=myAdapter(names)
        rView.adapter=adapter
        adapter.setOnItemClickListener(object : myAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(this@Recycler,"Item ${names[position]} clicked",Toast.LENGTH_SHORT).show()
            }

        })

        rView.layoutManager=GridLayoutManager(this,3)
    }
}