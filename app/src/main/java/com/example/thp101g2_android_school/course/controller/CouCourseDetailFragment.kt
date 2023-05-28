package com.example.thp101g2_android_school.course.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.byteArrayToBitmap
import com.example.thp101g2_android_school.course.model.Course
import com.example.thp101g2_android_school.course.model.Courses
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
                binding.viewModel?.course?.value = it as Courses
                if (it.image != null) {
                    val img = byteArrayToBitmap(it.image!!)
                    binding.ivCourse.setImageBitmap(img)
                }else{
                    binding.ivCourse.setBackgroundResource(R.drawable.com_user)
                }
            }
            with(binding){

                btAddCourse.setOnClickListener {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_couCourseDetailFragment_to_couMyCourseFragment)
                    Toast.makeText(view.context, "成功加入我的課程", Toast.LENGTH_SHORT).show()
                }
                btAddFavorite.setOnClickListener {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_couCourseDetailFragment_to_couFavoriteFragment)
                    Toast.makeText(view.context, "成功加入我的最愛", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
}