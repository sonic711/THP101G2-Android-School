package com.example.thp101g2_android_school.manage.controller

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.databinding.FragmentManageMemberDetailBinding
import com.example.thp101g2_android_school.manage.model.Members
import com.example.thp101g2_android_school.manage.model.SelectMemberBean
import com.example.thp101g2_android_school.manage.model.TeaApplyBean
import com.example.thp101g2_android_school.manage.viewmodel.ManageMemberViewModel

class ManageMemberDetailFragment : Fragment() {

    private lateinit var binding: FragmentManageMemberDetailBinding
    private lateinit var spinner: Spinner


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageMemberDetailBinding.inflate(inflater, container, false)
        //多了底下這兩串
        val viewModel = ManageMemberViewModel()
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
                binding.viewModel?.memberuse?.value = it as SelectMemberBean
            }
        }
//        spinner = binding.Spinner
//        val options = listOf("下架", "解除")
//        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, options)
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner.adapter = spinnerAdapter
//
//        // 设置Spinner的选择监听器
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                val selectedItem = parent.getItemAtPosition(position) as String
//                // 根据选择的选项执行相应的操作
//                when (selectedItem) {
//                    "下架" -> {
//                        courseReportBean.manageResult = true
//                    }
//                    "解除" -> {
//                        courseReportBean.manageResult = false
//                    }
//                }
//            }
    }


    }
