package com.example.thp101g2_android_school.shop.controller

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.shop.model.ShopPage
import com.example.thp101g2_android_school.databinding.FragmentShopMainBinding
import com.example.thp101g2_android_school.shop.viewmodel.ProductViewModel
import com.google.android.material.tabs.TabLayoutMediator

class ShopMainFragment : Fragment() {

    private lateinit var binding: FragmentShopMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // SearchView放在畫面頂端時通常會隱藏標題列
        (requireActivity() as MainActivity).supportActionBar?.hide()
        val viewModel: ProductViewModel by viewModels()
        binding = FragmentShopMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            //沒有layoutManager會沒recyclerview畫面
            recyclerView.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            viewModel?.products?.observe(viewLifecycleOwner) { products ->
                // adapter為null要建立新的adapter；之後只要呼叫updateFriends(friends)即可
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = ProductAdapter(products)
                } else {
                    (recyclerView.adapter as ProductAdapter).updateProduct(products)
                    if (products.isEmpty()) {
                        tvSearchnull.text = "搜尋無資料"
                        tvSearchnull.visibility = View.VISIBLE // 顯示 tvSearchnull
                    } else {
                        tvSearchnull.text = "" // 將文字設為空字串
                        tvSearchnull.visibility = View.GONE // 隱藏 tvSearchnull
                    }
                }
            }
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                // STEP09-2 當輸入內容變化時，呼叫search()
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel?.search(newText)
                    return true
                }

            })
            binding.btnBack.setOnClickListener {
                AlertDialog.Builder(requireContext())
                    .setMessage("確定要回上一頁嗎?")
                    .setTitle("警告!!!!")
                    .setPositiveButton("確定"){ dialog, which ->
                        Navigation.findNavController(requireView()).navigateUp()
                    }
                    .setNeutralButton("取消", null)
                    .show()
            }
        }


    }
}