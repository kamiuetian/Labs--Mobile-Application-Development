package com.example.sqliteexampleapp.sqlite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sqliteexampleapp.R


class ItemAdapter(val context: Context, val items: ArrayList<EmpModelClass>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.items_row,
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items.get(position)

        holder.tvName.text = item.name
        holder.tvEmail.text = item.email

        // Updating the background color according to the odd/even positions in list.
        if (position % 2 == 0) {
            holder.llMain.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorLightGray
                )
            )
        } else {
            holder.llMain.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite))
        }

        holder.ivEdit.setOnClickListener { view ->

            if (context is MainActivity) {
            context.updateRecordDialog(item)
        }
        }

        holder.ivDelete.setOnClickListener { view ->

            if (context is MainActivity) {
                context.deleteRecordAlertDialog(item)
            }
        }
    }

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each item to
        val llMain = view.findViewById<LinearLayout>(R.id.llMain)
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvEmail = view.findViewById<TextView>(R.id.tvEmail)
        val ivEdit = view.findViewById<ImageView>(R.id.ivEdit)
        val ivDelete = view.findViewById<ImageView>(R.id.ivDelete)
    }
}