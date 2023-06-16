package com.example.thp101g2_android_school.manage.controller

import com.example.thp101g2_android_school.R
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.ManageMainActivity
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.FragmentManageMaSettingBinding
import com.example.thp101g2_android_school.manage.model.ManageAccountBean
import com.example.thp101g2_android_school.manage.model.ManagePerBean
import com.example.thp101g2_android_school.manage.model.SelectMemberBean
import com.example.thp101g2_android_school.manage.viewmodel.ManageMaSettingViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageMaViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageMemberViewModel
import com.google.gson.JsonObject

class ManageMaSettingFragment : Fragment() {
    private lateinit var binding: FragmentManageMaSettingBinding
    private lateinit var managePerBean: ManagePerBean

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageMaSettingBinding.inflate(inflater, container, false)
        val viewModel: ManageMaViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.Back.setOnClickListener {
            Navigation.findNavController(requireView()).navigateUp()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            btCheck.setOnClickListener {
                var status = true
                // 帳號為空?
                viewModel?.mao?.value?.run {
                    if (manageAc .isNullOrBlank()) {
                        etManageAC.error = "請輸入帳號"
                        status = false

                    }
                    if (managePass.isNullOrBlank()) {
                        etManagePassword.error = "請輸入密碼"
                        status = false

                    }
                    if (manageName.isNullOrBlank()) {
                        etManageName.error = "請輸入姓名"
                        status = false
                    }
                    if (status) {
                        println("viewModel.mao.value = ${viewModel?.mao?.value}")
                        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/manageaccount"
                        val method = "POST"
                        requestTask<JsonObject>(url, method = method, reqBody = viewModel?.mao?.value)
                        findNavController().popBackStack()
                    }
                }
            }
        }

    }
}
