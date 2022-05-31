package com.example.apguidesandpractice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apguidesandpractice.databinding.FragmentPracticeBinding

class PracticeFragment : Fragment() {
    private var _binding: FragmentPracticeBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter : UnitAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPracticeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val classData = arguments?.getParcelable(ClassActivity.EXTRA_DATA) ?: ClassData("", listOf(), listOf())

        adapter = UnitAdapter(classData, "Practice")
        binding.recyclerViewUnitList.adapter = adapter
        binding.recyclerViewUnitList.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewUnitList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}