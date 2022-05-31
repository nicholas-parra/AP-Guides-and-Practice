package com.example.apguidesandpractice

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.apguidesandpractice.databinding.FragmentTestBinding
import com.example.apguidesandpractice.quiz_resources.QuizActivity

class TestFragment : Fragment() {
    private var _binding: FragmentTestBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTestBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val classData = arguments?.getParcelable(ClassActivity.EXTRA_DATA) ?: ClassData("", listOf(), listOf())

        val context = this.context
        val quizIntent = Intent(context, QuizActivity::class.java).apply {
            putExtra(QuizActivity.EXTRA_DATA, classData)
            putExtra(QuizActivity.EXTRA_UNIT, 0)
        }

        activity?.supportFragmentManager?.popBackStack()
        this.startActivity(quizIntent)

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}