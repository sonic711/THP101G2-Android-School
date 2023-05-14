package com.example.thp101g2_android_school.shop.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation

import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.databinding.FragmentProductDetailBinding
import com.example.thp101g2_android_school.shop.model.Product
import com.example.thp101g2_android_school.shop.viewmodel.ShopMainViewModel

class ProductDetailFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // 呈現標題列
        (requireActivity() as MainActivity).supportActionBar?.show()
        val viewModel: ShopMainViewModel by viewModels()
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { bundle ->
            bundle.getSerializable("product")?.let {
                binding.viewModel?.product?.value = it as Product
            }
        }
        binding.btnBack.setOnClickListener {
            Navigation.findNavController(requireView()).navigateUp()
        }
    }
}