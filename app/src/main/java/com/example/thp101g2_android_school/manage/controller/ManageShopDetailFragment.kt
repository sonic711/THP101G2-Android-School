package com.example.thp101g2_android_school.manage.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.R
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
//import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.FragmentManageFirmDetailBinding
import com.example.thp101g2_android_school.databinding.FragmentManageShopDetailBinding
import com.example.thp101g2_android_school.manage.model.Firms
import com.example.thp101g2_android_school.manage.model.ManageAccountBean
import com.example.thp101g2_android_school.manage.viewmodel.ManageFirmViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageShopDetailViewModel
import com.google.gson.JsonObject
//layout是shop
class ManageShopDetailFragment : Fragment() {
    private  lateinit var binding:FragmentManageShopDetailBinding
    private lateinit var spinner: Spinner
    private lateinit var firmss: Firms
    private var selectedOption: String? = null

//    private lateinit var viewModel: ManageFirmsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageShopDetailBinding.inflate(inflater, container, false)
        val viewModel : ManageFirmViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.Back.setOnClickListener {
            Navigation.findNavController(requireView()).navigateUp()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { bundle ->
            bundle.getSerializable("firmtoshop")?.let {
                firmss = it as Firms
                binding.viewModel?.firmo?.value = firmss
            }
        }
        val defaultOption = "請選擇下架或解除"
        selectedOption = null

        val viewModel = ViewModelProvider(this).get(ManageFirmViewModel::class.java)
        binding.viewModel = viewModel
        spinner = binding.MemberSpinner
        val options = listOf("下架", "解除")
        val spinnerAdapter =
            ArrayAdapter(requireContext(),R.layout.simple_spinner_item, options)
        spinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        // 設置Spinner的選擇監聽器
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedOption = parent.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedOption = null
            }
        }

        binding.btmemberBlockCheck.setOnClickListener {
            if (selectedOption != null && selectedOption != defaultOption) {
                val firmStatus = JsonObject()
                firmStatus.addProperty("firmNo", viewModel.firmo.value?.firmNo)
                when (selectedOption) {
                    "下架" -> {
                        firmss.firmStatus = selectedOption
                        // TODO viewModel.upadte(selectMemberBean.memberStatus)
                        firmStatus.addProperty("firmStatus", 0)
                        requestTask<JsonObject>(
                            "http://10.0.2.2:8080/THP101G2-WebServer-School/firmdrop",
                            method = "PUT",
                            reqBody = firmStatus
                        )
                    }
                    "解除" -> {
                        firmss.firmStatus = selectedOption
                        firmStatus.addProperty("firmStatus", 1)
                        requestTask<JsonObject>(
                            "http://10.0.2.2:8080/THP101G2-WebServer-School/firmdrop",
                            method = "PUT",
                            reqBody = firmStatus
                        )
                    }
                }
            } else {
                // 可以顯示提示訊息或執行其他相應處理
                Toast.makeText(requireContext(), "請選擇封鎖或解除，再次點擊送出", Toast.LENGTH_SHORT).show()
            }

//            val test = Firms(
//                firmStatus = viewModel?.firmo?.value?.firmStatus!!
//            )
//            requestTask<JsonObject>(
//                "http://10.0.2.2:8080/THP101G2-WebServer-School/firmdrop/",
//                method = "PUT",
//                reqBody = test
//            )

//            Navigation.findNavController(it)
//                .navigate(R.id.action_manageMemberDetailFragment_to_manageHomeFragment)
            Toast.makeText(view.context, "已更改廠商狀態", Toast.LENGTH_SHORT).show()
        }
    }

}