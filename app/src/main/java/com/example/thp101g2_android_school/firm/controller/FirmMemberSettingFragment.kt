package com.example.thp101g2_android_school.firm.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.databinding.FragmentFirmMemberSettingBinding
import com.example.thp101g2_android_school.firm.model.Firm
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

            btFirmMemberDataEdi.setOnClickListener {
                AlertDialog.Builder(view.context)
                    .setMessage("確定編輯?")
                    .setPositiveButton("是") { _, _ ->
                        val finished: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

                        var currentFirm: Firm? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/firms", "OPTIONS")
                        val FNO = currentFirm?.firmNo

                        requestTask<Unit>("$url/firmData/$FNO", "PUT", viewModel?.firm?.value)
                        Navigation.findNavController(view).popBackStack()
                        finished.value = true
                        if (finished.value == true) {
                            Toast.makeText(context, "編輯成功", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .setNegativeButton("否", null)
                    .setCancelable(false)
                    .show()
            }
        }
    }

}