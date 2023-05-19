package com.example.thp101g2_android_school.shop.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentShopFavoriteBinding
import com.example.thp101g2_android_school.databinding.FragmentShopingCartBinding
import com.example.thp101g2_android_school.shop.viewmodel.ShopFavoriteViewModel
import com.example.thp101g2_android_school.shop.viewmodel.ShopingCartViewModel

class ShopingCartFragment : Fragment() {

    private lateinit var binding: FragmentShopingCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).supportActionBar?.hide()
        val viewModel: ShopingCartViewModel by viewModels()
        binding = FragmentShopingCartBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

}