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
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

import com.example.thp101g2_android_school.databinding.FragmentClassDetailBinding
import com.example.thp101g2_android_school.manage.model.CourseReportBean
import com.example.thp101g2_android_school.manage.model.Members
//import com.example.thp101g2_android_school.manage.model.Classes
import com.example.thp101g2_android_school.manage.viewmodel.ManageClassViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageMaSettingViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageMemberViewModel

class ManageClassDetailFragment : Fragment() {
    private lateinit var binding: FragmentClassDetailBinding
    private lateinit var spinner: Spinner
    private lateinit var courseReportBean: CourseReportBean
    private var selectedOption: String? = null

    private val viewModel: ManageClassViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClassDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        spinner = binding.CourseSpinner
        val options = listOf("下架", "解除")
        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, options)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter


        // 设置Spinner的选择监听器
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
                // 未选择任何项时的操作
            }
        }

//        binding.btCourseDown.setOnClickListener {
//            // 检查是否选取了 Spinner 的选项
//            if (selectedOption != null) {
//                // 根据选项值执行相应的操作
//                when (selectedOption) {
//                    "下架" -> {
//                        courseReportBean.manageResult = true
//                        viewModel.updateCourseReport(courseReportBean)
//                    }
//                    "解除" -> {
//                        courseReportBean.manageResult = false
//                        viewModel.updateCourseReport(courseReportBean)
//                    }
//                }
//            } else {
//                // 未选取选项时的操作
//            }
//        }

//            "下架" -> {
//                courseReportBean.manageResult = true
//            }
//            "解除" -> {
//                courseReportBean.manageResult = false
//            }


                // 未选择任何项时的操作


//            binding.ManageCourseupdown.setOnClickListener {
        //按下之後要傳送更改true or false 監督上面?
//            }


//        binding.ManageCourseupdown.setOnClickListener {
//            viewModel.updateCourseReport(courseReportBean)
//        }
        binding.Back.setOnClickListener {
        Navigation.findNavController(requireView()).navigateUp()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            bundle.getSerializable("class")?.let {
                courseReportBean = it as CourseReportBean
                binding.viewModel?.classo?.value = courseReportBean


            }
        }




    }
}




