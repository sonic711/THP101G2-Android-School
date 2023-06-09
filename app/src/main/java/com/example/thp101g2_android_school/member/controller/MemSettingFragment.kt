package com.example.thp101g2_android_school.member.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.member.viewModel.MemSettingViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentMemSettingBinding

class MemSettingFragment : Fragment() {
    private lateinit var binding: FragmentMemSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMemSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.show()
        activity?.setTitle("帳號設定")
        with(binding) {
            llAccountSetting.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_memSettingFragment_to_memEditAccountFragment)
            }
            llNotificationSetting.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_memSettingFragment_to_memNotificationSettingFragment)
            }
            llEditPasswordSetting.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_memSettingFragment_to_memEditPasswordFragment)
            }
            llApplyToTeacher.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_memSettingFragment_to_memApplyToTeacherFragment)
            }
        }
    }

}