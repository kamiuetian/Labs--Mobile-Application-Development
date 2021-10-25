package com.example.listviewsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class customAdapter(val stdNames:List<String>):RecyclerView.Adapter<customViewHolder>() {
    private lateinit var mlistener: customAdapter.onItemClickListener
    interface onItemClickListener
    {
        fun onItemClick(position: Int)

    }
    fun setOnItemClickListener(listener: customAdapter.onItemClickListener)
    {
        mlistener=listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): customViewHolder {
        val inflator= LayoutInflater.from(parent.context)
        var view=inflator.inflate(R.layout.custom_list,parent,false)
        return customViewHolder(view,mlistener)
    }

    override fun onBindViewHolder(holder: customViewHolder, position: Int) {
        holder.name.text=stdNames[position]
    }

    override fun getItemCount(): Int {
        return stdNames.size
    }

}

class customViewHolder(itemView: View, listener: customAdapter.onItemClickListener):RecyclerView.ViewHolder(itemView)
{
    var name=itemView.findViewById<TextView>(R.id.name)
    var phone=itemView.findViewById<TextView>(R.id.phone)
    init{
        itemView.setOnClickListener{
            listener.onItemClick(adapterPosition)
        }
    }
}