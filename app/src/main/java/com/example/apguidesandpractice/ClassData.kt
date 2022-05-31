package com.example.apguidesandpractice

import android.os.Parcelable
import com.example.apguidesandpractice.quiz_resources.Question
import com.example.apguidesandpractice.topic_resources.Topic
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClassData(
    var class_name : String,
    var units : List<String>,
    var topics : List<Topic>,
    var questions : List<Question> = listOf()
) : Parcelable
