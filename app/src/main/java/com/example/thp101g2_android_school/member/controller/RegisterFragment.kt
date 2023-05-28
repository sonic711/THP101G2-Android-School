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
                Navigation.findNavController(it).navigate(R.id.action_registerFragment_to_registerPhoneFragment)
            }

            tvHaveAccount.setOnClickListener {
                haveAccountOnClick(it)
            }
        }
    }


    private fun haveAccountOnClick(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
    }


}