package com.example.thp101g2_android_school.shop.controller

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.databinding.FragmentShopBuyBinding
import com.example.thp101g2_android_school.shop.model.Product
import com.example.thp101g2_android_school.shop.model.ShopingCart
import com.example.thp101g2_android_school.shop.viewmodel.ShopMainViewModel
import com.example.thp101g2_android_school.shop.viewmodel.ShopingCartViewModel


class ShopBuyFragment : Fragment() {
    private lateinit var binding: FragmentShopBuyBinding
    private lateinit var cartproduct: ShopingCart
    private var quantity = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: ShopingCartViewModel by viewModels()
        binding = FragmentShopBuyBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.editTextNumber.visibility = View.GONE
        binding.tvPoint.visibility = View.GONE
        binding.tvPoints.visibility = View.GONE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        /**
         * TODO
         * 將cartproduct的資料帶到後端減掉quantity計算，商品數量夠不夠會員購買
         */

        arguments?.let { bundle ->
            bundle.getSerializable("cartproduct")?.let {
                cartproduct = it as ShopingCart
                binding.viewModel?.cartproduct?.value = cartproduct
                println(cartproduct.pquality)


            }
            arguments?.let { bundle ->
                quantity = bundle.getInt("quantity")
                println(quantity)
            }



            binding.btnBack.setOnClickListener {
                AlertDialog.Builder(requireContext())
                    .setMessage("確定要放棄購買嗎?")
                    .setTitle("警告!!!!")
                    .setPositiveButton("確定") { dialog, which ->
                        Navigation.findNavController(requireView()).navigateUp()
                    }
                    .setNegativeButton("取消", null)
                    .show()
            }
            binding.rbBuyWay2.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    binding.editTextNumber.visibility = View.VISIBLE
                    binding.tvPoint.visibility = View.VISIBLE
                    binding.tvPoints.visibility = View.VISIBLE
                } else {
                    binding.editTextNumber.visibility = View.GONE
                    binding.tvPoint.visibility = View.GONE
                    binding.tvPoints.visibility = View.GONE
                }
            }

        }
    }
}