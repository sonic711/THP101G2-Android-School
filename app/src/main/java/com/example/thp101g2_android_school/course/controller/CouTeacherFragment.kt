package com.example.thp101g2_android_school.course.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.course.viewmodel.CouTeacherViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.course.viewmodel.CouMainViewModel
import com.example.thp101g2_android_school.databinding.FragmentCouMainBinding
import com.example.thp101g2_android_school.databinding.FragmentCouTeacherBinding

class CouTeacherFragment : Fragment() {

    private lateinit var binding: FragmentCouTeacherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).supportActionBar?.hide()
        val viewModel: CouTeacherViewModel by viewModels()
        binding = FragmentCouTeacherBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.courses?.observe(viewLifecycleOwner) { courses ->
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = CouTacherAdapter(courses)
                } else {
                    (recyclerView.adapter as CouTacherAdapter).updateCourses(courses)
                }
            }
        }
    }
}