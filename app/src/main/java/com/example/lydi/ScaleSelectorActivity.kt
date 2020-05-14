package com.example.lydi

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

class ScaleSelectorAdapter(private val myDataset: MutableList<String>) : RecyclerView.Adapter<ScaleSelectorAdapter.ScaleSelectorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScaleSelectorAdapter.ScaleSelectorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.scale_selector_list_item, parent, false)
        return ScaleSelectorViewHolder(view)
    }

    class ScaleSelectorViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.scale_selector_checkbox)
    }

    override fun onBindViewHolder(holder: ScaleSelectorViewHolder, position: Int) {
        holder.checkBox.text = myDataset[position]
        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener())
    }

    override fun getItemCount() = myDataset.size
}