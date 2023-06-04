package com.example.thp101g2_android_school.member.controller

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.FragmentLoginBinding
import com.example.thp101g2_android_school.member.model.Member
import com.example.thp101g2_android_school.member.viewModel.LoginViewModel
import com.example.thp101g2_android_school.member.viewModel.MemberViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: LoginViewModel by viewModels()
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            tvForgetPassword.setOnClickListener {
                forgetPasswordOnClick(it)
            }
            btLogin.setOnClickListener {
                if (!inputValid()) {
                    return@setOnClickListener
                }
                val email = viewModel!!.member.value!!.memberEmail.trim()
                val password = viewModel!!.member.value!!.password.trim()

                val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/members"
                val respBody =
                    requestTask<Member>("$url/$email/$password")
                if (respBody?.memberNo != null) {
                    val intent = Intent(requireContext(), MainActivity::class.java)
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
            val email = viewModel?.member?.value?.memberEmail?.trim()
            val password = viewModel?.member?.value?.password?.trim()
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

    // FIXME
    // textView: forgetPassword 的點擊事件函式
    private fun forgetPasswordOnClick(view: View) {
        Toast.makeText(requireContext(), "ForgetPassword Clicked", Toast.LENGTH_SHORT).show()
    }

}