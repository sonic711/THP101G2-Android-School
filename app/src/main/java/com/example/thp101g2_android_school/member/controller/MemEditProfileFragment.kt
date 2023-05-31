package com.example.thp101g2_android_school.member.controller

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
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
import com.example.thp101g2_android_school.member.viewModel.MemEditProfileViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.FragmentMemEditProfileBinding
import com.example.thp101g2_android_school.member.viewModel.MemberViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.gson.JsonObject

class MemEditProfileFragment : Fragment() {
    private lateinit var binding: FragmentMemEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: MemberViewModel by viewModels()
        binding = FragmentMemEditProfileBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            viewModel?.initialize()
            btChangePic.setOnClickListener {
                val bottomSheetBehavior = BottomSheetBehavior.from(included.bottomSheet)
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
            val userId = viewModel?.member?.value?.userId?.trim()
            val intro = viewModel?.member?.value?.introduction?.trim()

            if (nickname?.length !in 1..10) {
                etNickname.error = "暱稱須為1-10字元"
                valid = false
            }
            if (userId?.length !in 5..15) {
                etUserId.error = "使用者ID須為5-15字元"
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
                result.data?.data?.let { uri ->
                    // TODO
                }
            }
        }

}