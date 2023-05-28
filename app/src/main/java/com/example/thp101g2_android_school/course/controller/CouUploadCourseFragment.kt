package com.example.thp101g2_android_school.course.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thp101g2_android_school.course.viewmodel.CouUploadCourseViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentCouUploadCourseBinding

class CouUploadCourseFragment : Fragment() {

    private lateinit var binding: FragmentCouUploadCourseBinding
    private lateinit var viewModel: CouUploadCourseViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCouUploadCourseBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(CouUploadCourseViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}


