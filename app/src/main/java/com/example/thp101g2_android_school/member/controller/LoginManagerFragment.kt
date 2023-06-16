package com.example.thp101g2_android_school.member.controller

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.thp101g2_android_school.ManageMainActivity
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.FragmentLoginManagerBinding
import com.example.thp101g2_android_school.manage.model.Manager
import com.example.thp101g2_android_school.member.viewModel.LoginViewModel

class LoginManagerFragment : Fragment() {
    private lateinit var binding: FragmentLoginManagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: LoginViewModel by viewModels()
        binding = FragmentLoginManagerBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            btLogin.setOnClickListener {
                if (!inputValid()) {
                    return@setOnClickListener
                }
                val ac = viewModel?.manageAc?.value?.trim()
                val password = viewModel?.managePass?.value?.trim()

                val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/managers"
                val respBody =
                    requestTask<Manager>("$url/$ac/$password")
                println(respBody)
                if (respBody?.manageId != null) {
                    val intent = Intent(requireContext(), ManageMainActivity::class.java)
                    intent.putExtra("type", respBody)
                    startActivity(intent)
                } else {
                    etManageAc.error = "信箱或密碼錯誤"
                    etPassword.error = "信箱或密碼錯誤"
                }
            }
        }

    }

    private fun inputValid(): Boolean {
        var valid = true
        with(binding) {
            val ac = viewModel?.manageAc?.value?.trim()
            val password = viewModel?.managePass?.value?.trim()
            if (ac == null || ac.isEmpty()) {
                etManageAc.error = "不可為空"
                valid = false
            }
            if (password == null || password.isEmpty()) {
                etPassword.error = "不可為空"
                valid = false
            }
        }
        return valid
    }



}