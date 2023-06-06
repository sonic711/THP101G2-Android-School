package com.example.thp101g2_android_school.manage.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentManageHomeBinding

class ManageHomeFragment : Fragment() {
    private lateinit var binding: FragmentManageHomeBinding
    //每次點擊都是新?
    companion object {
        fun newInstance() = ManageHomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val navController = findNavController()
        navController.popBackStack(R.id.manageHomeFragment, false)
        binding = FragmentManageHomeBinding.inflate(inflater, container, false)

        with(binding) {
            btManageClass.setOnClickListener {
                findNavController().navigate(R.id.action_manageHomeFragment_to_manageClassesFragment)
            }
            btManageCom.setOnClickListener {
                findNavController().navigate(R.id.action_manageHomeFragment_to_manageComFragment)
            }
            btManageFirm.setOnClickListener {
                findNavController().navigate(R.id.action_manageHomeFragment_to_manageFirmFragment)
            }
            btManageMa.setOnClickListener {
                findNavController().navigate(R.id.action_manageHomeFragment_to_manageMaFragment)
            }
            btManageMember.setOnClickListener {
                findNavController().navigate(
                    R.id.action_manageHomeFragment_to_manageMemberFragment,
                    null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.mainFragment, false)
                        .build()
                )
            }
            btManageReport.setOnClickListener {
                findNavController().navigate(R.id.action_manageHomeFragment_to_manageReportFragment)
            }
        }

        return binding.root
    }
}


