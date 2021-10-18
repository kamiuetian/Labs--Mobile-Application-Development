package com.example.listviewsapp

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneStateListener
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.security.AccessControlContext

class MainActivity : AppCompatActivity() {
    val names=arrayOf("Kamran","Ali","Mohid","Qasim","Kamran","Ali","Mohid","Qasim","Kamran","Ali","Mohid","Qasim")
    val phone=arrayOf("1111","222222","3333","4444","1111","222222","3333","4444","1111","222222","3333","4444")
    val images=arrayOf(R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground
    ,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground
    ,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val lView=findViewById<ListView>(R.id.lView)
        val data=arrayOf("java","php","Koltin","C++")
        //val adapter=ArrayAdapter(this,R.layout.simple_list_item_1,data)
       lView.adapter=myAdapter(this,names,phone,images)
       lView.setOnItemClickListener{av,v,i,l->
            Toast.makeText(this,"Item Slected:${data[i]}",Toast.LENGTH_LONG)
                .show()
        }
    }













    /*Custom Adapter*/

    class myAdapter(private val context:Activity,
                    private val names:Array<String>,
                    private val phones:Array<String>,
                    private val images:Array<Int>
    ):ArrayAdapter<String>(context,R.layout.custom_list,names)
    {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflator=context.layoutInflater
            val view=inflator.inflate(R.layout.custom_list,null,true)
            view.findViewById<TextView>(R.id.name).text=names[position]
            view.findViewById<TextView>(R.id.phone).text=phones[position]
            view.findViewById<ImageView>(R.id.photo).setImageResource(images[position])

            return view
        }
    }

}