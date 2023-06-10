package com.example.thp101g2_android_school.manage.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.databinding.FragmentClassDetailBinding
import com.example.thp101g2_android_school.databinding.FragmentManageFirmDetailBinding

import com.example.thp101g2_android_school.manage.model.Classes
import com.example.thp101g2_android_school.manage.model.Comms
import com.example.thp101g2_android_school.manage.model.Firms
import com.example.thp101g2_android_school.manage.viewmodel.ManageClassViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageFirmViewModel

class ManageFirmDetailFragment : Fragment() {
    private lateinit var binding: FragmentManageFirmDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageFirmDetailBinding.inflate(inflater, container, false)
        val viewModel = ManageFirmViewModel()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.Back.setOnClickListener {
            Navigation.findNavController(requireView()).navigateUp()
        }

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