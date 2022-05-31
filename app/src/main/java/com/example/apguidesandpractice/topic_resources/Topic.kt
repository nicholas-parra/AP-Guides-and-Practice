package com.example.apguidesandpractice.topic_resources

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Topic(
    var class_name : String = "",
    var unit : Int = -1,
    @SerializedName("topic_name")
    var topicName : String = "",
    @SerializedName("topic_id")
    var topicId : Int = -1,
    @SerializedName("enduring_understanding")
    var enduringUnderstanding : String = "",
    @SerializedName("learning_objectives")
    var learningObjectives : List<String> = listOf(),
    @SerializedName("essential_knowledge")
    var essentialKnowledge : List<List<String>> = listOf()
) : Parcelable
