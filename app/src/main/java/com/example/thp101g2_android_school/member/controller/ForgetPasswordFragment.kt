package com.example.thp101g2_android_school.member.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.member.viewModel.ForgetPasswordViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentForgetPasswordBinding

class ForgetPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: ForgetPasswordViewModel by viewModels()
        binding = FragmentForgetPasswordBinding.inflate(inflater, container, false)
        return binding.root
        //FIXME binding.viewModel記得設定
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            tvSmallLogin.setOnClickListener {
                smallLoginOnClick(it)
            }
        }
    }

    private fun smallLoginOnClick(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_loginMainFragment_to_registerFragment)
    }

}