package com.example.thp101g2_android_school.member.controller

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.FragmentMemApplyToTeacherBinding
import com.example.thp101g2_android_school.member.viewModel.MemSettingViewModel
import com.google.gson.JsonObject

class MemApplyToTeacherFragment : Fragment() {
    private lateinit var binding: FragmentMemApplyToTeacherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: MemSettingViewModel by viewModels()
        binding = FragmentMemApplyToTeacherBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as MainActivity).supportActionBar?.show()
        activity?.setTitle("申請教師")

        with(binding) {
            scApplyToTeacher.setOnClickListener {
                if (scApplyToTeacher.isChecked) {
                    llReason.visibility = View.VISIBLE
                } else {
                    llReason.visibility = View.INVISIBLE
                }
            }

            // FIXME 目前只要送出申請就成為教師
            btSubmit.setOnClickListener {
                val reason = viewModel?.reason?.value?.trim()
                if (reason.isNullOrEmpty()) {
                    etReason.error = "不可為空"
                    return@setOnClickListener
                }

                AlertDialog.Builder(view.context)
                    .setMessage("確定送出申請?")
                    .setPositiveButton("是") {_,_ ->
                        viewModel?.member?.value?.memberIdentity = "教師"
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

}