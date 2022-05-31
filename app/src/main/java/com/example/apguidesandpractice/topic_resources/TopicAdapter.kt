package com.example.apguidesandpractice.topic_resources

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.apguidesandpractice.R
import com.example.apguidesandpractice.quiz_resources.QuizActivity

class TopicAdapter(var dataSet: List<Topic>, var currUnit : Int = -1) :
    RecyclerView.Adapter<TopicAdapter.ViewHolder>() {

    companion object {
        val TAG = "Adapter"
    }

    /*** Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView
        val layout : ConstraintLayout

        init {
            textViewName = view.findViewById(R.id.textView_topicItem_topicName)
            layout = view.findViewById(R.id.layout_topicItem)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_topic, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val currTopic = dataSet[position]
        viewHolder.textViewName.text = "$currUnit.${currTopic.topicId} ${currTopic.topicName}"
        viewHolder.layout.setOnClickListener {
            if (position > 1) {
                Toast.makeText(
                    it.context,
                    "Only topics 1 and 2 have functionality at the moment.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val topicDetailIntent = Intent(it.context, TopicDetailActivity::class.java).apply {
                    putExtra(TopicDetailActivity.EXTRA_TOPIC, currTopic)
                }
                it.context.startActivity(topicDetailIntent)
            }
        }
    }

    override fun getItemCount() = dataSet.size
}