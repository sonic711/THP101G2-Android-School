package com.example.thp101g2_android_school.shop.controller

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentShopFrontBinding
import com.example.thp101g2_android_school.shop.model.ShopPage
import com.example.thp101g2_android_school.shop.viewmodel.ProductViewModel
import com.google.android.material.tabs.TabLayoutMediator

class ShopFrontFragment : Fragment() {

    private lateinit var binding: FragmentShopFrontBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentShopFrontBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        handleViews()
    }

    private fun handleViews() {
        val pages = listOf(
            ShopPage(
                "首頁",
                ContextCompat.getColor(requireContext(), R.color.white),
                ShopMainFragment()
            ),
            ShopPage(
                "我的最愛",
                ContextCompat.getColor(requireContext(), R.color.white),
                ShopFavoriteFragment()
            ),
            ShopPage(
                "我的購物車",
                ContextCompat.getColor(requireContext(), R.color.white),
                ShopingCartFragment()
            ),
            //待修.jerry
//            ShopPage(
//                "我的訂單",
//                ContextCompat.getColor(requireContext(), R.color.white),
//                ComAllClassFragment()
//            )
        )
        with(binding) {
            viewPager2.adapter = MyFragmentStateAdapter(this@ShopFrontFragment, pages)
            viewPager2.setCurrentItem(1, false)
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                // 設定tab標題文字
                tab.text = pages[position].title
                tab.view.setBackgroundColor(pages[position].color)
            }.attach()
        }

    }

    class MyFragmentStateAdapter(activity: ShopFrontFragment, private var pages: List<ShopPage>) :
        FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            return pages.size
        }

        override fun createFragment(position: Int): Fragment {
            return pages[position].fragment
        }
    }


}