package com.example.lydi

import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ScaleSelectorActivity : AppCompatActivity(), CheckBoxInterface {

    lateinit var nameEditText: EditText
    lateinit var enharmonicSwitch: Switch
    lateinit var secondsEditText: EditText
    lateinit var saveButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    var allScales = ScaleSetManager().allScales

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scale_selector)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        setupViews()
    }

    fun setupViews() {
        setupSaveButton()
        setupRecyclerView()
        setupNameEditText()
        setupEnharmonicSwitch()
        setupSecondsEditText()
    }

    //MARK: Recycler view
    fun setupRecyclerView() {
        viewManager = LinearLayoutManager(this)
        viewAdapter = ScaleSelectorAdapter(allScales, this)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    //MARK: Save Button
    fun setupSaveButton() {
        saveButton = findViewById(R.id.save_button)
        saveButton.isEnabled = false
        saveButton.setBackgroundResource(R.drawable.rounded_button_disabled)
        saveButton.setOnClickListener { saveClicked() }
    }

    override fun enableSave() {
        if (!saveButton.isEnabled) {
            saveButton.isEnabled = true
            saveButton.setBackgroundResource(R.drawable.rounded_button_enabled)
        }
    }

    override fun disableSave() {
        if (saveButton.isEnabled) {
            saveButton.isEnabled = false
            saveButton.setBackgroundResource(R.drawable.rounded_button_disabled)
        }
    }

    fun saveClicked() {
        val internalStorage = InternalStorage()
        val scaleSet = makeScaleSet()
        
//        internalStorage.writeToMemory(" EMPTY ")
    }

    //MARK: Name Edit Text
    fun setupNameEditText() {
        nameEditText = findViewById(R.id.setName)
        nameEditText.hint = "Scale Set 1"
    }

    //MARK: Enharmonic Switch
    fun setupEnharmonicSwitch() {
        enharmonicSwitch = findViewById(R.id.enharmonic_switch)
    }

    //MARK: Seconds Number Picker
    fun setupSecondsEditText() {
        secondsEditText = findViewById(R.id.seconds_edit_text)
    }

    fun getSelectedScales(): MutableList<String> {
        return mutableListOf("boop")
    }

    fun makeScaleSet(): ScaleSet {
        val name = nameEditText.text.toString()
        val enharmonicsEnabled = enharmonicSwitch.isEnabled
        val timerSeconds = Integer.parseInt(secondsEditText.text.toString())
        val selectedScales =  getSelectedScales()
        var scaleSet: ScaleSet = ScaleSet(name, enharmonicsEnabled, timerSeconds, selectedScales)
        return scaleSet
    }
}

interface CheckBoxInterface {
    fun enableSave()
    fun disableSave()
}