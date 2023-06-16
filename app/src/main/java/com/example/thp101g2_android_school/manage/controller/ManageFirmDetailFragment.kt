package com.example.thp101g2_android_school.manage.controller

import com.example.thp101g2_android_school.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.ManageMainActivity
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.FragmentManageFirmDetailBinding
import com.example.thp101g2_android_school.manage.model.Firms
import com.example.thp101g2_android_school.manage.viewmodel.ManageFirmViewModel
import com.google.gson.JsonObject

class ManageFirmDetailFragment : Fragment() {
    private lateinit var binding: FragmentManageFirmDetailBinding
    private lateinit var spinner: Spinner
    private lateinit var shop: Firms
    private var selectedOption: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageFirmDetailBinding.inflate(inflater, container, false)
        val viewModel: ManageFirmViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.BackShop.setOnClickListener {
            Navigation.findNavController(requireView()).navigateUp()
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { bundle ->
            bundle.getSerializable("firms")?.let {
                shop = it as Firms
                binding.viewModel?.firmo?.value = shop
            }
        }

        val defaultOption = "請選擇下架或解除"
        selectedOption = null

        val viewModel = ViewModelProvider(this).get(ManageFirmViewModel::class.java)
        binding.viewModel = viewModel
        spinner = binding.MemberSpinner
        val options = listOf("下架", "解除")
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
            if (selectedOption != null && selectedOption != defaultOption) {
                val shopStatus = JsonObject()
                shopStatus.addProperty("shopProductId", viewModel.firmo.value?.shopProductId)
                when (selectedOption) {
                    "下架" -> {
                        shop.shopProductStatus = selectedOption
                        // TODO viewModel.upadte(selectMemberBean.memberStatus)
                        shopStatus.addProperty("shopProductStatus", 2)
                        requestTask<JsonObject>(
                            "http://10.0.2.2:8080/THP101G2-WebServer-School/firmdrop",
                            method = "PUT",
                            reqBody = shopStatus
                        )
                        Navigation.findNavController(it)
                            .navigate(R.id.action_manageFirmDetailFragment_to_manageHomeFragment)
                    }
                    "解除" -> {
                        shop.shopProductStatus = selectedOption
                        shopStatus.addProperty("shopProductStatus", 0)
                        requestTask<JsonObject>(
                            "http://10.0.2.2:8080/THP101G2-WebServer-School/firmdrop",
                            method = "PUT",
                            reqBody = shopStatus
                        )
                        Navigation.findNavController(it)
                            .navigate(R.id.action_manageFirmDetailFragment_to_manageHomeFragment)
                    }
                }
            } else {
                Toast.makeText(requireContext(), "請選擇封鎖或解除，再次點擊送出", Toast.LENGTH_SHORT).show()
            }

            Toast.makeText(view.context, "已更改商品狀態", Toast.LENGTH_SHORT).show()
        }
        binding.btfirmGo.setOnClickListener {
            val firmData = binding.viewModel?.firmo?.value
            val bundle = Bundle()
            bundle.putSerializable("firmtoshop", firmData)
            Navigation.findNavController(requireView())
                .navigate(
                    R.id.action_manageFirmDetailFragment_to_manageShopDetailFragment2,
                    bundle
                )
        }
    }
}
