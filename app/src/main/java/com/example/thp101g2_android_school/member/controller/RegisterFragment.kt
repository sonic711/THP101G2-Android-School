package com.example.thp101g2_android_school.member.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentRegisterBinding
import com.example.thp101g2_android_school.member.model.Member
import com.example.thp101g2_android_school.member.viewModel.RegisterViewModel

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: RegisterViewModel by viewModels()
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            btRegister.setOnClickListener {
                if (!inputValid()) {
                    return@setOnClickListener
                }
                val member = Member(
                    nickname = viewModel!!.member.value!!.nickname.trim(),
                    userId = viewModel!!.member.value!!.userId.trim(),
                    memberEmail = viewModel!!.member.value!!.memberEmail.trim(),
                    password = viewModel!!.member.value!!.password.trim()
                )

                val bundle = Bundle()
                bundle.putSerializable("member", member)

                Navigation.findNavController(it).navigate(R.id.registerPhoneFragment, bundle)
            }

            tvHaveAccount.setOnClickListener {
                haveAccountOnClick(it)
            }
        }
    }

    private fun inputValid(): Boolean {
        var valid = true
        with(binding) {
            val nickname = viewModel?.member?.value?.nickname?.trim()
            val userId = viewModel?.member?.value?.userId?.trim()
            val email = viewModel?.member?.value?.memberEmail?.trim()
            val password = viewModel?.member?.value?.password?.trim()
            val cPassword = viewModel?.cPassword?.value?.trim()

            if (nickname?.length !in 1..10) {
                etNickname.error = "暱稱須為1-10字元"
                valid = false
            }
            if (userId?.length !in 5..15) {
                etUserId.error = "使用者ID須為5-15字元"
                valid = false
            }
            if (email == null || email.isEmpty()) {
                etEmail.error = "信箱不能為空"
                valid = false
            }
            if (password?.length !in 6..12) {
                etPassword.error = "密碼須為6-12字元"
                valid = false
            }
            if (cPassword != password) {
                etConPassword.error = "與密碼不相符"
                valid = false
            }
        }
        return valid
    }


    private fun haveAccountOnClick(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
    }


}