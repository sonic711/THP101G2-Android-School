package com.example.thp101g2_android_school.course.controller

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.course.viewmodel.CouUploadCourseViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.course.model.Courses
import com.example.thp101g2_android_school.course.model.UpCourses
import com.example.thp101g2_android_school.databinding.FragmentCouUploadCourseBinding
import com.example.thp101g2_android_school.member.model.Member
import org.json.JSONObject
import java.io.ByteArrayOutputStream

class CouUploadCourseFragment : Fragment() {

    private lateinit var binding: FragmentCouUploadCourseBinding
    private lateinit var viewModel: CouUploadCourseViewModel
    private lateinit var byteArray : ByteArray
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btPick.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                PictureLauncher.launch(intent)
            }

            btSub.setOnClickListener {
                val member: Member? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/members", "OPTIONS")
                var memberNo = member?.memberNo
                val test = UpCourses(
                    courseName = viewModel?.name?.value!!,
                    summary = viewModel?.summary?.value!!,
                    image = byteArray,
                    memberNo = memberNo
                )
                requestTask<JSONObject>(
                    "http://10.0.2.2:8080/THP101G2-WebServer-School/course/",
                    method = "POST",
                    reqBody = test
                )
                val bundle = Bundle()
                bundle.putSerializable("course", test)
                Navigation.findNavController(it)
                    .navigate(R.id.action_couUploadCourseFragment_to_couUploadChapterFragment, bundle)
            }
        }
    }


    private var PictureLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
           if (result.resultCode == Activity.RESULT_OK){
               result.data?.data?.let { uri -> binding.imageView3.setImageURI(uri)
                   val test: Courses
               val options = BitmapFactory.Options()
               options.inSampleSize = 3
               val inputStream = requireActivity().contentResolver.openInputStream(uri)
                   val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
                   inputStream?.close()

                   val byteArrayOutputStream = ByteArrayOutputStream()
                   bitmap?.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)

                    byteArray = byteArrayOutputStream.toByteArray()
               }
           }
        }
}


