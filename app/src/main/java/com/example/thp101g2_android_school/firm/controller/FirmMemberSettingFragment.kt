package com.example.thp101g2_android_school.firm.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.databinding.FragmentFirmMemberSettingBinding

class FirmMemberSettingFragment : Fragment() {
    private lateinit var binding : FragmentFirmMemberSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirmMemberSettingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            ibMemberSettinToBack.setOnClickListener{
                Navigation.findNavController(view).popBackStack()
            }
        }
    }

}