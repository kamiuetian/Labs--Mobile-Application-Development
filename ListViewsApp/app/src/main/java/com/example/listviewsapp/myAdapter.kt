package com.example.listviewsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class myAdapter(val names:List<String>):RecyclerView.Adapter<myViewHolder>() {
    private lateinit var mlistener:onItemClickListener
    interface onItemClickListener
    {
        fun onItemClick(position: Int)

    }
    fun setOnItemClickListener(listener: onItemClickListener)
    {
        mlistener=listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val inflator=LayoutInflater.from(parent.context)
        var view=inflator.inflate(R.layout.custom_list,parent,false)
        return myViewHolder(view,mlistener)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.name.text=names[position]
    }

    override fun getItemCount(): Int {
        return names.size
    }

}

class myViewHolder(itemView: View,listener: myAdapter.onItemClickListener): RecyclerView.ViewHolder(itemView)
{
    var name=itemView.findViewById<TextView>(R.id.name)
    init{
itemView.setOnClickListener{
    listener.onItemClick(adapterPosition)
}
    }
}