package com.example.lydi

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LoadMenuAdapter(val myDataset: MutableList<String>, loadMenuListener: LoadMenuInterface) : RecyclerView.Adapter<LoadMenuAdapter.LoadMenuViewHolder>() {

    val listener = loadMenuListener

    inner class LoadMenuViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.findViewById(R.id.load_set_name)

        init {
            view.setOnClickListener {
                val setName = myDataset[adapterPosition]
                listener.setSelected(setName)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoadMenuAdapter.LoadMenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.load_menu_list_item, parent, false)
        return LoadMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: LoadMenuViewHolder, position: Int) {
        holder.name.text = myDataset[position]
    }

    override fun getItemCount() = myDataset.size
}