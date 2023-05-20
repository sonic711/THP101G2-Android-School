package com.example.thp101g2_android_school.shop.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentShopFavoriteBinding
import com.example.thp101g2_android_school.databinding.FragmentShopMainBinding
import com.example.thp101g2_android_school.shop.viewmodel.ProductViewModel
import com.example.thp101g2_android_school.shop.viewmodel.ShopFavoriteViewModel


class ShopFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentShopFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).supportActionBar?.hide()
        val viewModel: ProductViewModel by viewModels()
        binding = FragmentShopFavoriteBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            //沒有layoutManager會沒recyclerview畫面
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.products?.observe(viewLifecycleOwner) { products ->
                // adapter為null要建立新的adapter；之後只要呼叫updateFriends(friends)即可
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = ShopFavoriteAdapter(products)
                } else {
                    (recyclerView.adapter as ShopFavoriteAdapter).updateProduct(products)
                    if (products.isEmpty()) {
                        tvSearchnull.text = "搜尋無資料"
                        tvSearchnull.visibility = View.VISIBLE // 顯示 tvSearchnull
                    } else {
                        tvSearchnull.text = "" // 將文字設為空字串
                        tvSearchnull.visibility = View.GONE // 隱藏 tvSearchnull
                    }
                }
            }
        }

    }
}