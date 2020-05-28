package com.example.lydi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView

class ScaleSelectorAdapter(val myDataset: MutableList<String>, checkBoxListener: CheckBoxInterface) : RecyclerView.Adapter<ScaleSelectorAdapter.ScaleSelectorViewHolder>() {

    var checkedScales = mutableListOf<String>()
    val listener = checkBoxListener

    inner class ScaleSelectorViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.scale_selector_checkbox)

        init {
            view.setOnClickListener { checkBox.isChecked = !checkBox.isChecked }
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                val scaleName = myDataset[adapterPosition]
                if (isChecked) {
                    checkedScales.add(scaleName)
                    listener.enableSave()
                }
                else {
                    if (checkedScales.contains(scaleName)) checkedScales.remove(scaleName)
                    if (checkedScales.count() == 0) listener.disableSave()
                }
                listener.updateCheckedScales(checkedScales)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScaleSelectorAdapter.ScaleSelectorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.scale_selector_list_item, parent, false)
        return ScaleSelectorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScaleSelectorViewHolder, position: Int) {
        val scaleName = myDataset[position]
        holder.checkBox.text = scaleName
        holder.checkBox.isChecked = checkedScales.contains(scaleName)
    }

    fun getAllCheckedScales(): MutableList<String> {
        return checkedScales
    }

    override fun getItemCount() = myDataset.size
}