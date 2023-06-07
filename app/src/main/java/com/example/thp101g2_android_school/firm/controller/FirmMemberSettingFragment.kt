package com.example.thp101g2_android_school.firm.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.databinding.FragmentFirmMemberSettingBinding
import com.example.thp101g2_android_school.firm.viewmodel.FirmShopDataViewModel
import com.example.thp101g2_android_school.firm.viewmodel.FirmShopSettingViewModel

class FirmMemberSettingFragment : Fragment() {
    private lateinit var binding : FragmentFirmMemberSettingBinding
    private val viewModel: FirmShopSettingViewModel by viewModels { requireParentFragment().defaultViewModelProviderFactory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel : FirmShopSettingViewModel by viewModels()
        binding = FragmentFirmMemberSettingBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            viewModel?.init()
            println(viewModel?.firm?.value)
            ibMemberSettinToBack.setOnClickListener{
                Navigation.findNavController(view).popBackStack()
            }
        }
    }

}