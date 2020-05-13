package com.example.lydi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.LayoutInflaterCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ScaleSelectorActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    var scales = mutableListOf("Major", "minor", "Dorian", "Phrygian", "Lydian", "Mixolydian", "Aeolian", "Locrian")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scale_selector)

        viewManager = LinearLayoutManager(this)
        viewAdapter = ScaleSelectorAdapter(scales)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
    }
}

//The view holder objects are managed by an adapter, which you create by extending RecyclerView.Adapter.
// The adapter creates view holders as needed.
// The adapter also binds the view holders to their data.
// It does this by assigning the view holder to a position, and calling the adapter's onBindViewHolder() method.
// That method uses the view holder's position to determine what the contents should be, based on its list position.


class ScaleSelectorAdapter(private val myDataset: MutableList<String>) : RecyclerView.Adapter<ScaleSelectorAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ScaleSelectorAdapter.MyViewHolder {
        // create a new view
//        val textView = LayoutInflater.from(parent.context).inflate(R.layout.textview, parent, false) as TextView
//        val textView = LayoutInflater.from(parent.context)
        val textView = TextView(parent.context)
        textView.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.text = myDataset[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}