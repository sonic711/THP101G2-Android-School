package com.example.thp101g2_android_school.member.controller

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.FragmentRegisterVerificationBinding
import com.example.thp101g2_android_school.member.model.Member
import com.example.thp101g2_android_school.member.viewModel.RegisterViewModel
import com.google.gson.JsonObject

class RegisterVerificationFragment : Fragment() {
    private lateinit var binding: FragmentRegisterVerificationBinding
    private val myTag = "TAG_${javaClass.simpleName}"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewMode: RegisterViewModel by viewModels()
        binding = FragmentRegisterVerificationBinding.inflate(inflater, container, false)
        binding.viewModel = viewMode
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            var member = Member()
            arguments?.let { bundle ->
                member = bundle.getSerializable("member") as Member
                viewModel?.member?.value?.phoneNumber = member.phoneNumber
            }
            Log.d(myTag, "member: $member")
            btSubmit.setOnClickListener {
                if (viewModel?.captcha?.value == null || viewModel?.captcha?.value!!.isEmpty()) {
                    return@setOnClickListener
                }
                if (viewModel!!.captcha.value!!.length != 4) {
                    etVerification.error = "驗證碼錯誤"
                }

                val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/members"
                val respBody =
                    requestTask<JsonObject>(url, "POST", member)
                Log.d(myTag, respBody.toString())

                if (respBody?.get("successful")?.asBoolean == true) {
                    Toast.makeText(requireContext(), "註冊成功", Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(it).popBackStack(R.id.loginFragment, false)
                } else {
                    Toast.makeText(requireContext(), "註冊失敗", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

}