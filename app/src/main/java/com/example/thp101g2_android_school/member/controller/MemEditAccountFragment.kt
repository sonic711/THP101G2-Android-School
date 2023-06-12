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
import com.example.thp101g2_android_school.databinding.FragmentMemEditAccountBinding
import com.example.thp101g2_android_school.member.viewModel.MemSettingViewModel
import com.google.gson.JsonObject

class MemEditAccountFragment : Fragment() {
    private lateinit var binding: FragmentMemEditAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: MemSettingViewModel by viewModels()
        binding = FragmentMemEditAccountBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as MainActivity).supportActionBar?.show()
        activity?.setTitle("帳號設定")
        with(binding) {
            viewModel?.initialize()
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

            if (nickname?.length !in 1..10) {
                etNickname.error = "暱稱須為1-10字元"
                valid = false
            }
        }
        return valid
    }

}