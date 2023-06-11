package com.example.thp101g2_android_school.firm.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.FirmActivityViewModel
import com.example.thp101g2_android_school.FirmMainActivity
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.databinding.FragmentFirmFixedPasswordBinding
import com.example.thp101g2_android_school.firm.model.Firm
import com.example.thp101g2_android_school.firm.viewmodel.FirmFixedPasswordViewModel
import com.example.thp101g2_android_school.firm.viewmodel.FirmShopSettingViewModel

class FirmFixedPasswordFragment : Fragment() {
    private lateinit var binding: FragmentFirmFixedPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: FirmFixedPasswordViewModel by viewModels()
        binding = FragmentFirmFixedPasswordBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val firmId = (requireActivity() as FirmMainActivity).firmNo
        with(binding) {
            btFixedPasswordToBack.setOnClickListener {
                viewModel?.edit(firmId)
            }
            ibFixedPasswordToBack.setOnClickListener {
                Navigation.findNavController(view).popBackStack()
            }

        }
    }

}