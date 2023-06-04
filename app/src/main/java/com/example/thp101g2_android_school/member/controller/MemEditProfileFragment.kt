package com.example.thp101g2_android_school.member.controller

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.ActivityViewModel
import com.example.thp101g2_android_school.member.viewModel.MemEditProfileViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.FragmentMemEditProfileBinding
import com.example.thp101g2_android_school.member.viewModel.MemberViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.gson.JsonObject
import com.yalantis.ucrop.UCrop
import java.io.File

class MemEditProfileFragment : Fragment() {
    private lateinit var binding: FragmentMemEditProfileBinding
    private lateinit var imageUris: MutableList<Uri>
    private val viewModel: MemberViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageUris = mutableListOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMemEditProfileBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            viewModel?.initialize()
            val bottomSheetBehavior = BottomSheetBehavior.from(included.bottomSheet)
            if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
            btChangePic.setOnClickListener {
                if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
            included.tvProfilePhoto.setOnClickListener {
                Toast.makeText(requireContext(), "pick1", Toast.LENGTH_SHORT).show()
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                pickPictureLauncher.launch(intent)

            }

            included.tvCoverPicture.setOnClickListener {
                Toast.makeText(requireContext(), "pick2", Toast.LENGTH_SHORT).show()
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                pickPictureLauncher2.launch(intent)
            }
            btSubmit.setOnClickListener {
                if (!inputValid()) {
                    return@setOnClickListener
                }
                AlertDialog.Builder(view.context)
                    .setMessage("確定更改?")
                    .setPositiveButton("是") {_,_ ->
                        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/members"
                        val respBody = requestTask<JsonObject>(url, "PUT", viewModel!!.member.value!!)
                        Navigation.findNavController(view).popBackStack()
                    }
                    .setNegativeButton("否", null)
                    .setCancelable(false)
                    .show()
            }
        }
    }

    private fun inputValid(): Boolean {
        var valid = true
        with(binding) {
            val nickname = viewModel?.member?.value?.nickname?.trim()
            val intro = viewModel?.member?.value?.introduction?.trim()

            if (nickname?.length !in 1..10) {
                etNickname.error = "暱稱須為1-10字元"
                valid = false
            }
            if (intro?.length!! >= 20) {
                etIntroduction.error = "介紹不可超過20字元"
                valid = false
            }
        }
        return valid
    }

    private var pickPictureLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri -> crop(uri) }
            }
        }

    private fun crop(sourceImageUri: Uri) {
        val file = File(requireContext().getExternalFilesDir(null), "picture_cropped.jpg")
        val destinationUri = Uri.fromFile(file)
        val cropIntent: Intent = UCrop.of(sourceImageUri, destinationUri)
            .getIntent(requireContext())
        cropPictureLauncher.launch(cropIntent)
    }

    private var cropPictureLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let { intent ->
                    UCrop.getOutput(intent)?.let { uri -> binding.ivProfilePic.setImageURI(uri) }
                }
            }
        }

    private var pickPictureLauncher2 =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri -> crop2(uri) }
            }
        }

    private fun crop2(sourceImageUri: Uri) {
        val file = File(requireContext().getExternalFilesDir(null), "picture_cropped.jpg")
        val destinationUri = Uri.fromFile(file)
        val cropIntent: Intent = UCrop.of(sourceImageUri, destinationUri)
            .getIntent(requireContext())
        cropPictureLauncher2.launch(cropIntent)
    }

    private var cropPictureLauncher2 =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let { intent ->
                    UCrop.getOutput(intent)?.let { uri -> binding.ivCoverPho.setImageURI(uri) }
                }
            }
        }

}