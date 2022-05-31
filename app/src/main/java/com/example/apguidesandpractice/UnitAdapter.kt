package com.example.apguidesandpractice

import android.content.Intent
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.apguidesandpractice.quiz_resources.QuizActivity
import com.example.apguidesandpractice.topic_resources.Topic
import com.example.apguidesandpractice.topic_resources.TopicListActivity
import java.util.ArrayList

class UnitAdapter(var dataSet: ClassData, var parent : String) :
    RecyclerView.Adapter<UnitAdapter.ViewHolder>() {

    companion object {
        val TAG = "Adapter"
    }

    /*** Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView
        val textViewDesc : TextView
        val layout : ConstraintLayout

        init {
            textViewName = view.findViewById(R.id.textView_unitItem_unitName)
            textViewDesc = view.findViewById(R.id.textView_unitItem_desc)
            layout = view.findViewById(R.id.layout_unitItem)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_unit, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val currUnit = position + 1
        if (parent == "Curriculum" || parent == "Practice") {
            viewHolder.textViewName.text = "Unit " + (currUnit).toString()
            viewHolder.textViewDesc.text = dataSet.units[position]
        }

        viewHolder.layout.setOnClickListener {
            val context = viewHolder.layout.context
            if (currUnit > 2) {
                Toast.makeText(
                    context,
                    "Only Units 1 and 2 have functionality at the moment.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            } else if (parent == "Curriculum") {
                val quizIntent = Intent(context, TopicListActivity::class.java).apply {
                    val desiredTopics = dataSet.topics.filter {
                        it.unit == position + 1
                    }
                    putParcelableArrayListExtra(TopicListActivity.EXTRA_TOPICS, desiredTopics as ArrayList<out Parcelable>)
                    putExtra(TopicListActivity.EXTRA_UNIT, currUnit)
                }
                context.startActivity(quizIntent)
            } else if (parent == "Practice") {
                val quizIntent = Intent(context, QuizActivity::class.java).apply {
                    putExtra(QuizActivity.EXTRA_DATA, dataSet)
                    putExtra(QuizActivity.EXTRA_UNIT, currUnit)
                }
                context.startActivity(quizIntent)
            }
        }
    }

    override fun getItemCount() = dataSet.units.size
}