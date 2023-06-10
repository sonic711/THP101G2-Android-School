package com.example.thp101g2_android_school.shop.controller


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentShopFrontBinding
import com.example.thp101g2_android_school.shop.model.ShopPage
import com.google.android.material.tabs.TabLayout
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
                ShopFavoriteFragment(),
            ),
            ShopPage(
                "我的購物車",
                ContextCompat.getColor(requireContext(), R.color.white),
                ShopingCartFragment()
            ),
            ShopPage(
                "我的訂單",
                ContextCompat.getColor(requireContext(), R.color.white),
                ShopOrderListFragment()
            )
        )
        with(binding) {
            viewPager2.adapter = MyFragmentStateAdapter(this@ShopFrontFragment, pages)
            viewPager2.setCurrentItem(0, false)
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                // 設定tab標題文字
                tab.text = pages[position].title
                tab.view.setBackgroundColor(pages[position].color)
            }.apply {
                attach()
                handleTabLayout(pages)
            }
        }

    }
    private fun handleTabLayout(pages: List<ShopPage>) {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position ?: return
                val selectedPage = pages[position]

                // 根据选中的页面执行相应操作
                when (selectedPage.title) {
                    "首頁" -> {
                        binding.shopsearchView.visibility = View.VISIBLE
                        binding.tvShopTitle.text = ""
                    }
                    "我的最愛" -> {
                        binding.shopsearchView.visibility = View.GONE
                        binding.tvShopTitle.text = "我的最愛"
                    }
                    "我的購物車" -> {
                        binding.shopsearchView.visibility = View.GONE
                        binding.tvShopTitle.text = "我的購物車"
                    }
                    "我的訂單" -> {
                        binding.shopsearchView.visibility = View.GONE
                        binding.tvShopTitle.text = "我的訂單"
                    }
                }
                if (selectedPage.title == "首頁") {
                    val searchView = requireActivity().findViewById<SearchView>(R.id.shopsearchView)
                    searchView.visibility = View.VISIBLE
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
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