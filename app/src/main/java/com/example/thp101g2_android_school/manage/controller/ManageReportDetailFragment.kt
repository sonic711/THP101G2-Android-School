package com.example.thp101g2_android_school.manage.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.databinding.FragmentClassDetailBinding
import com.example.thp101g2_android_school.databinding.FragmentManageReportDetailBinding

import com.example.thp101g2_android_school.manage.model.Classes
import com.example.thp101g2_android_school.manage.model.Reports
import com.example.thp101g2_android_school.manage.viewmodel.ManageMaViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageReportViewModel

class ManageReportDetailFragment : Fragment() {
    private lateinit var binding: FragmentManageReportDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageReportDetailBinding.inflate(inflater, container, false)
        val viewModel = ManageReportViewModel()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.Back.setOnClickListener {
            Navigation.findNavController(requireView()).navigateUp()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { bundle ->
            bundle.getSerializable("Report")?.let {
                binding.viewModel?.reporto?.value = it as Reports
            }
        }
    }
}