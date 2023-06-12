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
import com.example.thp101g2_android_school.databinding.FragmentClassDetailBinding
import com.example.thp101g2_android_school.manage.model.CourseReportBean
import com.example.thp101g2_android_school.manage.viewmodel.ManageClassViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageClassesViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageMemberViewModel
import com.google.gson.JsonObject

class ManageClassDetailFragment : Fragment() {
    private lateinit var binding: FragmentClassDetailBinding
    private lateinit var spinner: Spinner
    private lateinit var courseReportBean: CourseReportBean
    private var selectedOption: String? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: ManageClassViewModel by viewModels()
        binding = FragmentClassDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.Back.setOnClickListener {
            Navigation.findNavController(requireView()).navigateUp()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { bundle ->
            bundle.getSerializable("class")?.let {
                courseReportBean = it as CourseReportBean
                binding.viewModel?.classo?.value = courseReportBean
            }
        }

        val defaultOption = "請選擇下架或解除"
        selectedOption = null

        val viewModel = ViewModelProvider(this).get(ManageClassViewModel::class.java)
        binding.viewModel = viewModel
        spinner = binding.CourseSpinner
        val options = listOf("下架", "解除")
        val spinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, options)
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
                // 未選擇任何項時的操作
                selectedOption = null
            }
        }

        binding.btCourseDown.setOnClickListener {
            //  檢查是否選取了Spinner的選項
            if (selectedOption != null && selectedOption != defaultOption) {
                val classStatus = JsonObject()
                classStatus.addProperty("courseReportId", viewModel.classo.value?.courseReportId)
                when (selectedOption) {
                    "下架" -> {
                        courseReportBean.manageResult = true
                        // TODO viewModel.upadte(class.manageResult)
                        classStatus.addProperty("manageResult", true)
                        requestTask<JsonObject>(
                            "http://10.0.2.2:8080/THP101G2-WebServer-School/coursereport",
                            method = "PUT",
                            reqBody = classStatus
                        )
                        Navigation.findNavController(it)
                            .navigate(R.id.action_manageClassDetailFragment_to_manageHomeFragment)
                        Toast.makeText(view?.context,"已將課程下架", Toast.LENGTH_SHORT).show()
                    }
                    "解除" -> {
                        courseReportBean.manageResult = false
                        classStatus.addProperty("manageResult", false)
                        requestTask<JsonObject>(
                            "http://10.0.2.2:8080/THP101G2-WebServer-School/coursereport",
                            method = "PUT",
                            reqBody = classStatus
                        )
                        Navigation.findNavController(it)
                            .navigate(R.id.action_manageClassDetailFragment_to_manageHomeFragment)
                        Toast.makeText(view?.context,"已解除課程下架", Toast.LENGTH_SHORT).show()
                    }
                }

//                val test = CourseReportBean(
//                    courseId = viewModel?.classo?.value?.courseId!!,
//                    addAndRemove = viewModel?.classo?.value?.addAndRemove!!,
//                    manageResult = viewModel?.classo?.value?.manageResult!!
//                )

//                requestTask<JsonObject>(
//                    "http://10.0.2.2:8080/THP101G2-WebServer-School/coursereport/",
//                    method = "PUT",
//                    reqBody = test
//                )

            } else {
                // 未选取选项时的操作
                Toast.makeText(view?.context,"請選擇操作項目", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
