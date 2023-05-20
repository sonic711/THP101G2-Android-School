package com.example.thp101g2_android_school.manage.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.thp101g2_android_school.databinding.FragmentClassDetailBinding

import com.example.thp101g2_android_school.manage.model.Classes

class ClassDetailFragment : Fragment() {
    private lateinit var binding: FragmentClassDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClassDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { bundle ->
            bundle.getSerializable("class")?.let {
                binding.viewModel?.classo?.value = it as Classes
            }
        }
    }
}