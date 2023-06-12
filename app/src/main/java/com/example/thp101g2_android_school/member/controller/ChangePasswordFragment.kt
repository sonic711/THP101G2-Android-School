package com.example.thp101g2_android_school.member.controller

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.member.viewModel.ChangePasswordViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.FragmentChangePasswordBinding
import com.example.thp101g2_android_school.member.viewModel.ForgetPasswordViewModel
import com.google.gson.JsonObject
import org.mindrot.jbcrypt.BCrypt

class ChangePasswordFragment : Fragment() {
    private lateinit var binding: FragmentChangePasswordBinding
    private val viewModel: ForgetPasswordViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangePasswordBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            btSubmit.setOnClickListener {
                if (!inputValid()) {
                    return@setOnClickListener
                }
                savePreference()
                if (viewModel?.changePassword() == true) {
                    Log.d("TAG_ChangePassword", "result: ${viewModel?.changePassword()}")
                    Navigation.findNavController(it).popBackStack(R.id.loginFragment, false)
                } else {
                    Toast.makeText(requireContext(), "編輯失敗", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun inputValid(): Boolean {
        var valid = true
        with(binding) {
            val nPassword = viewModel?.nPassword?.value?.trim()
            val cPassword = viewModel?.cPassword?.value?.trim()

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

    private fun savePreference() {
        requireActivity().getSharedPreferences("memberAccount", Context.MODE_PRIVATE).edit()
            .putString("password", binding.viewModel?.nPassword?.value?.trim())
            .apply()
        Toast.makeText(requireContext(), "data saved", Toast.LENGTH_SHORT).show()
    }

}