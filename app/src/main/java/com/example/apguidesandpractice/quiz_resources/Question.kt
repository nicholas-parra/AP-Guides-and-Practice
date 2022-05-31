package com.example.apguidesandpractice.quiz_resources

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    val class_name : String,
    val unit : Int,
    val question : String,
    val answers : List<String>,
    @SerializedName("correct_answer")
    val correctAnswer : Int
) : Parcelable