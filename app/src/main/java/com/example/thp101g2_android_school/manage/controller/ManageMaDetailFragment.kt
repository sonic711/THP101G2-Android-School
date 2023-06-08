package com.example.thp101g2_android_school.manage.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.databinding.FragmentClassDetailBinding
import com.example.thp101g2_android_school.databinding.FragmentManageMaDetailBinding

import com.example.thp101g2_android_school.manage.model.Classes
import com.example.thp101g2_android_school.manage.model.ManageAccountBean
import com.example.thp101g2_android_school.manage.model.Mas
import com.example.thp101g2_android_school.manage.viewmodel.ManageFirmViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageMaViewModel

class ManageMaDetailFragment : Fragment() {
    private lateinit var binding: FragmentManageMaDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageMaDetailBinding.inflate(inflater, container, false)
        val viewModel = ManageMaViewModel()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.Back.setOnClickListener {
            Navigation.findNavController(requireView()).navigateUp()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { bundle ->
            bundle.getSerializable("ma")?.let {
                binding.viewModel?.mao?.value = it as ManageAccountBean
            }
        }
    }
}