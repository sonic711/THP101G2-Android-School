package com.example.thp101g2_android_school.shop.controller

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.databinding.FragmentShopBuyBinding
import com.example.thp101g2_android_school.shop.model.Product
import com.example.thp101g2_android_school.shop.model.ShopingCart
import com.example.thp101g2_android_school.shop.viewmodel.ShopMainViewModel
import com.example.thp101g2_android_school.shop.viewmodel.ShopingCartViewModel
import com.google.gson.JsonObject
import kotlin.random.Random


class ShopBuyFragment : Fragment() {
    private lateinit var binding: FragmentShopBuyBinding
    private lateinit var cartproduct: ShopingCart
    private var quantity = 0
    private val ERROR_COLOR = Color.RED


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
         * 將cartproduct的資料和後端做比對，只能買到商品最大數量。
         */

        arguments?.let { bundle ->
            bundle.getSerializable("cartproduct")?.let {
                cartproduct = it as ShopingCart
                binding.viewModel?.cartproduct?.value = cartproduct
                println("${cartproduct.shopProductCount}" + "${cartproduct.shopProductId}")


            }
            arguments?.let { bundle ->
                quantity = bundle.getInt("quantity")
                println(quantity)
            }

            var Count = cartproduct.shopProductCount - quantity
            println(Count)



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
                    binding.editTextNumber.text = null
                }
            }
            binding.button5.setOnClickListener {
                val name = binding.etPersonName.text.toString()
                val email = binding.etEmail.text.toString()
                val address = binding.etAddress.text.toString()
                val phone = binding.etPhone.text.toString()
                var hasError = false

                if (name.isEmpty() || name.length < 2) {
                    // 姓名验证失败，显示错误信息
                    binding.etPersonName.setError("姓名輸入失敗，請至少輸入兩個字元")
                    setErrorState(binding.etPersonName)
                    hasError = true
                } else {
                    // 验证成功，恢复原始颜色
                    clearErrorState(binding.etPersonName)
                }

                if (email.isEmpty() || !isEmailValid(email)) {
                    // 邮箱验证失败，显示错误信息
                    binding.etEmail.setError("電子郵件驗證失敗")
                    setErrorState(binding.etEmail)
                    hasError = true
                } else {
                    // 验证成功，恢复原始颜色
                    clearErrorState(binding.etEmail)
                }

                if (address.isEmpty() || !isAddressValid(address)) {
                    // 地址验证失败，显示错误信息
                    binding.etAddress.setError("地址格式不正確，請重新輸入")
                    setErrorState(binding.etAddress)
                    hasError = true
                } else {
                    // 验证成功，恢复原始颜色
                    clearErrorState(binding.etAddress)
                }

                if (phone.isEmpty() || !isPhoneValid(phone)) {
                    // 手机号验证失败，显示错误信息
                    binding.etPhone.setError("手機、電話號碼格式不正確")
                    setErrorState(binding.etPhone)
                    hasError = true
                } else {
                    // 验证成功，恢复原始颜色
                    clearErrorState(binding.etPhone)
                }

                if (hasError) {
                    // 至少有一个字段验证失败，执行相应逻辑
                    // ...
                } else {
//                    println("PDF購物車增加一筆")
//                    val randomNumber = Random.nextInt(10000000)
//                    val jsonObj = JsonObject()
//                    jsonObj.addProperty("shoppingCartId", randomNumber)
//                    jsonObj.addProperty("memberNo", 1)
//                    jsonObj.addProperty("shopProductId", product.shopProductId)
//                    jsonObj.addProperty("shopOrderCount", product.shopProductCount)
//                    val respbody = requestTask<JsonObject>(
//                        "$url/shop/shoppingcart",
//                        "POST", jsonObj
//                    )
                    // 所有字段验证成功，执行相应逻辑
                    // ...
                }
            }

        }
    }

    private fun setErrorState(editText: EditText) {
        editText.backgroundTintList = ColorStateList.valueOf(ERROR_COLOR)
    }

    private fun clearErrorState(editText: EditText) {
        editText.backgroundTintList = null
    }

    // 邮箱格式验证函数
    private fun isEmailValid(email: String): Boolean {
        // 使用正则表达式验证邮箱格式
        val pattern = "^([\\w.-]+)@([\\w]+\\.[\\w]+)$"
        return email.matches(Regex(pattern))
    }

    // 地址格式验证函数
    private fun isAddressValid(address: String): Boolean {
        // 这里可以添加对地址格式的验证规则
        // 根据你的需求自定义规则并返回验证结果
        return true
    }

    // 手机号格式验证函数
    private fun isPhoneValid(phone: String): Boolean {
        // 使用正则表达式验证手机号格式
        val pattern = "^(0|\\+?\\d{2})?\\d{10}\$"
        return phone.matches(Regex(pattern))
    }
}