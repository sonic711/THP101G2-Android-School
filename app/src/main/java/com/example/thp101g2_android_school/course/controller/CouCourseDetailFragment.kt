package com.example.thp101g2_android_school.course.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.course.model.Course
import com.example.thp101g2_android_school.course.viewmodel.CouMainDetailViewModel
import com.example.thp101g2_android_school.course.viewmodel.CouMainViewModel
import com.example.thp101g2_android_school.databinding.CouCourseDetailBinding
import com.example.thp101g2_android_school.databinding.FragmentCouMainBinding
class CouCourseDetailFragment: Fragment() {
    private lateinit var binding: CouCourseDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        (requireActivity() as MainActivity).supportActionBar?.show()
        val viewModel: CouMainDetailViewModel by viewModels()
        binding = CouCourseDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { bundle ->
            bundle.getSerializable("course")?.let {
                binding.viewModel?.course?.value = it as Course
            }
        }
    }
}