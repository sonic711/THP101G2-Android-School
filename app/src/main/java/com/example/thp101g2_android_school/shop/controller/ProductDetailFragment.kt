package com.example.thp101g2_android_school.shop.controller

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation

import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentProductDetailBinding
import com.example.thp101g2_android_school.shop.model.Product
import com.example.thp101g2_android_school.shop.viewmodel.ShopMainViewModel

class ProductDetailFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var FvisClicked = false
    private var ScisClicked = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // 呈現標題列
        (requireActivity() as MainActivity).supportActionBar?.show()
        val viewModel: ShopMainViewModel by viewModels()
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        sharedPreferences = requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        FvisClicked = sharedPreferences.getBoolean("isButtonClicked", false)
        ScisClicked = sharedPreferences.getBoolean("isButtonClicked2", false)
        binding.btShopFavorite.setImageResource(if (FvisClicked) R.drawable.shop_favorite_clicked else R.drawable.shop_favorite_notclick)
        binding.btShoppingCartAdd.setImageResource(if (ScisClicked) R.drawable.shop_shoppingcart_clicked else R.drawable.shop_shoppingcart_notclick)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FvisClicked = sharedPreferences.getBoolean("isButtonClicked", false)
        ScisClicked = sharedPreferences.getBoolean("isButtonClicked2", false)
        arguments?.let { bundle ->
            bundle.getSerializable("product")?.let {
                binding.viewModel?.product?.value = it as Product
            }
        }
        binding.btnBack.setOnClickListener {
            Navigation.findNavController(requireView()).navigateUp()
        }
        binding.btShopFavorite.setOnClickListener {
            FvisClicked = !FvisClicked
            binding.btShopFavorite.setImageResource(if (FvisClicked) R.drawable.shop_favorite_clicked else R.drawable.shop_favorite_notclick)
            if(FvisClicked){
                Toast.makeText(context, "加入最愛成功!!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "刪除自我的最愛", Toast.LENGTH_SHORT).show()
            }

            val Fveditor = sharedPreferences.edit()
            Fveditor.putBoolean("isButtonClicked", FvisClicked)
            Fveditor.apply()
        }
        binding.btShoppingCartAdd.setOnClickListener {
            ScisClicked = !ScisClicked
            binding.btShoppingCartAdd.setImageResource(if (ScisClicked) R.drawable.shop_shoppingcart_clicked else R.drawable.shop_shoppingcart_notclick)
            if(ScisClicked){
                Toast.makeText(context, "加入購物車成功!!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "刪除自我的購物車", Toast.LENGTH_SHORT).show()
            }

            val Sceditor = sharedPreferences.edit()
            Sceditor.putBoolean("isButtonClicked2", ScisClicked)
            Sceditor.apply()
        }
    }
}
//package com.example.thp101g2_android_school.shop.controller
//
//import android.content.Context
//import android.content.SharedPreferences
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.fragment.app.viewModels
//import androidx.navigation.Navigation
//
//import com.example.thp101g2_android_school.MainActivity
//import com.example.thp101g2_android_school.R
//import com.example.thp101g2_android_school.app.requestTask
//import com.example.thp101g2_android_school.databinding.FragmentProductDetailBinding
//import com.example.thp101g2_android_school.shop.model.Product
//import com.example.thp101g2_android_school.shop.viewmodel.ShopMainViewModel
//import com.google.gson.reflect.TypeToken
//
//class ProductDetailFragment : Fragment() {
//    private lateinit var binding: FragmentProductDetailBinding
//    private var FvisClicked = false
//    private var ScisClicked = false
//    //TODO 透過productid存我的最愛or購物車，布林值判斷DB內有沒有該productid，有就ture沒有就false，不要用按鈕事件判斷
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View {
//        // 呈現標題列
//        (requireActivity() as MainActivity).supportActionBar?.show()
//        val viewModel: ShopMainViewModel by viewModels()
//        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
//        binding.viewModel = viewModel
//        binding.lifecycleOwner = this
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        arguments?.let { bundle ->
//            bundle.getSerializable("product")?.let {
//                binding.viewModel?.product?.value = it as Product
//            }
//        }
//        binding.btnBack.setOnClickListener {
//            Navigation.findNavController(requireView()).navigateUp()
//        }
//        binding.btShopFavorite.setOnClickListener {
//            // 查询数据库，检查产品是否在收藏夹中
//            val isFavorite = isProductInFavorites(productId = null)
//            if (isFavorite) {
//                Toast.makeText(context, "已加入最愛", Toast.LENGTH_SHORT).show()
//                binding.btShopFavorite.setImageResource(R.drawable.shop_favorite_clicked)
//            } else {
//                Toast.makeText(context, "未加入最愛", Toast.LENGTH_SHORT).show()
//                binding.btShopFavorite.setImageResource(R.drawable.shop_favorite_notclick)
//            }
//        }
//
//        binding.btShoppingCartAdd.setOnClickListener {
//            // 查询数据库，检查产品是否在购物车中
//            val isInShoppingCart = isProductInShoppingCart(productId = null)
//            if (isInShoppingCart) {
//                Toast.makeText(context, "已加入購物車", Toast.LENGTH_SHORT).show()
//                binding.btShoppingCartAdd.setImageResource(R.drawable.shop_shoppingcart_clicked)
//            } else {
//                Toast.makeText(context, "未加入購物車", Toast.LENGTH_SHORT).show()
//                binding.btShoppingCartAdd.setImageResource(R.drawable.shop_shoppingcart_notclick)
//            }
//        }
//
//    }
//    private fun isProductInFavorites(productId: String?) {
//        // 根据productId查询数据库，判断产品是否在收藏夹中
//        // 返回true表示在收藏夹中，返回false表示不在收藏夹中
//        // 实现具体的数据库查询逻辑，根据你的数据库结构和查询方式进行修改
//
//        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/shop"
//
//
//    }
//
//    private fun isProductInShoppingCart(productId: String?) {
//        // 根据productId查询数据库，判断产品是否在购物车中
//        // 返回true表示在购物车中，返回false表示不在购物车中
//        // 实现具体的数据库查询逻辑，根据你的数据库结构和查询方式进行修改
//
//    }
//}
