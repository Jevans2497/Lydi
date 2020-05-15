package com.example.lydi

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ScaleSelectorActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    var scales = mutableListOf("Major", "minor", "Dorian", "Phrygian", "Lydian", "Mixolydian", "Aeolian", "Locrian", "Diminished H/W", "Diminished W/H", "Major Pentatonic", "minor Pentatonic", "Augmented", "Whole Tone", "Chromatic")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scale_selector)

        viewManager = LinearLayoutManager(this)
        viewAdapter = ScaleSelectorAdapter(scales)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}

class ScaleSelectorAdapter(val myDataset: MutableList<String>) : RecyclerView.Adapter<ScaleSelectorAdapter.ScaleSelectorViewHolder>() {

    var checkedScales = mutableListOf<String>()

    inner class ScaleSelectorViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.scale_selector_checkbox)

        init {
            view.setOnClickListener { checkBox.isChecked = !checkBox.isChecked }
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                val scaleName = myDataset[adapterPosition]
                if (isChecked) checkedScales.add(scaleName)
                else {
                    if (checkedScales.contains(scaleName)) checkedScales.remove(scaleName)
                }
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

    override fun getItemCount() = myDataset.size
}