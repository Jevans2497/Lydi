package com.example.lydi

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_scale_selector.*

class ScaleSelectorActivity : AppCompatActivity(), CheckBoxInterface {

    var prefs: Prefs? = null
    lateinit var nameEditText: EditText
    lateinit var enharmonicSwitch: Switch
    lateinit var secondsNumberPicker: NumberPicker
    lateinit var saveButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    var allScales = ScaleSetManager().allScales

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scale_selector)
        prefs = Prefs(this)
        setupSaveButton()
        setupRecyclerView()
        nameEditText = findViewById(R.id.setName)
        enharmonicSwitch = findViewById(R.id.enharmonic_switch)
        secondsNumberPicker = findViewById(R.id.seconds_picker)
    }

    fun setupRecyclerView() {
        viewManager = LinearLayoutManager(this)
        viewAdapter = ScaleSelectorAdapter(allScales, this)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    fun setupSaveButton() {
        saveButton = findViewById(R.id.save_button)
        saveButton.isEnabled = false
        saveButton.setBackgroundColor(ContextCompat.getColor(this, R.color.background))
        saveButton.setOnClickListener { saveClicked() }
    }

    override fun enableSave() {
        if (!saveButton.isEnabled) {
            saveButton.isEnabled = true
            saveButton.setBackgroundColor(Color.parseColor("#99ff99"))
        }
    }

    override fun disableSave() {
        if (saveButton.isEnabled) {
            saveButton.isEnabled = false
            saveButton.setBackgroundColor(ContextCompat.getColor(this, R.color.background))
        }
    }

    fun saveClicked() {
        prefs?.storeSet("Scale set 1", "TEST")
    }
}

interface CheckBoxInterface {
    fun enableSave()
    fun disableSave()
}

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
                    listener.enableSave()
                    checkedScales.add(scaleName)
                }
                else {
                    if (checkedScales.contains(scaleName)) checkedScales.remove(scaleName)
                    if (checkedScales.count() == 0) listener.disableSave()
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