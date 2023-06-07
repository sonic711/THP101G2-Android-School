package com.example.thp101g2_android_school.course.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.course.viewmodel.CouMyCourseSelectViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.byteArrayToBitmap
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.course.model.FavCourse
import com.example.thp101g2_android_school.course.model.MyCourse
import com.example.thp101g2_android_school.course.viewmodel.CouFavoriteDetailSelectViewModel
import com.example.thp101g2_android_school.databinding.FragmentCouFavoriteDetailBinding
import com.example.thp101g2_android_school.databinding.FragmentCouMyCourseSelectBinding
import com.google.gson.JsonObject

class CouMyCourseSelectFragment : Fragment() {

    private lateinit var binding: FragmentCouMyCourseSelectBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: CouMyCourseSelectViewModel by viewModels()
        binding = FragmentCouMyCourseSelectBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {bundle ->
            bundle.getSerializable("mycourse")?.let {
                binding.viewModel?.mycourse?.value = it as MyCourse
                if (it.image != null) {
                    val img = byteArrayToBitmap(it.image!!)
                    binding.ivCourse.setImageBitmap(img)
                } else {
                    binding.ivCourse.setBackgroundResource(R.drawable.com_user)
                }
            }
                with(binding){

                    btStartCourse.setOnClickListener {
                        Navigation.findNavController(it)
                            .navigate(R.id.couClassroomFragment, bundle)
                    }
                    btDeleteMy.setOnClickListener {
                        requestTask<JsonObject>(
                            "http://10.0.2.2:8080/THP101G2-WebServer-School/studentcourses/"+"${viewModel?.mycourse?.value!!.studentCoursesId}",
                            method = "DELETE",
                        )
                        Navigation.findNavController(it)
                            .navigate(R.id.couMyCourseFragment)

                    }
                }
            }



        }

    }
