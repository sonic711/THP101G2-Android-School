
package com.example.thp101g2_android_school.firm.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.databinding.FragmentFirmProductManagerBinding
import com.example.thp101g2_android_school.databinding.FragmentFirmShopSettingBinding
import com.example.thp101g2_android_school.firm.viewmodel.FirmDatasCenterViewModel
import com.example.thp101g2_android_school.firm.viewmodel.FirmProductManagerViewModel
import com.example.thp101g2_android_school.firm.viewmodel.FirmShopDataViewModel
import com.example.thp101g2_android_school.firm.viewmodel.FirmShopSettingViewModel

class FirmShopSettingFragment : Fragment() {
    private lateinit var binding:FragmentFirmShopSettingBinding
    private val viewModel: FirmShopSettingViewModel by viewModels { requireParentFragment().defaultViewModelProviderFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel : FirmShopSettingViewModel by viewModels()
        binding = FragmentFirmShopSettingBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            viewModel?.init()
            ibShopSettingToBack.setOnClickListener{
                Navigation.findNavController(it).popBackStack()
            }

        }
    }
}