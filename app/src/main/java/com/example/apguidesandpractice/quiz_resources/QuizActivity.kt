package com.example.apguidesandpractice.quiz_resources

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apguidesandpractice.ClassData
import com.example.apguidesandpractice.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    lateinit var game : Quiz
    lateinit var binding : ActivityQuizBinding
    lateinit var answerButtons : MutableList<Button>

    companion object {
        val EXTRA_DATA = "class data"
        val EXTRA_UNIT = "desired unit"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.quizTextFinalScore.visibility = View.GONE
        binding.buttonQuizReturn.setOnClickListener { finish() }
        answerButtons = mutableListOf()
        answerButtons.add(binding.buttonQuizAnswerOne)
        answerButtons.add(binding.buttonQuizAnswerTwo)
        answerButtons.add(binding.buttonQuizAnswerThree)
        answerButtons.add(binding.buttonQuizAnswerFour)


        val classData = intent.getParcelableExtra<ClassData>(EXTRA_DATA)
        val unit = intent.getIntExtra(EXTRA_UNIT, 0)
        val questions = classData!!.questions

        val desiredQuestions = mutableListOf<Question>()

        if (unit != 0) {
            for (question in questions) {
                if (question.unit == unit) desiredQuestions.add(question)
            }
        } else {
            while (desiredQuestions.size < 10) {
                val randQuestion = questions.random()
                if (!desiredQuestions.contains(randQuestion)) desiredQuestions.add(randQuestion)
            }
        }

        game = Quiz(desiredQuestions)

        for (i in 0..3) {
            answerButtons[i].setOnClickListener {
                val currentQuestion = game.getCurrentQuestion()
                val response = if (game.isCorrect(i)) {
                    "Correct!"
                } else {
                    "Wrong! The correct answer was: " +
                            "\"${currentQuestion.answers[currentQuestion.correctAnswer]}\""
                }
                Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
                if (game.questionsRemaining()) {
                    updateText()
                } else {
                    reportScore()
                }
            }
        }

        updateText()
    }

    private fun updateText() {
        val currentQuestion = game.getCurrentQuestion()
        binding.quizTextQuestion.text = currentQuestion.question
        for (i in 0..3) {
            answerButtons[i].text = currentQuestion.answers[i]
        }
        binding.quizTextCurrentScore.text = "Current score: " + game.score + "/${game.getQuestionNumber()}"
    }

    private fun reportScore() {
        binding.groupQuizMainUi.visibility = View.GONE
        binding.quizTextFinalScore.text = "Final score: ${game.score}/${game.getQuestionNumber()}"
        binding.quizTextFinalScore.visibility = View.VISIBLE
    }

}