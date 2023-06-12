package com.example.thp101g2_android_school.manage.controller


import com.example.thp101g2_android_school.app.requestTask
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
import androidx.navigation.fragment.findNavController
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.FragmentManageComDetailBinding
import com.example.thp101g2_android_school.manage.model.*
import com.example.thp101g2_android_school.manage.viewmodel.ManageClassViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageComViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageMemberViewModel
import com.google.gson.JsonObject

class ManageComDetailFragment : Fragment() {
    private lateinit var binding: FragmentManageComDetailBinding
    private lateinit var spinner: Spinner
    private var selectedOption: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageComDetailBinding.inflate(inflater, container, false)
        val viewModel: ManageComViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.Back.setOnClickListener {
            Navigation.findNavController(requireView()).navigateUp()
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { bundle ->
            bundle.getSerializable("comms")?.let {
                binding.viewModel?.commo?.value = it as ManageComReportBean
            }
        }

        val defaultOption = "請選擇封鎖或解除"
        selectedOption = null

        val viewModel = ViewModelProvider(this).get(ManageComViewModel::class.java)
        binding.viewModel = viewModel

        // Initialize spinner
        spinner = binding.MemberSpinner
        val options = listOf("封鎖", "解除")
        val spinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, options)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        // Set spinner selection listener
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
                // Perform actions when no option is selected
                selectedOption = null
            }
        }

        binding.btnBlock.setOnClickListener {
            // Check if a spinner option is selected
            if (selectedOption != null && selectedOption != defaultOption) {
                // Perform action based on the selected option
                val comment = JsonObject()
                comment.addProperty("comPostId", viewModel.commo.value?.comPostId)
                when (selectedOption) {
                    "封鎖" -> {
                        // TODO: viewModel.upadte(selectMemberBean.memberStatus)
                        comment.addProperty("comPostStatus", true)
                        requestTask<JsonObject>(
                            "http://10.0.2.2:8080/THP101G2-WebServer-School/managecomreport",
                            method = "PUT",
                            reqBody = comment
                        )
                    }
                    "解除" -> {
                        comment.addProperty("comPostStatus", false)
                        requestTask<JsonObject>(
                            "http://10.0.2.2:8080/THP101G2-WebServer-School/managecomreport",
                            method = "PUT",
                            reqBody = comment
                        )
                    }
                }
            } else {
                // Display a toast message or perform other actions if no option is selected
                Toast.makeText(requireContext(), "請選擇封鎖或解除，再次點擊送出", Toast.LENGTH_SHORT).show()
            }

//            Navigation.findNavController(it)
//                .navigate(R.id.action_manageComDetailFragment_to_manageHomeFragment)
//            Toast.makeText(view.context, "已更改會員狀態", Toast.LENGTH_SHORT).show()
        }
    }
}