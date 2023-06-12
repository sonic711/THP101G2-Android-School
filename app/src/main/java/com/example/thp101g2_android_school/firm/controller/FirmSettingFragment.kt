package com.example.thp101g2_android_school.firm.controller

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.databinding.FragmentFirmSettingBinding
import com.example.thp101g2_android_school.firm.model.Firm
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

            btFirmSignOut.setOnClickListener {
                AlertDialog.Builder(view.context)
                    .setMessage("確定登出?")
                    .setPositiveButton("是") { _, _ ->
                        var currentFirm: Firm? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/firms", "OPTIONS")
                        val FNO = currentFirm?.firmNo
                        requestTask<Unit>("$url/firms/$FNO", "DELETE")
                        // TODO 導覽至登入畫面 loginMainFragment
                        Log.d("TAG_","LOGOUT:${Navigation.findNavController(view).currentDestination?.id}")
                        // Navigation.findNavController(view).navigate(R.id.loginFirmFragment)
                        requireActivity().finish()


                    }
                    .setNegativeButton("否", null)
                    .setCancelable(false)
                    .show()
            }
        }
    }
}