package com.example.thp101g2_android_school.manage.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.thp101g2_android_school.databinding.FragmentClassDetailBinding
import com.example.thp101g2_android_school.databinding.FragmentManageComDetailBinding

import com.example.thp101g2_android_school.manage.model.Classes
import com.example.thp101g2_android_school.manage.model.Comms
import com.example.thp101g2_android_school.manage.model.CourseReportBean
import com.example.thp101g2_android_school.manage.model.ManageComReportBean
import com.example.thp101g2_android_school.manage.viewmodel.ManageClassViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageComViewModel

class ManageComDetailFragment : Fragment() {
    private lateinit var binding: FragmentManageComDetailBinding
    private lateinit var manageComReportBean: ManageComReportBean

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageComDetailBinding.inflate(inflater, container, false)
        val viewModel = ManageComViewModel()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this



        binding.Back.setOnClickListener {
            Navigation.findNavController(requireView()).navigateUp()
            }
//        binding.ComBack.setOnClickListener {
//            findNavController().navigateUp()
//        }

        return binding.root

//        binding.btnBlock.setOnClickListener {
//
//        }
//        binding.btnBlockComment.setOnClickListener {
//
//        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            bundle.getSerializable("comm")?.let {
                manageComReportBean = it as ManageComReportBean
                binding.viewModel?.commo?.value =  manageComReportBean
            }
        }
    }
}