package com.example.lydi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate

class MainActivity : AppCompatActivity() {

    private val toolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolBar) }
    val scaleName by lazy { findViewById<TextView>(R.id.scale_name) }
    val startAndStop by lazy { findViewById<Button>(R.id.start_and_stop) }
    var isRunning = false
    var internalStorage = InternalStorage()
    var preexistingScaleSets: ScaleSets? = null
    var currentScaleSet: ScaleSet? = null

    var timer = Timer(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        startAndStop.setOnClickListener { startAndStopClicked() }
    }

    override fun onStart() {
        super.onStart()
        preexistingScaleSets = internalStorage.readFromMemory(this.baseContext)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                currentScaleSet = getSetFromName(data?.getStringExtra("selectedSet"))
            }
        }
    }

    fun startAndStopClicked() {
        if (!isRunning) startRunning() else stopRunning()
    }

    fun startRunning() {
        if (currentScaleSet == null) {
            showNoScaleSetSelectedToast()
        } else {
            isRunning = true
            startAndStop.text = "Stop"
            timer.scheduleAtFixedRate(0, currentScaleSet!!.timerSeconds.toLong() * 1000) {
                displayScales()
            }
        }
    }

    fun stopRunning() {
        isRunning = false
        startAndStop.text = "Start"
        timer.cancel()
        timer = Timer(true)
    }

    fun displayScales() {
        runOnUiThread { scaleName.text = currentScaleSet!!.selectedScales.random() }
    }

    fun showNoScaleSetSelectedToast() {
        Toast.makeText(applicationContext,"Invalid Scaleset",Toast.LENGTH_SHORT).show()
        val toast = Toast.makeText(applicationContext, "You must load in a scale set or create a new one", Toast.LENGTH_LONG)
        toast.show()
    }

    //MARK -> App bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_add -> createNew()
            R.id.action_load -> load()
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return false
    }

    fun createNew() {
        val intent = Intent(this, ScaleSelectorActivity::class.java)
        startActivityForResult(intent, 1)
    }

    fun load() {
        val intent = Intent(this, LoadMenuActivity::class.java)
        startActivityForResult(intent, 1)
    }

    fun setDefaultScaleSet() {
        if (preexistingScaleSets != null) {
            currentScaleSet = preexistingScaleSets!!.sets.last()
        }
    }

    private fun getSetFromName(name: String?): ScaleSet? {
        val set = preexistingScaleSets?.sets?.first { it.name == name }
        if (set == null) {
            return preexistingScaleSets?.sets?.last()
        } else {
            return set
        }
    }
}
