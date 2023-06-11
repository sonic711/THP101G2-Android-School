package com.example.thp101g2_android_school.course.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.course.viewmodel.CouFavoriteDetailSelectViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.byteArrayToBitmap
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.course.model.Courses
import com.example.thp101g2_android_school.course.model.FavCourse
import com.example.thp101g2_android_school.course.viewmodel.CouMainDetailViewModel
import com.example.thp101g2_android_school.databinding.CouCourseDetailBinding
import com.example.thp101g2_android_school.databinding.FragmentCouFavoriteDetailBinding
import com.google.gson.JsonObject

class CouFavoriteDetailFragment : Fragment() {


    private lateinit var binding: FragmentCouFavoriteDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: CouFavoriteDetailSelectViewModel by viewModels()
        binding = FragmentCouFavoriteDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {bundle ->
            bundle.getSerializable("favcourse")?.let {
                binding.viewModel?.favcourse?.value = it as FavCourse
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
                            .navigate(R.id.action_couFavoriteDetailFragment_to_couClassroomFragment, bundle)
                    }
                    btDeleteFavorite.setOnClickListener {
                        requestTask<JsonObject>(
                            "http://10.0.2.2:8080/THP101G2-WebServer-School/favoritecourses/"+"${viewModel?.favcourse?.value!!.favoriteCoursesId}",
                            method = "DELETE",
                        )
                        Navigation.findNavController(it)
                            .navigate(R.id.couFavoriteFragment)

                    }
                }
            }


        }


    }



