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
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.course.model.Chapter
import com.example.thp101g2_android_school.course.model.Comment
import com.example.thp101g2_android_school.course.model.Courses
import com.example.thp101g2_android_school.course.viewmodel.CouRateViewModel
import com.example.thp101g2_android_school.databinding.FragmentCouRateBinding
import com.example.thp101g2_android_school.member.model.Member
import com.google.gson.JsonObject
import java.io.ByteArrayOutputStream

//之後需要寫把資料傳送到後端
class CouRateFragment : Fragment() {

    private lateinit var binding: FragmentCouRateBinding
    private val viewModel: CouRateViewModel by viewModels()
    private lateinit var byteArray: ByteArray

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCouRateBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { bundle ->
            bundle.getSerializable("roomcourse")?.let {
                binding.viewModel?.rates?.value = it as Comment
            }
        }
        with(binding) {

//                btChoose.setOnClickListener {
//                    val intent = Intent(
//                        Intent.ACTION_PICK,
//                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//                    )
//                    PictureLauncher.launch(intent)
//                }

            binding.btnSubmit.setOnClickListener {
                val member: Member? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/members", "OPTIONS")
                var memberNo = member?.memberNo
                if (viewModel?.point?.value != null) {
                    if (viewModel?.point?.value!! < 0.toString() || viewModel?.point?.value!! > 5.toString()) {
                        Toast.makeText(requireContext(), "請輸入有效的評分（1-5）", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    } else {
                        val test = Comment(
                            rating = viewModel?.point?.value!!,
                            comment = viewModel?.comment?.value!!,
                            memberNo = memberNo
                            //image = byteArray
                        )
                        //串會員要把資料加進去

                        requestTask<JsonObject>(
                            "http://10.0.2.2:8080/THP101G2-WebServer-School/comment/",
                            method = "POST",
                            reqBody = test
                        )

                        Navigation.findNavController(it)
                            .navigate(R.id.action_couRateFragment_to_couRatingFragment)
                    }
                }
//                } else {
//                    val test = Comment(
//                        rating = viewModel?.point?.value!!,
//                        comment = viewModel?.comment?.value!!,
//                        //image = byteArray
//                    )
//                    //串會員要把資料加進去
//
//                    requestTask<JsonObject>(
//                        "http://10.0.2.2:8080/THP101G2-WebServer-School/comment/",
//                        method = "POST",
//                        reqBody = test
//                    )
//
//                    Navigation.findNavController(it)
//                        .navigate(R.id.action_couRateFragment_to_couRatingFragment)
//                }


            //} else {
//                    val test = Comment(
//                        rating = viewModel?.point?.value!!,
//                        comment = viewModel?.comment?.value!!,
//                        //image = byteArray
//                    )
//                    //串會員要把資料加進去
//
//                    requestTask<JsonObject>(
//                        "http://10.0.2.2:8080/THP101G2-WebServer-School/comment/",
//                        method = "POST",
//                        reqBody = test
//                    )
//
//                    Navigation.findNavController(it)
//                        .navigate(R.id.action_couRateFragment_to_couRatingFragment)
//                }
        }
    }
}

//    private var PictureLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
//            if (result.resultCode == Activity.RESULT_OK){
//                result.data?.data?.let { uri -> binding.imageView4.setImageURI(uri)
//                    val test: Courses
//                    val options = BitmapFactory.Options()
//                    options.inSampleSize = 8
//                    val inputStream = requireActivity().contentResolver.openInputStream(uri)
//                    val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
//                    inputStream?.close()
//
//                    val byteArrayOutputStream = ByteArrayOutputStream()
//                    bitmap?.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)
//
//                    byteArray = byteArrayOutputStream.toByteArray()
//                }
//            }
//        }

}
//    }
//}
