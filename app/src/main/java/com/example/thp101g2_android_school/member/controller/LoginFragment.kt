package com.example.thp101g2_android_school.member.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.thp101g2_android_school.databinding.FragmentLoginBinding
import com.example.thp101g2_android_school.member.viewModel.LoginViewModel

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
                forgetPasswordOnClick(view)

            }
        }
    }

// FIXME
    // textView: forgetPassword 的點擊事件函式
    fun forgetPasswordOnClick(view: View) {
        Toast.makeText(requireContext(), "TextView Clicked", Toast.LENGTH_SHORT).show()
    }

}