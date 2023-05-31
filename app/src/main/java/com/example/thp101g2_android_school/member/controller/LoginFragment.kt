package com.example.thp101g2_android_school.member.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
        with(binding){
            tvForgetPassword.setOnClickListener {
                forgetPasswordOnClick(it)
            }
//            btLogin.setOnClickListener {
//                val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/member"
//                val respBody = requestTask<Member>("$url/${viewModel.member.value?.memberEmail}/${viewModel.member.value?.password}")
//            }
        }



    }

// FIXME
    // textView: forgetPassword 的點擊事件函式
    private fun forgetPasswordOnClick(view: View) {
        Toast.makeText(requireContext(), "ForgetPassword Clicked", Toast.LENGTH_SHORT).show()
    }

}