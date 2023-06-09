package com.example.thp101g2_android_school.manage.controller

import android.R
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.ManageMainActivity
import com.example.thp101g2_android_school.databinding.FragmentManageMaSettingBinding
import com.example.thp101g2_android_school.manage.viewmodel.ManageMaSettingViewModel
class ManageMaSettingFragment : Fragment() {
    private lateinit var binding: FragmentManageMaSettingBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ManageMaAdapter
    private lateinit var spinner: Spinner

    private val viewModel: ManageMaSettingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageMaSettingBinding.inflate(inflater, container, false)
        binding.Back.setOnClickListener {
            Navigation.findNavController(requireView()).navigateUp()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as ManageMainActivity).supportActionBar?.hide()

//        // 初始化RecyclerView和适配器
//        recyclerView = binding.recyclerView
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        adapter = ManageMaAdapter(emptyList())
//        recyclerView.adapter = adapter


        spinner = binding.spinner
        val options = listOf("新增管理者", "刪除管理者")
        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, options)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position) as String
                when (selectedItem) {
                    "新增管理者" -> {
                        // 执行新增管理者操作
                    }
                    "刪除管理者" -> {
                        // 执行刪除管理者操作
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // 未选择任何选项时的操作
            }
        }


    }
}