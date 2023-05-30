package com.example.thp101g2_android_school.member.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.LoginMainActivity
import com.example.thp101g2_android_school.member.viewModel.LoginMainViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentLoginMainBinding

class LoginMainFragment : Fragment() {
    private lateinit var binding: FragmentLoginMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as LoginMainActivity).supportActionBar?.hide()
        val viewModel: LoginMainViewModel by viewModels()
        binding = FragmentLoginMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btFirm.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.registerFragment)
            }
            btStudent.setOnClickListener {
                Log.d("test", "123")
                Navigation.findNavController(it).navigate(R.id.registerFragment)
            }
        }
    }

}