package com.example.lydi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private val toolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolBar) }
    val scaleName by lazy { findViewById<TextView>(R.id.scale_name) }
    val startAndStop by lazy { findViewById<Button>(R.id.start_and_stop) }
    var isRunning = false
    val notes = mutableListOf("A", "Bb", "B", "C", "C#", "D", "Eb", "E", "F", "F#", "G", "Ab")

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
        startDisplayingScales()
    }

    fun stopRunning() {
        isRunning = false
        startAndStop.text = "Start"
        stopDisplayingScales()
    }

    fun startDisplayingScales() {

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
        //Do nothing
    }

    fun load() {
        //Do nothing
    }
}
