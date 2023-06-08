package com.example.thp101g2_android_school.manage.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thp101g2_android_school.databinding.FragmentManageTeaApplyDetailBinding
import com.example.thp101g2_android_school.manage.model.Reports
import com.example.thp101g2_android_school.manage.model.TeaApplyBean
import com.example.thp101g2_android_school.manage.viewmodel.ManageComViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageTeaApplyViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageTeaApplysViewModel

class ManageTeaApplyDetailFragment : Fragment() {
    private  lateinit var binding: FragmentManageTeaApplyDetailBinding

    companion object {
        fun newInstance() = ManageTeaApplyDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentManageTeaApplyDetailBinding.inflate(inflater, container, false)
        val viewModel = ManageTeaApplyViewModel()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { bundle ->
            bundle.getSerializable("teaapply")?.let {
                binding.viewModel?.teaapply?.value = it as TeaApplyBean
            }
        }
    }
}

