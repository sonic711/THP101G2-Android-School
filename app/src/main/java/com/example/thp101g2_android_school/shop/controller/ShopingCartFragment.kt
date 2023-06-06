package com.example.thp101g2_android_school.shop.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentShopingCartBinding
import com.example.thp101g2_android_school.shop.viewmodel.ProductViewModel
import com.example.thp101g2_android_school.shop.viewmodel.ShopFavoriteFgViewModel
import com.example.thp101g2_android_school.shop.viewmodel.ShopingCartFgViewModel
import com.example.thp101g2_android_school.shop.viewmodel.ShopingCartViewModel

class ShopingCartFragment : Fragment() {

    private lateinit var binding: FragmentShopingCartBinding
    private val viewModel: ShopingCartFgViewModel by viewModels { requireParentFragment().defaultViewModelProviderFactory }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).supportActionBar?.hide()
        binding = FragmentShopingCartBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // TODO 這邊使用viewModel: ShopFavoriteFgViewModel 去使用viewmodel內的方法.loadProduct，可以即時更新我的最愛
        viewModel.loadProduct()
        //這裡註解要問老師關於SearchView的顯示跟關閉
        val searchView = requireActivity().findViewById<SearchView>(R.id.shopsearchView)
        with(binding) {
            //沒有layoutManager會沒recyclerview畫面
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.cartproducts?.observe(viewLifecycleOwner) { products ->
                // adapter為null要建立新的adapter
                val adapter = recyclerView.adapter as ShopingCartAdapter?
                if (adapter == null) {
                    recyclerView.adapter = ShopingCartAdapter(products)
                } else {
                    adapter.updateProduct(products)
                }

                if (products.isEmpty()) {
                    tvSearchnull.visibility = View.VISIBLE // 显示 tvSearchnull
                } else {
                    tvSearchnull.visibility = View.GONE // 隐藏 tvSearchnull
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