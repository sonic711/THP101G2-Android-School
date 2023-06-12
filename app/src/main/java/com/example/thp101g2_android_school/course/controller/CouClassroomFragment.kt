package com.example.thp101g2_android_school.course.controller

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.annotation.RequiresApi
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
import com.example.thp101g2_android_school.point.others.SetAlertDialog
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken


class CouClassroomFragment : Fragment() {
    private lateinit var contextSC: Context

    lateinit var binding: FragmentCouClassroomBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).supportActionBar?.show()
        val viewModel: CouClassroomViewModel by viewModels()
        binding = FragmentCouClassroomBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        contextSC = requireContext()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            val myCourse = arguments?.getSerializable("mycourse", MyCourse::class.java)
            val id = arguments?.getInt("id")
            if (id != null) {
                viewModel?.loadData(id)
            }
            val type = object: TypeToken<List<Chapter>>(){}.type
            val chapterList = requestTask<List<Chapter>>("http://10.0.2.2:8080/THP101G2-WebServer-School/chapter/${myCourse?.courseId}", respBodyType = type)
            val path = chapterList?.get(0)?.video
            if (path != null) {
                videoView.setVideoURI(Uri.parse(path))
            }
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
                val postUrl = "http://10.0.2.2:8080/THP101G2-WebServer-School/point"
                val requestBody = mapOf(
                    "type" to "insertForSC",
                )
                val responseType = object : TypeToken<SetAlertDialog.ApiResponse>() {}.type
                val response = requestTask<SetAlertDialog.ApiResponse>(postUrl, "POST", requestBody,
                    responseType)
                if (response != null) {
                    val daiBiao = SetAlertDialog(contextSC)
                    daiBiao.showAlertDialogForSC(contextSC)
                } else {
                }

            }
            videoView.start()
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.roomcourses?.observe(viewLifecycleOwner) { roomcourses ->
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = CouClassroomAdapter(roomcourses)
                } else {
                    (recyclerView.adapter as CouClassroomAdapter).updateroomCourses(roomcourses)
                }
            }
        }
    }

//    fun importVideoUrl(videoUrl: String) {
//        binding.videoView.setVideoURI(Uri.parse(videoUrl))
//        binding.videoView.setMediaController(MediaController(requireContext()))
//        binding.videoView.start()
//    }

}