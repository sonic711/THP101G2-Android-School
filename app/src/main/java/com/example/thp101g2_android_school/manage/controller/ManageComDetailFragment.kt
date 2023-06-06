package com.example.thp101g2_android_school.manage.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.thp101g2_android_school.databinding.FragmentClassDetailBinding
import com.example.thp101g2_android_school.databinding.FragmentManageComDetailBinding

import com.example.thp101g2_android_school.manage.model.Classes
import com.example.thp101g2_android_school.manage.model.Comms
import com.example.thp101g2_android_school.manage.viewmodel.ManageClassViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageComViewModel

class ManageComDetailFragment : Fragment() {
    private lateinit var binding: FragmentManageComDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageComDetailBinding.inflate(inflater, container, false)
        val viewModel = ManageComViewModel()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { bundle ->
            bundle.getSerializable("comm")?.let {
                binding.viewModel?.commo?.value = it as Comms
            }
        }
    }
}