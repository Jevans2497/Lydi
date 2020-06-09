package com.example.lydi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LoadMenuActivity: AppCompatActivity(), LoadMenuInterface {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    var internalStorage = InternalStorage()
    var preexistingScaleSets: ScaleSets? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.load_menu)
    }

    override fun onStart() {
        super.onStart()
        preexistingScaleSets = internalStorage.readFromMemory(this.baseContext)
        setupRecyclerView()
    }

    fun setupRecyclerView() {
        viewManager = LinearLayoutManager(this)
        viewAdapter = LoadMenuAdapter(scaleSetNames(), this)
        recyclerView = findViewById<RecyclerView>(R.id.load_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun scaleSetNames(): MutableList<String> {
        return preexistingScaleSets?.sets?.map { it.name } as MutableList<String>
    }

    override fun setSelected(name: String) {
        val intent = Intent()
        intent.putExtra("selectedSet", name)
        setResult(RESULT_OK, intent)
        finish()
    }

}

interface LoadMenuInterface {
    fun setSelected(name: String)
}