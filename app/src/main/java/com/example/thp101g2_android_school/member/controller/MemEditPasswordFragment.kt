package com.example.thp101g2_android_school.member.controller

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.member.viewModel.MemEditPasswordViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.FragmentMemEditPasswordBinding
import com.example.thp101g2_android_school.databinding.FragmentMemEditProfileBinding
import com.example.thp101g2_android_school.member.viewModel.MemSettingViewModel
import com.google.gson.JsonObject

class MemEditPasswordFragment : Fragment() {
    private lateinit var binding: FragmentMemEditPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: MemSettingViewModel by viewModels()
        binding = FragmentMemEditPasswordBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as MainActivity).supportActionBar?.show()
        activity?.setTitle("修改密碼")

        with(binding) {
            viewModel?.initialize()

            btSubmit.setOnClickListener {
                if (!inputValid()) {
                    return@setOnClickListener
                }

                AlertDialog.Builder(view.context)
                    .setMessage("確定更改?")
                    .setPositiveButton("是") {_,_ ->
                        viewModel?.member?.value?.password = viewModel?.nPassword?.value.toString()
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
            val oPassword = viewModel?.oPassword?.value?.trim()
            val nPassword = viewModel?.nPassword?.value?.trim()
            val cPassword = viewModel?.cPassword?.value?.trim()

            if (oPassword != viewModel?.member?.value?.password?.trim()) {
                etOldPW.error = "密碼不正確"
                valid = false
            }
            if (nPassword?.length !in 6..12) {
                etNewPW.error = "密碼須為6-12字元"
                valid = false
            }
            if (cPassword != nPassword) {
                etConNewPW.error = "新密碼與確認密碼不相符"
                valid = false
            }
        }
        return valid
    }

}