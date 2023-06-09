package com.example.thp101g2_android_school.course.controller

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.course.model.Chapter
import com.example.thp101g2_android_school.course.model.Courses
import com.example.thp101g2_android_school.course.model.MyCourse
import com.example.thp101g2_android_school.course.viewmodel.CouClassroomViewModel
import com.example.thp101g2_android_school.course.viewmodel.CouFavoriteViewModel
import com.example.thp101g2_android_school.databinding.FragmentClassDetailBinding
import com.example.thp101g2_android_school.databinding.FragmentCouClassroomBinding
import com.example.thp101g2_android_school.databinding.FragmentCouFavoriteBinding
import com.google.gson.JsonObject


class CouClassroomFragment : Fragment() {

    private lateinit var binding: FragmentCouClassroomBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).supportActionBar?.show()
        val viewModel: CouClassroomViewModel by viewModels()
        binding = FragmentCouClassroomBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
             val path = "URL"
            videoView.setVideoURI(Uri.parse(path))
            videoView.setMediaController(MediaController(view.context))
            videoView.setOnCompletionListener {
                Navigation.findNavController(view)
                    .navigate(R.id.action_couClassroomFragment_to_couRateFragment)
                val coursesProgress = viewModel?.coursesProgress?.value ?: true
                val test = MyCourse(
                    coursesProgress = coursesProgress
                )
                requestTask<JsonObject>(
                    "http://10.0.2.2:8080/THP101G2-WebServer-School/studentcourses/",
                    method = "PUT",
                    reqBody = test
                )

            }
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.roomcourses?.observe(viewLifecycleOwner){roomcourses ->
                if (recyclerView.adapter == null){
                    recyclerView.adapter = CouClassroomAdapter(roomcourses)
                }else{
                    (recyclerView.adapter as CouClassroomAdapter).updateroomCourses(roomcourses)
                }
            }
        }
    }
}