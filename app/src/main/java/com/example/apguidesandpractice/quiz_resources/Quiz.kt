package com.example.apguidesandpractice.quiz_resources

class Quiz(
    private val questionList : List<Question>,
    private var currentQuestionIndex : Int = 0,
    var score : Int = 0
) {

    fun getQuestionNumber() : Int {
        return questionList.size
    }

    //Have a function to see if there are any more questions remaining, returning a boolean
    fun questionsRemaining() : Boolean {
        return currentQuestionIndex < questionList.size
    }

    //Have a function to return the current question object
    fun getCurrentQuestion() : Question {
        return questionList[currentQuestionIndex]
    }

    fun isCorrect(playerAnswer : Int) : Boolean {
        if (playerAnswer == getCurrentQuestion().correctAnswer) {
            score++
            currentQuestionIndex++
            return true
        }
        currentQuestionIndex++
        return false
    }
}