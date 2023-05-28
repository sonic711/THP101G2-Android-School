package com.example.thp101g2_android_school.community.controller

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.thp101g2_android_school.MainFragment
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.community.model.Page
import com.example.thp101g2_android_school.databinding.FragmentComMainBinding
import com.example.thp101g2_android_school.shop.controller.ShopMainFragment
import com.google.android.material.tabs.TabLayoutMediator

class ComMainFragment : Fragment() {
     lateinit var binding: FragmentComMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        handleViews()
        with(binding) {
            floatingBtn.setOnClickListener {
                val navController = Navigation.findNavController(it)
                /**
                 *  TODO 這邊可能會有問題，先記錄一下
                 *  回到上一頁會失效，因為主頁被pop掉了，解決辦法可能是之後要倒回ComMainFragment
                 */
                navController.popBackStack()
                navController.navigate(R.id.comPostFragment)

            }
        }
    }

    private fun handleViews() {
        val pages = listOf(
            Page("追蹤", ContextCompat.getColor(requireContext(), R.color.gray_50), ComFollowFragment()),
            Page("全部文章", ContextCompat.getColor(requireContext(), R.color.gray_50), ComAllPostFragment()),
            Page("全部分類", ContextCompat.getColor(requireContext(), R.color.gray_50), ComAllClassFragment()),
        )

        with(binding) {

            viewPager2.adapter = MyFragmentStateAdapter(this@ComMainFragment, pages)
            // 預設頁面為全部文章
            viewPager2.setCurrentItem(1, false)
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                // 設定tab標題文字
                tab.text = pages[position].title
                tab.view.setBackgroundColor(pages[position].color)
            }.attach()
        }
    }

    class MyFragmentStateAdapter(activity: ComMainFragment, private var pages: List<Page>) :
        FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            return pages.size
        }

        override fun createFragment(position: Int): Fragment {
            return pages[position].fragment
        }
    }

}