package com.example.thp101g2_android_school.manage.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentManageHomeBinding

class ManageHomeFragment : Fragment() {
    private lateinit var binding: FragmentManageHomeBinding

    companion object {
        fun newInstance() = ManageHomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentManageHomeBinding.inflate(inflater, container, false)

        with(binding) {
            btManageClass.setOnClickListener {
                // 使用导航控制器进行页面转换
                findNavController().navigate(R.id.action_manageHomeFragment_to_manageClassesFragment)
            }
            btManageCom.setOnClickListener {
                // 使用导航控制器进行页面转换
                findNavController().navigate(R.id.action_manageHomeFragment_to_manageComFragment)
            }
            btManageFirm.setOnClickListener {
                // 使用导航控制器进行页面转换
                findNavController().navigate(R.id.action_manageHomeFragment_to_manageFirmFragment)
            }
            btManageMa.setOnClickListener {
                // 使用导航控制器进行页面转换
                findNavController().navigate(R.id.action_manageMaFragment_to_manageMaDetailFragment)
            }
            btManageMember.setOnClickListener {
                // 使用导航控制器进行页面转换
                findNavController().navigate(R.id.action_manageHomeFragment_to_manageMemberFragment)
            }
            btManageReport.setOnClickListener {
                // 使用导航控制器进行页面转换
                findNavController().navigate(R.id.action_manageHomeFragment_to_manageReportFragment)
            }
        }

        return binding.root
    }
}


