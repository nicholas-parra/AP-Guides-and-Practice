package com.example.apguidesandpractice.topic_resources

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.apguidesandpractice.R
import com.example.apguidesandpractice.databinding.ActivityTopicDetailBinding

class TopicDetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityTopicDetailBinding

    companion object {
        val EXTRA_TOPIC = "topic"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopicDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val topic = intent.getParcelableExtra(EXTRA_TOPIC) ?: Topic()
        title = topic.class_name

        binding.textViewTopicDetailTopicName.text = topic.unit.toString() + "." + topic.topicId + " " + topic.topicName

        binding.tableLayoutTopicDetailTopicInfo.setColumnStretchable(0, true)
        binding.tableLayoutTopicDetailTopicInfo.setColumnStretchable(1, true)
        binding.tableLayoutTopicDetailTopicInfo.setBackgroundColor(Color.parseColor("#F0F7F7"))

        val enduringUnderstandingView = TextView(this)
        enduringUnderstandingView.text = "Enduring Understanding\n" + topic.enduringUnderstanding
        enduringUnderstandingView.gravity = Gravity.CENTER_HORIZONTAL
        enduringUnderstandingView.maxWidth = binding.scrollViewTopicDetailTopicInfo.width

        val headerRow = TableRow(this)
        headerRow.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT)
        val learningObjectiveHeaderView = TextView(this)
        learningObjectiveHeaderView.text = "Learning Objectives"
        learningObjectiveHeaderView.maxWidth = binding.scrollViewTopicDetailTopicInfo.width / 2
        learningObjectiveHeaderView.gravity = Gravity.CENTER_HORIZONTAL
        val essentialKnowledgeHeaderView = TextView(this)
        essentialKnowledgeHeaderView.text = "Essential Knowledge"
        essentialKnowledgeHeaderView.maxWidth = binding.scrollViewTopicDetailTopicInfo.width / 2
        essentialKnowledgeHeaderView.gravity = Gravity.CENTER_HORIZONTAL
        headerRow.addView(learningObjectiveHeaderView)
        headerRow.addView(essentialKnowledgeHeaderView)

        val layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.WRAP_CONTENT,
            TableLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(0, 20, 0, 20)

        binding.tableLayoutTopicDetailTopicInfo.addView(enduringUnderstandingView, layoutParams)
        binding.tableLayoutTopicDetailTopicInfo.addView(headerRow, layoutParams)

        var i = 0
        for (learningObjective in topic.learningObjectives) {
            val row = TableRow(this)
            row.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT)

            val learningObjectiveView = TextView(this)
            learningObjectiveView.text = learningObjective
            learningObjectiveView.maxWidth = binding.scrollViewTopicDetailTopicInfo.width / 2
            learningObjectiveView.setPadding(dpToPx(5), dpToPx(5), 0, dpToPx(5))

            val essentialKnowledgeView = TextView(this)
            for (skill in topic.essentialKnowledge[i]) {
                essentialKnowledgeView.text = essentialKnowledgeView.text.toString() + "\n\n$skill"
            }
            essentialKnowledgeView.text = essentialKnowledgeView.text.toString().drop(2)
            essentialKnowledgeView.maxWidth = binding.scrollViewTopicDetailTopicInfo.width / 2
            essentialKnowledgeView.setPadding(dpToPx(5), dpToPx(5), 0, dpToPx(5))

            row.dividerDrawable = getDrawable(R.color.black)
            row.showDividers = TableRow.SHOW_DIVIDER_MIDDLE
            row.addView(learningObjectiveView)
            row.addView(essentialKnowledgeView)

            binding.tableLayoutTopicDetailTopicInfo.addView(row)
            i++
        }
    }
    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}