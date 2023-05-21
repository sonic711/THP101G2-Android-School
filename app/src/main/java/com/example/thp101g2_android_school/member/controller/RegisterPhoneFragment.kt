package com.example.thp101g2_android_school.member.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentRegisterPhoneBinding
import com.example.thp101g2_android_school.member.viewModel.RegisterPhoneViewModel

class RegisterPhoneFragment : Fragment() {

    private lateinit var binding: FragmentRegisterPhoneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: RegisterPhoneViewModel by viewModels()
        binding = FragmentRegisterPhoneBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btGetCaptcha.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_registerPhoneFragment_to_registerVerificationFragment)
            }
            ivBack.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
        }
    }


}