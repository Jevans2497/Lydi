package com.example.lydi

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate

class MainActivity : AppCompatActivity() {

    private val toolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolBar) }
    val scaleName by lazy { findViewById<TextView>(R.id.scale_name) }
    val startAndStop by lazy { findViewById<Button>(R.id.start_and_stop) }
    var isRunning = false
    val scaleManager = ScaleManager()
    var selectedScales = mutableListOf<String>()

    var timer = Timer(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        startAndStop.setOnClickListener { startAndStopClicked() }
    }

    fun startAndStopClicked() {
        if (!isRunning) startRunning() else stopRunning()
    }

    fun startRunning() {
        isRunning = true
        startAndStop.text = "Stop"
        selectedScales = scaleManager.selectedScales()
        timer.scheduleAtFixedRate(0, 3000) {
            startDisplayingScales()
        }
    }

    fun stopRunning() {
        isRunning = false
        startAndStop.text = "Start"
        timer.cancel()
        timer = Timer(true)
        stopDisplayingScales()
    }

    fun startDisplayingScales() {
        runOnUiThread { scaleName.text = selectedScales.random() }
    }

    fun stopDisplayingScales() {

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
        startActivity(intent)
    }

    fun load() {
        //Do nothing
    }
}
