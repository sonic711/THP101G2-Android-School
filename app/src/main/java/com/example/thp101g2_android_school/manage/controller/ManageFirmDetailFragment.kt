package com.example.thp101g2_android_school.manage.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.thp101g2_android_school.databinding.FragmentClassDetailBinding
import com.example.thp101g2_android_school.databinding.FragmentManageFirmDetailBinding

import com.example.thp101g2_android_school.manage.model.Classes
import com.example.thp101g2_android_school.manage.model.Comms
import com.example.thp101g2_android_school.manage.model.Firms

class ManageFirmDetailFragment : Fragment() {
    private lateinit var binding: FragmentManageFirmDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageFirmDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { bundle ->
            bundle.getSerializable("firm")?.let {
                binding.viewModel?.firmo?.value = it as Firms
            }
        }
    }
}