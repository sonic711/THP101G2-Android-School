package com.example.thp101g2_android_school.member.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentRegisterPhoneBinding
import com.example.thp101g2_android_school.member.model.Member
import com.example.thp101g2_android_school.member.viewModel.RegisterViewModel

class RegisterPhoneFragment : Fragment() {

    private lateinit var binding: FragmentRegisterPhoneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: RegisterViewModel by viewModels()
        binding = FragmentRegisterPhoneBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btGetCaptcha.setOnClickListener {
                if (!inputValid()) {
                    return@setOnClickListener
                }
                val newBundle = Bundle()
                arguments?.let {bundle ->
                    val member = bundle.getSerializable("member") as Member?
                    member?.let { member ->
                        member.phoneNumber = viewModel!!.member.value!!.phoneNumber.trim()
                    }
                    newBundle.putSerializable("member", member)
                }
                Navigation.findNavController(it).navigate(R.id.registerVerificationFragment, newBundle)
            }
            ivBack.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
        }
    }

    private fun inputValid(): Boolean {
        var valid = true
        val phoneRegex = Regex("^09\\d{8}\$")
        with(binding) {
            val phone = viewModel?.member?.value?.phoneNumber?.trim()
            if (phone?.length != 10 || !phone.matches(phoneRegex)) {
                etPhone.error = "手機格式不正確"
                valid = false
            }
        }
        return valid
    }


}