package com.example.thp101g2_android_school.member.controller

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.thp101g2_android_school.FirmMainActivity
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.FragmentLoginFirmBinding
import com.example.thp101g2_android_school.firm.model.Firm
import com.example.thp101g2_android_school.member.viewModel.LoginViewModel

class LoginFirmFragment : Fragment() {
    private lateinit var binding: FragmentLoginFirmBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: LoginViewModel by viewModels()
        binding = FragmentLoginFirmBinding.inflate(inflater, container, false)
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
                val email = viewModel!!.firm.value!!.firmEmail!!.trim()
                val password = viewModel!!.firm.value!!.password!!.trim()

                val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/firms"
                val respBody =
                    requestTask<Firm>("$url/$email/$password")
                if (respBody?.firmNo != null) {
                    val intent = Intent(requireContext(), FirmMainActivity::class.java)
                    intent.putExtra("type", "user")
                    startActivity(intent)
                } else {
                    etEmail.error = "信箱或密碼錯誤"
                    etPassword.error = "信箱或密碼錯誤"
                }
            }
        }

    }

    private fun inputValid(): Boolean {
        var valid = true
        with(binding) {
            val email = viewModel?.firm?.value?.firmEmail?.trim()
            val password = viewModel?.firm?.value?.password?.trim()
            if (email == null || email.isEmpty()) {
                etEmail.error = "不可為空"
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