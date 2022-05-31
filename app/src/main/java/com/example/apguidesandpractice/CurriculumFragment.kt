package com.example.apguidesandpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apguidesandpractice.databinding.FragmentCurriculumBinding

class CurriculumFragment : Fragment() {
    private var _binding: FragmentCurriculumBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter : UnitAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCurriculumBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val a = arguments?.getParcelable(ClassActivity.EXTRA_DATA) ?: ClassData("", listOf(), listOf())

        adapter = UnitAdapter(a, "Curriculum")
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