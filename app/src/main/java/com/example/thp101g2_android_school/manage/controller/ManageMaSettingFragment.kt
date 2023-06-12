package com.example.thp101g2_android_school.manage.controller

//import android.R
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
    private lateinit var spinner: Spinner
    private lateinit var managePerBean: ManagePerBean
    private var selectedOption: String? = null
    private var receivedData: Boolean = false

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
        arguments?.let { bundle ->
            bundle.getSerializable("ma")?.let {
                managePerBean = it as ManagePerBean
                binding.viewModel?.mao?.value = managePerBean
            }

        }
        (requireActivity() as ManageMainActivity).supportActionBar?.hide()
        val defaultOption = "請選擇封鎖或解除"
        selectedOption = null

        val viewModel = ViewModelProvider(this).get(ManageMaViewModel::class.java)
        binding.viewModel = viewModel
        spinner = binding.spinner
        val options = listOf("新增管理者", "修改管理者")
        val spinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, options)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter
//        val receivedData: Boolean
        val editTextNumber: EditText = binding.printAccount
        val editTextPassword: EditText = binding.printPassword
        val memberManageCheckbox: CheckBox = binding.memberManage
        val manageReportCheckbox: CheckBox = binding.manageReport
        val manageFirmCheckbox: CheckBox = binding.manageFirm
        val manageCourseCheckbox: CheckBox = binding.classManage
        val manageComCheckbox: CheckBox = binding.comManage
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedOption = parent.getItemAtPosition(position) as String

                // 所選操作的類型更新 managePerBean 的屬性
                when (selectedOption) {
                    "新增管理者" -> {
                        // 更新 receivedData 的值为 checkBox 的选中状态
                        receivedData =
                            memberManageCheckbox.isChecked && manageReportCheckbox.isChecked
                                    && manageFirmCheckbox.isChecked && manageCourseCheckbox.isChecked
                                    && manageComCheckbox.isChecked
                    }
                    "修改管理者" -> {
                        // 更新 receivedData 的值为 checkBox 的选中状态
                        receivedData =
                            !(memberManageCheckbox.isChecked || manageReportCheckbox.isChecked
                                    || manageFirmCheckbox.isChecked || manageCourseCheckbox.isChecked
                                    || manageComCheckbox.isChecked)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedOption = null
            }
        }
        // 聲明一個 ManagePerBean 初始化
        val managePerBean = ManagePerBean()

//            binding.btCheck.setOnClickListener {
//
//                    }
//                }


//                memberManageCheckbox.setOnCheckedChangeListener { _, isChecked ->
//                    managePerBean.manageMember = isChecked
//                }
//
//                manageReportCheckbox.setOnCheckedChangeListener { _, isChecked ->
//                    managePerBean.manageReport = isChecked
//                }
//
//                manageFirmCheckbox.setOnCheckedChangeListener { _, isChecked ->
//                    managePerBean.manageFirm = isChecked
//                }
//
//                manageCourseCheckbox.setOnCheckedChangeListener { _, isChecked ->
//                    managePerBean.manageCourse = isChecked
//                }
//
//                manageComCheckbox.setOnCheckedChangeListener { _, isChecked ->
//                    managePerBean.manageCom = isChecked
//                }


        binding.btCheck.setOnClickListener {
            val accountNumber = editTextNumber.text.toString()
            val password = editTextPassword.text.toString()
            // 帳號為空?
            if (accountNumber.isEmpty()) {
                editTextNumber.error = "請輸入帳號"
                if (password.isEmpty()) {
                    editTextPassword.error = "請輸入密碼"

            val selectedOperation = spinner.selectedItem.toString()
            if (selectedOption != null && selectedOption != defaultOption) {
                val manage = JsonObject()
                manage.addProperty("manageId", viewModel.mao.value?.manageId)

                //根據所選的操作類型執行
                when (selectedOperation) {
                    "新增管理者" -> {
                        manage.addProperty("manageId", 0)

                    }
                    "修改管理者" -> {
                        manage.addProperty("manageId", 1)

                    }
                }
                manage.addProperty("accountNumber", editTextNumber.text.toString())
                manage.addProperty("password", editTextPassword.text.toString())
                manage.addProperty("manageMember", receivedData)
                println(manage)
                val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/manageaccount"
                val method = if (selectedOperation == "新增管理者") "POST" else "PUT"
                requestTask<JsonObject>(url, method = method, reqBody = manage)
                     }
                }
            }
        }
    }
}
