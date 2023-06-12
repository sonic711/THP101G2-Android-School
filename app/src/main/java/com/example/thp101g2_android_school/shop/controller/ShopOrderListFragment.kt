package com.example.thp101g2_android_school.shop.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentShopOrderListBinding
import com.example.thp101g2_android_school.shop.viewmodel.*

class ShopOrderListFragment : Fragment() {

    private lateinit var binding: FragmentShopOrderListBinding
    private val viewModel: ShopOrderViewModel by viewModels { requireParentFragment().defaultViewModelProviderFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).supportActionBar?.hide()
        binding = FragmentShopOrderListBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.loadOrderProduct()
        //這裡註解要問老師關於SearchView的顯示跟關閉
        val searchView = requireActivity().findViewById<SearchView>(R.id.shopsearchView)
        with(binding) {
            //沒有layoutManager會沒recyclerview畫面
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.orders?.observe(viewLifecycleOwner) { orders ->
                // adapter為null要建立新的adapter
                val adapter = recyclerView.adapter as ShopOrderListAdapter?
                if (adapter == null) {
                    recyclerView.adapter = ShopOrderListAdapter(orders)
                } else {
                    adapter.updateProduct(orders)
                }

                if (orders.isEmpty()) {
                    tvOrdernull.visibility = View.VISIBLE // 显示 tvSearchnull
                } else {
                    tvOrdernull.visibility = View.GONE // 隐藏 tvSearchnull
                }
            }
            }

//            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
//                androidx.appcompat.widget.SearchView.OnQueryTextListener {
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    return false
//                }
//
//                // 當輸入內容變化時，呼叫search()
//                override fun onQueryTextChange(newText: String?): Boolean {
//                    viewModel?.search(newText)
//                    return true
//                }
//
//            })
        }
    }

