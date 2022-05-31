package com.example.apguidesandpractice.topic_resources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apguidesandpractice.R
import com.example.apguidesandpractice.UnitAdapter
import com.example.apguidesandpractice.databinding.ActivityClassBinding
import com.example.apguidesandpractice.databinding.ActivityTopicListBinding

class TopicListActivity : AppCompatActivity() {
    lateinit var binding : ActivityTopicListBinding
    lateinit var adapter : TopicAdapter

    companion object {
        val EXTRA_UNIT = "unit"
        val EXTRA_TOPICS = "topics"
        val TAG = "TopicListActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopicListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val topics = intent.getParcelableArrayListExtra<Parcelable>(EXTRA_TOPICS) as List<Topic>
        val currUnit = intent.getIntExtra(EXTRA_UNIT, 0)
        val className = topics[0].class_name
        title = className

        adapter = TopicAdapter(topics, currUnit)
        binding.recyclerViewTopicList.adapter = adapter
        binding.recyclerViewTopicList.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewTopicList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }
}