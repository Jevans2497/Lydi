package com.example.lydi

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
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
    var checkedScales = mutableListOf<String>()
    val internalStorage = InternalStorage()
    var preexistingScaleSets: ScaleSets? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scale_selector)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        preexistingScaleSets = internalStorage.readFromMemory(this.baseContext)
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

    override fun updateCheckedScales(scales: MutableList<String>) {
        checkedScales = scales
    }

    fun saveClicked() {
        val scaleSet = makeScaleSet()
        val updatedScaleSets = appendScaleSetToScaleSets(scaleSet)
        InternalStorage().writeToMemory(this.baseContext, updatedScaleSets)
        finish()
    }

    //MARK: Name Edit Text
    fun setupNameEditText() {
        nameEditText = findViewById(R.id.setName)
        var num = preexistingScaleSets?.sets?.size
        if (num == null) { num = 1 }
        nameEditText.hint = "Scale Set " + num.toString()
    }

    //MARK: Enharmonic Switch
    fun setupEnharmonicSwitch() {
        enharmonicSwitch = findViewById(R.id.enharmonic_switch)
    }

    //MARK: Seconds Number Picker
    fun setupSecondsEditText() {
        secondsEditText = findViewById(R.id.seconds_edit_text)
    }

    //MARK: Making scale sets
    fun appendScaleSetToScaleSets(scaleSet: ScaleSet): ScaleSets {
        val currentScaleSets = preexistingScaleSets
        if (currentScaleSets == null) {
            var newSets = ScaleSets()
            newSets.sets.add(scaleSet)
            return newSets
        } else {
            currentScaleSets.sets.add(scaleSet)
            return currentScaleSets
        }
    }

    fun makeScaleSet(): ScaleSet {
        var name = nameEditText.text.toString()
        if (name == "") { name = nameEditText.hint.toString() }

        var enharmonicsEnabled = enharmonicSwitch.isEnabled

        var timerSecondsString = secondsEditText.text.toString()
        var timerSeconds = 0

        if (timerSecondsString.isDigitsOnly() && timerSecondsString != "") {
            timerSeconds = Integer.parseInt(timerSecondsString)
        } else {
            timerSeconds = 7
        }

        val selectedScales =  checkedScales
        var scaleSet: ScaleSet = ScaleSet(name, enharmonicsEnabled, timerSeconds, selectedScales)
        return scaleSet
    }
}

interface CheckBoxInterface {
    fun enableSave()
    fun disableSave()
    fun updateCheckedScales(scales: MutableList<String>)
}