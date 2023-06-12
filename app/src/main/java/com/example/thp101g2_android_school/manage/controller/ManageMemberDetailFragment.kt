package com.example.thp101g2_android_school.manage.controller

import com.example.thp101g2_android_school.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.FragmentManageMemberDetailBinding
import com.example.thp101g2_android_school.manage.model.SelectMemberBean
import com.example.thp101g2_android_school.manage.viewmodel.ManageMemberViewModel
import com.google.gson.JsonObject

class ManageMemberDetailFragment : Fragment() {
    private lateinit var binding: FragmentManageMemberDetailBinding
    private lateinit var spinner: Spinner
    private lateinit var selectMemberBean: SelectMemberBean
    private var selectedOption: String? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageMemberDetailBinding.inflate(inflater, container, false)
        val viewModel: ManageMemberViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.Back.setOnClickListener {
            Navigation.findNavController(requireView()).navigateUp()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { bundle ->
            bundle.getSerializable("member")?.let {
                selectMemberBean = it as SelectMemberBean
                binding.viewModel?.memberuse?.value = selectMemberBean
            }
        }

        val defaultOption = "請選擇封鎖或解除"
        selectedOption = null

        val viewModel = ViewModelProvider(this).get(ManageMemberViewModel::class.java)
        binding.viewModel = viewModel
        spinner = binding.MemberSpinner
        val options = listOf("封鎖", "解除")
        val spinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, options)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter
        // 設置Spinner的選擇監聽器
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedOption = parent.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // 未選擇任何項時的操作
                selectedOption = null
            }
        }

        binding.btmemberBlockCheck.setOnClickListener {
            // 檢查是否選取了Spinner的選項
            if (selectedOption != null && selectedOption != defaultOption) {
                // 根據選項值執行相應的操作
                val member = JsonObject()
                member.addProperty("memberNo", viewModel.memberuse.value?.memberNo)
                when (selectedOption) {
                    "封鎖" -> {
                        selectMemberBean.memberStatus = true
                        // TODO viewModel.upadte(selectMemberBean.memberStatus)
                        member.addProperty("memberStatus", 0)
                        requestTask<JsonObject>(
                            "http://10.0.2.2:8080/THP101G2-WebServer-School/coursereport",
                            method = "OPTIONS",
                            reqBody = member
                        )
                    }
                    "解除" -> {
                        selectMemberBean.memberStatus = false
                        member.addProperty("memberStatus", 1)
                        requestTask<JsonObject>(
                            "http://10.0.2.2:8080/THP101G2-WebServer-School/coursereport",
                            method = "OPTIONS",
                            reqBody = member
                        )
                    }
                }
            } else {
                // 可以顯示提示訊息或執行其他相應處理
                Toast.makeText(requireContext(), "請選擇封鎖或解除，再次點擊送出", Toast.LENGTH_SHORT).show()
            }

            Navigation.findNavController(it)
                .navigate(R.id.action_manageMemberDetailFragment_to_manageHomeFragment)
            Toast.makeText(view.context, "已更改會員狀態", Toast.LENGTH_SHORT).show()
        }
    }
}
