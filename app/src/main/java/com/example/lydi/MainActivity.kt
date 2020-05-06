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
    val scaleName = findViewById<TextView>(R.id.scale_name)
    val startAndStop = findViewById<Button>(R.id.start_and_stop)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }



    //MARK -> App bar
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
