package com.example.thp101g2_android_school.firm.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentFirmSettingBinding
import com.example.thp101g2_android_school.firm.viewmodel.FirmShopSettingViewModel

class FirmSettingFragment : Fragment() {
    private lateinit var binding : FragmentFirmSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewMode : FirmShopSettingViewModel by viewModels()
        binding = FragmentFirmSettingBinding.inflate(inflater,container,false)
        binding.viewModel = viewMode
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            println(viewModel?.firm?.value)
            ibFirmGoToShopSetting.setOnClickListener{
                Navigation.findNavController(it).navigate(R.id.firmShopSettingFragment)
            }
            ibFirmGoToMemberSetting.setOnClickListener{
                Navigation.findNavController(it).navigate(R.id.firmMemberSettingFragment)
            }
            ibFirmGoToPasswordSetting.setOnClickListener{
                Navigation.findNavController(it).navigate(R.id.firmFixedPasswordFragment)
            }


        }
    }

}