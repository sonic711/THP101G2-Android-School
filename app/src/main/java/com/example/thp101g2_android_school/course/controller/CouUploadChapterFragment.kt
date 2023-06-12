package com.example.thp101g2_android_school.course.controller

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.course.viewmodel.CouUploadChapterViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.course.model.Chapter
import com.example.thp101g2_android_school.course.model.UpChapter
import com.example.thp101g2_android_school.databinding.FragmentCouUploadChapterBinding
import com.example.thp101g2_android_school.member.model.Member
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.JsonObject
import com.yalantis.ucrop.UCrop
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class CouUploadChapterFragment : Fragment() {

    private lateinit var binding: FragmentCouUploadChapterBinding
    private  val viewModel: CouUploadChapterViewModel by viewModels()
    private var videoUrl: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCouUploadChapterBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            btchoose.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                )
                startActivityForResult(intent, REQUEST_VIDEO_PICK)
            }
            btUpV.setOnClickListener {
                val member: Member? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/members", "OPTIONS")
                var memberNo = member?.memberNo
                val test = UpChapter(
                    chapterName = viewModel?.name?.value!!,
                    chapterSequence = viewModel?.no?.value!!,
                    video = videoUrl!!
                )
                val insertResult = requestTask<JsonObject>(
                    "http://10.0.2.2:8080/THP101G2-WebServer-School/chapter/",
                    method = "POST",
                    reqBody = test
                )
                Navigation.findNavController(it)
                    .navigate(R.id.action_couUploadChapterFragment_to_couTeacherFragment)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_VIDEO_PICK && resultCode == Activity.RESULT_OK) {
            data?.data?.let { videoUri ->
                uploadVideo(videoUri)
            }
        }
    }

    private fun uploadVideo(videoUri: Uri) {
        val storageReference = FirebaseStorage.getInstance().reference
        val videoRef = storageReference.child("videos/${System.currentTimeMillis()}.mp4")

        val uploadTask = videoRef.putFile(videoUri)

        uploadTask.addOnSuccessListener { uploadTaskSnapshot ->
            videoRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                videoUrl = downloadUrl.toString()
                Toast.makeText(
                    requireContext(),
                    "影片選擇成功",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }.addOnFailureListener { exception ->
            // 上传失败
            // 处理上传失败的情况
            Toast.makeText(
                requireContext(),
                "影片上傳失敗",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    companion object {
        private const val REQUEST_VIDEO_PICK = 1
    }
}
