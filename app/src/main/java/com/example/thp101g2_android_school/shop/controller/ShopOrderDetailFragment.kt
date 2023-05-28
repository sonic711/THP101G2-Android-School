package com.example.thp101g2_android_school.shop.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.databinding.FragmentShopOrderDetailBinding
import com.example.thp101g2_android_school.shop.model.ShopOrderList
import com.example.thp101g2_android_school.shop.viewmodel.ShopOrderListViewModel

class ShopOrderDetailFragment : Fragment() {
    private lateinit var binding: FragmentShopOrderDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).supportActionBar?.show()
        val viewModel: ShopOrderListViewModel by viewModels()
        binding = FragmentShopOrderDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { bundle ->
            bundle.getSerializable("order")?.let {
                binding.viewModel?.order?.value = it as ShopOrderList
            }
        }
        binding.btnBack.setOnClickListener {
            Navigation.findNavController(requireView()).navigateUp()
        }
    }



}