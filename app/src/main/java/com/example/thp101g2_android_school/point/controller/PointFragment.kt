package com.example.thp101g2_android_school.point.controller

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentPointBinding
import com.example.thp101g2_android_school.firm.viewmodel.FirmProductsEditViewModel
import com.example.thp101g2_android_school.point.others.SetAlertDialog
import com.example.thp101g2_android_school.point.viewmodel.PointViewModel


class PointFragment : Fragment() {
    private lateinit var binding: FragmentPointBinding
    private val viewModel: PointViewModel by viewModels { requireParentFragment().defaultViewModelProviderFactory }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: PointViewModel by viewModels()
        binding = FragmentPointBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            viewModel?.loadData()
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            val pointAdapter = PointAdapter(emptyList())
            recyclerView.adapter = pointAdapter

            val pointViewModel: PointViewModel by viewModels()
            pointViewModel.reasons.observe(viewLifecycleOwner) { reasons ->
                pointAdapter.updateReasons(reasons)
            }




        }


    }


}