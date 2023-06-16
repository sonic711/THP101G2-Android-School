package com.example.thp101g2_android_school.shop.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentShopingCartBinding
import com.example.thp101g2_android_school.shop.model.ShopingCart
import com.example.thp101g2_android_school.shop.viewmodel.ProductViewModel
import com.example.thp101g2_android_school.shop.viewmodel.ShopFavoriteFgViewModel
import com.example.thp101g2_android_school.shop.viewmodel.ShopingCartFgViewModel
import com.example.thp101g2_android_school.shop.viewmodel.ShopingCartViewModel

class ShopingCartFragment : Fragment() {
    private var cartAdapter: ShopingCartAdapter? = null
    private lateinit var fragmentShopingCartBinding: FragmentShopingCartBinding // 添加该行
    private val viewModel: ShopingCartFgViewModel by viewModels { requireParentFragment().defaultViewModelProviderFactory }
    private lateinit var cartproducts: List<ShopingCart>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        (requireActivity() as MainActivity).supportActionBar?.hide()
//        binding = FragmentShopingCartBinding.inflate(inflater, container, false)
//        binding.viewModel = viewModel
//        return binding.root
        (requireActivity() as MainActivity).supportActionBar?.hide()
        fragmentShopingCartBinding = FragmentShopingCartBinding.inflate(inflater, container, false)
        fragmentShopingCartBinding.viewModel = viewModel // 更新为 fragmentShopingCartBinding
        cartproducts = emptyList() // 或者根据需要初始化为其他值
        // ...
        return fragmentShopingCartBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // TODO 這邊使用viewModel: ShopFavoriteFgViewModel 去使用viewmodel內的方法.loadProduct，可以即時更新我的最愛
        viewModel.loadProduct()
        cartAdapter = ShopingCartAdapter(cartproducts, fragmentShopingCartBinding)
        fragmentShopingCartBinding.recyclerView.adapter = cartAdapter
        //這裡註解要問老師關於SearchView的顯示跟關閉
        val searchView = requireActivity().findViewById<SearchView>(R.id.shopsearchView)
        with(fragmentShopingCartBinding) {
            //沒有layoutManager會沒recyclerview畫面
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.cartproducts?.observe(viewLifecycleOwner) { products ->
                cartproducts = products
                if (cartAdapter == null) {
                    cartAdapter = ShopingCartAdapter(products,fragmentShopingCartBinding)
                    recyclerView.adapter = cartAdapter
                } else {
                    cartAdapter?.updateProduct(products)
                }

                if (products.isEmpty()) {
                    tvSearchnull.visibility = View.VISIBLE
                } else {
                    tvSearchnull.visibility = View.GONE
                }
            }
        }

    }

}