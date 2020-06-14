package com.example.lydi

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
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

    override fun showEditDeleteAlert() {
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setMessage("What would you like to do?")
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton("Proceed", DialogInterface.OnClickListener {
                    dialog, id -> finish()
            })
            // negative button text and action
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("AlertDialogExample")
        // show alert dialog
        alert.show()
    }
}

interface LoadMenuInterface {
    fun setSelected(name: String)
    fun showEditDeleteAlert()
}