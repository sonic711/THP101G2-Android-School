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
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.FragmentShopFavoriteBinding
import com.example.thp101g2_android_school.member.model.Member
import com.example.thp101g2_android_school.shop.viewmodel.ShopFavoriteFgViewModel


class ShopFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentShopFavoriteBinding
    val viewModel: ShopFavoriteFgViewModel by viewModels { requireParentFragment().defaultViewModelProviderFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).supportActionBar?.hide()
        binding = FragmentShopFavoriteBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var currentMember: Member? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/members", "OPTIONS")
        println(currentMember)
        val memberno = currentMember?.memberNo
        //TODO 這邊使用viewModel: ShopFavoriteFgViewModel 去使用viewmodel內的方法.loadProduct，可以即時更新我的最愛
        viewModel.loadProduct()
        //這裡註解要問老師關於SearchView的顯示跟關閉
        val searchView = requireActivity().findViewById<SearchView>(R.id.shopsearchView)
        with(binding) {
            //沒有layoutManager會沒recyclerview畫面
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.favoriteproducts?.observe(viewLifecycleOwner) { favoriteproducts ->
                val adapter = recyclerView.adapter as ShopFavoriteAdapter?
                if (adapter == null) {
                    recyclerView.adapter = ShopFavoriteAdapter(favoriteproducts)
                } else {
                    adapter.updateProduct(favoriteproducts)
                }

                if (favoriteproducts.isEmpty()) {
                    tvSearchnull.visibility = View.VISIBLE // 显示 tvSearchnull
                } else {
                    tvSearchnull.visibility = View.GONE // 隐藏 tvSearchnull
                }
            }
        }


    }
}