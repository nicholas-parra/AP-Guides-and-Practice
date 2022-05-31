package com.example.apguidesandpractice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.apguidesandpractice.databinding.ActivityClassBinding
import com.example.apguidesandpractice.quiz_resources.Question
import com.example.apguidesandpractice.quiz_resources.QuizActivity
import com.example.apguidesandpractice.topic_resources.Topic
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ClassActivity : AppCompatActivity() {
    private lateinit var binding : ActivityClassBinding
    private val CURRICULUM = 0
    private val PRACTICE = 1
    private val TEST = 2
    companion object {
        val EXTRA_DATA = "data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        val tabLayout = binding.classTabLayout

        val className = intent?.getStringExtra("class name") ?: "uh oh"
        title = className
        val bundle = Bundle()
        val classData = getClassData(className)
        bundle.putParcelable(EXTRA_DATA, classData)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            var currentTab = 0
            override fun onTabSelected(selectedTab: TabLayout.Tab?) {
                if (currentTab == CURRICULUM) {
                    if (selectedTab == binding.classTabLayout.getTabAt(PRACTICE)) {
                        navController.navigate(R.id.action_nav_curriculum_to_nav_practice, bundle)
                        currentTab = PRACTICE
                    } else if (selectedTab == binding.classTabLayout.getTabAt(TEST)) {
                        val context = this@ClassActivity
                        val quizIntent = Intent(context, QuizActivity::class.java).apply {
                            putExtra(QuizActivity.EXTRA_DATA, classData)
                            putExtra(QuizActivity.EXTRA_UNIT, 0)
                        }
                        startActivity(quizIntent)
                        tabLayout.selectTab(tabLayout.getTabAt(0))
                        tabLayout.setScrollPosition(CURRICULUM, 0f, true)
                    }
                } else if (currentTab == PRACTICE) {
                    if (selectedTab == binding.classTabLayout.getTabAt(CURRICULUM)) {
                        navController.navigate(R.id.action_nav_practice_to_nav_curriculum, bundle)
                        currentTab = CURRICULUM
                    } else if (selectedTab == binding.classTabLayout.getTabAt(TEST)) {
                        val context = this@ClassActivity
                        val quizIntent = Intent(context, QuizActivity::class.java).apply {
                            putExtra(QuizActivity.EXTRA_DATA, classData)
                            putExtra(QuizActivity.EXTRA_UNIT, 0)
                        }
                        startActivity(quizIntent)
                        tabLayout.selectTab(tabLayout.getTabAt(1))
                        tabLayout.setScrollPosition(PRACTICE, 0f, true)
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                return
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                return
            }
        })

        navController.navigate(R.id.nav_curriculum, bundle)
    }

    private fun getClassData(className : String): ClassData {
        var inputStream = resources.openRawResource(R.raw.classes)
        var jsonText = inputStream.bufferedReader().use {
            it.readText()
        }
        var sType = object : TypeToken<List<ClassData>>() {}.type
        val classes = Gson().fromJson<List<ClassData>>(jsonText, sType)
        val desiredClass = classes.find {
            it.class_name == className
        } ?: ClassData("", listOf(), listOf()) // shouldn't happen lol


        inputStream = resources.openRawResource(R.raw.questions)
        jsonText = inputStream.bufferedReader().use {
            it.readText()
        }
        sType = object : TypeToken<List<Question>>() {}.type
        val questions = Gson().fromJson<List<Question>>(jsonText, sType)
        var desiredQuestions = mutableListOf<Question>()
        for (question in questions) {
            if (question.class_name == className) desiredQuestions.add(question)
        }
        desiredClass.questions = desiredQuestions

        inputStream = resources.openRawResource(R.raw.topics)
        jsonText = inputStream.bufferedReader().use {
            it.readText()
        }
        sType = object : TypeToken<List<Topic>>() {}.type
        val topics = Gson().fromJson<List<Topic>>(jsonText, sType)
        var desiredTopics = mutableListOf<Topic>()
        for (topic in topics) {
            if (topic.class_name == className) desiredTopics.add(topic)
        }
        desiredClass.topics = desiredTopics

        return desiredClass
    }

    override fun onBackPressed() {
        finish()
    }
}