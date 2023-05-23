package com.example.thp101g2_android_school.firm.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentFirmOrderDetailBinding
import com.example.thp101g2_android_school.databinding.FragmentFirmOrderManagerBinding
import com.example.thp101g2_android_school.firm.model.Order
import com.example.thp101g2_android_school.firm.viewmodel.FirmOrderViewModel
import com.example.thp101g2_android_school.firm.viewmodel.FirmOrdersViewModel

class FirmOrderDetailFragment : Fragment() {
    private lateinit var binding : FragmentFirmOrderDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewMode : FirmOrderViewModel by viewModels()
        binding = FragmentFirmOrderDetailBinding.inflate(inflater,container,false)
        binding.viewModel = viewMode
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            arguments?.let { bundle ->
                bundle.getSerializable("order")?.let {
                    binding.viewModel?.firmOrder?.value = it as Order
                }
            }
        }
    }

}