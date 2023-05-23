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
import com.example.thp101g2_android_school.databinding.FragmentShopingCartBinding
import com.example.thp101g2_android_school.shop.viewmodel.ProductViewModel
import com.example.thp101g2_android_school.shop.viewmodel.ShopOrderListViewModel
import com.example.thp101g2_android_school.shop.viewmodel.ShopOrderViewModel
import com.example.thp101g2_android_school.shop.viewmodel.ShopingCartViewModel

class ShopOrderListFragment : Fragment() {

    private lateinit var binding: FragmentShopOrderListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).supportActionBar?.hide()
        val viewModel: ShopOrderViewModel by viewModels()
        binding = FragmentShopOrderListBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //這裡註解要問老師關於SearchView的顯示跟關閉
        val searchView = requireActivity().findViewById<SearchView>(R.id.shopsearchView)
//        if(searchView.visibility == View.VISIBLE){
//            searchView.visibility = View.GONE
//        }
        with(binding) {
            //沒有layoutManager會沒recyclerview畫面
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.orders?.observe(viewLifecycleOwner) { orders ->
                // adapter為null要建立新的adapter
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = ShopOrderListAdapter(orders)
                } else {
                    (recyclerView.adapter as ShopOrderListAdapter).updateProduct(orders)
                    if (orders.isEmpty()) {
                        tvOrdernull.text = "目前沒有任何訂單呦"
                        tvOrdernull.visibility = View.VISIBLE // 顯示 tvSearchnull
                    } else {
                        tvOrdernull.text = "" // 將文字設為空字串
                        tvOrdernull.visibility = View.GONE // 隱藏 tvSearchnull
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