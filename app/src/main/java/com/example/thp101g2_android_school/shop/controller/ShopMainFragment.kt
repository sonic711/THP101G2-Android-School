package com.example.thp101g2_android_school.shop.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentShopMainBinding
import com.example.thp101g2_android_school.shop.viewmodel.ProductViewModel

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
            //這裡註解要問老師關於SearchView的顯示跟關閉
            val searchView = requireActivity().findViewById<SearchView>(R.id.shopsearchView)
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
        }


    }
}