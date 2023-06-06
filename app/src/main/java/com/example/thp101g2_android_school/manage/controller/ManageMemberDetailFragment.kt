package com.example.thp101g2_android_school.manage.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thp101g2_android_school.databinding.FragmentManageMemberDetailBinding
import com.example.thp101g2_android_school.manage.model.Members
import com.example.thp101g2_android_school.manage.viewmodel.ManageMemberViewModel

class ManageMemberDetailFragment : Fragment() {
    private lateinit var binding: FragmentManageMemberDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {


        binding = FragmentManageMemberDetailBinding.inflate(inflater, container, false)
        //多了底下這兩串
        val viewModel = ManageMemberViewModel()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { bundle ->
            bundle.getSerializable("member")?.let {
                binding.viewModel?.memberuse?.value = it as Members
            }
        }
    }

    }