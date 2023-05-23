package com.example.thp101g2_android_school.firm.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.databinding.FragmentFirmShopSettingBinding

class FirmShopSettingFragment : Fragment() {
    private lateinit var binding : FragmentFirmShopSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirmShopSettingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            ibShopSettingToBack.setOnClickListener{
                Navigation.findNavController(view).popBackStack()
            }
        }
    }

}