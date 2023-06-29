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
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.databinding.FragmentShopBuyBinding
import com.example.thp101g2_android_school.shop.model.ShopingCart
import com.example.thp101g2_android_school.shop.viewmodel.ShopingCartViewModel
import com.google.gson.JsonObject
import android.util.Base64
import com.example.thp101g2_android_school.GooglePayActivity
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.member.model.Member
import com.example.thp101g2_android_school.shop.model.ShopBuyCalss
import com.google.firebase.firestore.core.ViewChange
import com.google.gson.reflect.TypeToken


class ShopBuyFragment : Fragment() {
    private lateinit var binding: FragmentShopBuyBinding
    private var quantity = 0
    private val ERROR_COLOR = Color.RED
    private var radioButtonSelected = false
    private var radioButtonError = false
    private var userEmail = ""
    private var userName = ""
    private var userPhone = ""
    var pointans = 0
    var ViewChange = false


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
        binding.btGPay.visibility = View.GONE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /**
         * TODO 66~78:去拿Member資料
         */

        var currentMember: Member? =
            requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/members", "OPTIONS")
        println(currentMember)
        val points = currentMember?.rewardPoints
        println("擁有積分: " + points)
        binding.tvPoints.text = "您的積分總額 : " + points
        val memberno = currentMember?.memberNo
        if (currentMember != null) {
            userEmail = currentMember.memberEmail
        }
        if (currentMember != null) {
            userName = currentMember.nickname
        }
        if (currentMember != null) {
            userPhone = currentMember.phoneNumber
        }
        println(userEmail)
        println(memberno)


        /**
         * TODO 將cartproduct.shopProductCount的資料和後端做比對，使用者只能買到商品最大數量。
         */

        arguments?.let { bundle ->
            val shopCartList = bundle.getParcelableArray("cartproduct")?.let { array ->
                array.mapNotNull { it as? ShopingCart }
            } ?: listOf()
            val amountList = bundle.getIntArray("quantity")?.let { it.toList() } ?: listOf()
            for ((index, shopCart) in shopCartList.withIndex()) {
                val amount = amountList[index]
                println("商品數量 :${shopCart.shopProductCount}" + "     " + "商品ID :${shopCart.shopProductId}")
                println("廠商代號 : ${shopCart.firmNo}")
                println("商品價格 : ${shopCart.shopProductPrice}")
                println("我購買的數量 :$amount")
            }
//            bundle.getSerializable("cartproduct")?.let {
//                cartproduct = it as Array
//                binding.viewModel?.cartproduct?.value = cartproduct
//                println("商品數量 :${cartproduct.shopProductCount}" + "     " +"商品ID :${cartproduct.shopProductId}")
//                println("廠商代號 : ${cartproduct.firmNo}")
//                println("商品價格 : ${cartproduct.shopProductPrice}")
//
//
//
//            }
//            arguments?.let { bundle ->
//                quantity = bundle.getInt("quantity")
//                println("我購買的數量 :$quantity")
//            }
//            val memberpoints = cartproduct?.rewardPoints
//            binding.tvPoints.text = "您的積分總額 : "+memberpoints
//            println(memberpoints)
//            val productid = cartproduct.shopProductId.toInt()
//            val productname = cartproduct.shopProductName
//            val firmno = cartproduct.firmNo
//            val productimg = cartproduct.shopProductImage
//            var pointquantity = binding.editTextNumber.text.toString().toIntOrNull()
//            var hasError: Boolean = false
//
//            var Count = cartproduct.shopProductCount - quantity
//            println("資料庫要變動的數量 :$Count")
//            val price = cartproduct.shopProductPrice.toInt() * quantity
//            println("我的購買金額: $price")

//            binding.tvBuyPrice.text = "總金額 : ${price}"


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
            binding.rbBuyWay.setOnCheckedChangeListener { _, isChecked ->
                radioButtonSelected = isChecked
                if (isChecked) {
                    binding.btGPay.visibility = View.VISIBLE
                } else {
                    binding.btGPay.visibility = View.GONE
                }
            }
            binding.rbBuyWay2.setOnCheckedChangeListener { _, isChecked ->
                radioButtonSelected = isChecked
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
            /**
             * TODO 跳轉googlepay頁面
             */

//            binding.btGPay.setOnClickListener {
//                val name = binding.etPersonName.text.toString()
//                val email = binding.etEmail.text.toString()
//                val address = binding.etAddress.text.toString()
//                val phone = binding.etPhone.text.toString()
//
//                var hasError = false
//
//                if (name.isEmpty() || name.length < 2 || name != userName) {
//                    // 姓名验证失败，显示错误信息
//                    binding.etPersonName.setError("姓名輸入失敗，請至少輸入兩個字元")
//                    setErrorState(binding.etPersonName)
//                    hasError = true
//                } else {
//                    // 验证成功，恢复原始颜色
//                    clearErrorState(binding.etPersonName)
//                }
//
//                if (email.isEmpty() || !isEmailValid(email) || email != userEmail) {
//                    // 邮箱验证失败，显示错误信息
//                    binding.etEmail.setError("電子郵件驗證失敗")
//                    setErrorState(binding.etEmail)
//                    hasError = true
//                } else {
//                    // 验证成功，恢复原始颜色
//                    clearErrorState(binding.etEmail)
//                }
//                if (phone.isEmpty() || !isPhoneValid(phone) || phone != userPhone) {
//                    // 手机号验证失败，显示错误信息
//                    binding.etPhone.setError("手機、電話號碼格式不正確")
//                    setErrorState(binding.etPhone)
//                    hasError = true
//                } else {
//                    // 验证成功，恢复原始颜色
//                    clearErrorState(binding.etPhone)
//                }
//
//                if (address.isEmpty() || !isAddressValid(address)) {
//                    // 地址验证失败，显示错误信息
//                    binding.etAddress.setError("地址格式不正確，請重新輸入")
//                    setErrorState(binding.etAddress)
//                    hasError = true
//                } else {
//                    // 验证成功，恢复原始颜色
//                    clearErrorState(binding.etAddress)
//                }
//
//                if (!hasError) {
//                    val intent = Intent(requireContext(), GooglePayActivity::class.java)
//                    // 添加要传递的数据到Intent的Extra中
//                    intent.putExtra("cartproduct", cartproduct as java.io.Serializable)
//                    intent.putExtra("quantity", quantity)
//                    intent.putExtra("price",price)
//                    intent.putExtra("name",name)
//                    intent.putExtra("email",email)
//                    intent.putExtra("address",address)
//                    intent.putExtra("phone",phone)
//                    intent.putExtra("productid",productid)
//                    intent.putExtra("memberno",memberno)
//                    intent.putExtra("productname",productname)
//                    intent.putExtra("firmno",firmno)
//                    intent.putExtra("productimg",productimg)
//                    intent.putExtra("count",Count)
//                    // 启动下一个页面
//                    startActivity(intent)
//                } else {
//                    Toast.makeText(context, "付款失敗", Toast.LENGTH_SHORT).show()
//                }
//            }
//            binding.btComFirm.setOnClickListener {
//                if (!radioButtonSelected) {
//                    radioButtonError = true
//                    binding.rbBuyWay.setButtonTintList(ColorStateList.valueOf(Color.RED))
//                    binding.rbBuyWay2.setButtonTintList(ColorStateList.valueOf(Color.RED))
//                    Toast.makeText(context, "请选择一种付款方式", Toast.LENGTH_SHORT).show()
//                    hasError = true
//                } else {
//                    binding.rbBuyWay.setButtonTintList(null)
//                    binding.rbBuyWay2.setButtonTintList(null)
//                    hasError = false
//                }
//                var pointquantity = binding.editTextNumber.text.toString().toIntOrNull()
//                hasError = false
//                if (pointquantity == null) {
//                    binding.editTextNumber.setError("不可為空")
//                    setErrorState(binding.editTextNumber)
//                    hasError = true
//                } else if (pointquantity!! > memberpoints!!) {
//                    // 数量超过奖励积分，显示错误信息
//                    binding.editTextNumber.setError("不能超過你擁有的積分")
//                    setErrorState(binding.editTextNumber)
//                    hasError = true
//                } else if (pointquantity!! < price) {
//                    binding.editTextNumber.setError("您的積分不夠支付")
//                    setErrorState(binding.editTextNumber)
//                    hasError = true
//                } else if (pointquantity!! > price) {
//                    Toast.makeText(context, "積分使用過多", Toast.LENGTH_SHORT).show()
//                    pointquantity = price
//                    hasError = true
//                } else {
//                    pointans = memberpoints - pointquantity!!
//                    clearErrorState(binding.editTextNumber)
//                    println(pointans)
//                }
//                val name = binding.etPersonName.text.toString()
//                val email = binding.etEmail.text.toString()
//                val address = binding.etAddress.text.toString()
//                val phone = binding.etPhone.text.toString()
//
//                if (name.isEmpty() || name.length < 2 || name != userName) {
//                    // 姓名验证失败，显示错误信息
//                    binding.etPersonName.setError("姓名輸入失敗，請至少輸入兩個字元")
//                    setErrorState(binding.etPersonName)
//                    hasError = true
//                } else {
//                    // 验证成功，恢复原始颜色
//                    clearErrorState(binding.etPersonName)
//                }
//
//                if (email.isEmpty() || !isEmailValid(email) || email != userEmail) {
//                    // 邮箱验证失败，显示错误信息
//                    binding.etEmail.setError("電子郵件驗證失敗")
//                    setErrorState(binding.etEmail)
//                    hasError = true
//                } else {
//                    // 验证成功，恢复原始颜色
//                    clearErrorState(binding.etEmail)
//                }
//
//                if (address.isEmpty() || !isAddressValid(address)) {
//                    // 地址验证失败，显示错误信息
//                    binding.etAddress.setError("地址格式不正確，請重新輸入")
//                    setErrorState(binding.etAddress)
//                    hasError = true
//                } else {
//                    // 验证成功，恢复原始颜色
//                    clearErrorState(binding.etAddress)
//                }
//
//                if (phone.isEmpty() || !isPhoneValid(phone) || phone != userPhone) {
//                    // 手机号验证失败，显示错误信息
//                    binding.etPhone.setError("手機、電話號碼格式不正確")
//                    setErrorState(binding.etPhone)
//                    hasError = true
//                } else {
//                    // 验证成功，恢复原始颜色
//                    clearErrorState(binding.etPhone)
//                }
//
//                if (!hasError) {
//                        Toast.makeText(context, "付款成功", Toast.LENGTH_SHORT).show()
//                    println("Order增加一筆")
//                    val shopBuy = ShopBuyCalss(
//                        shopProductId = productid,
//                        memberNo = memberno,
//                        shopAddress = address,
//                        shopRecipient = name,
//                        shopOrderPhone = phone,
//                        shopPointDiscount = pointquantity,
//                        shopProductSales = price,
//                        shopProductName = productname,
//                        firmNo = firmno,
//                        shopOrderCount = quantity,
//                        shopOrderImage = productimg
//                    )
//
//                    val respbody = requestTask<JsonObject>(
//                        "$url/shop/buy/",
//                        "POST", reqBody = shopBuy
//                    )
//                    val jsonObj2 = JsonObject()
//                    val urldelete = "$url/shop/buy/$productid"
//                    val respbody2 = requestTask<JsonObject>(urldelete, "DELETE")
//
//                    val jsonObj3 = JsonObject()
//                    jsonObj3.addProperty("shopProductCount", Count)
//                    jsonObj3.addProperty("shopProductId", productid)
//                    val respbody3 = requestTask<JsonObject>(
//                        "$url/shop/buy/",
//                        "PUT", jsonObj3
//                    )
//                    val jsonObj4 = JsonObject()
//                    jsonObj4.addProperty("rewardPoints", pointans)
//                    jsonObj4.addProperty("memberNo", memberno)
//                    val respbody4 = requestTask<JsonObject>(
//                        "$url/shop/buy/point",
//                        "PUT", jsonObj4
//                    )
//                    /**
//                     *  TDOO 以下是積分折抵後的積分使用狀況後端更新.Ian //278~289行
//                     */
//                    val postUrl = "http://10.0.2.2:8080/THP101G2-WebServer-School/point"
//                    val requestBody = mapOf(
//                        "type" to "insertForSO",
//                    )
//                    val responseType = object : TypeToken<JsonObject>() {}.type
//                    val response = requestTask<JsonObject>(
//                        postUrl, "POST",
//                        requestBody, responseType)
//
//                    Navigation.findNavController(requireView()).navigateUp()
//
//                } else {
//                        Toast.makeText(context, "付款失敗", Toast.LENGTH_SHORT).show()
//                }
//            }

        }
    }

    override fun onResume() {
        super.onResume()
        val receivedData = requireActivity().intent
        val fragmentName = receivedData.getStringExtra("fragment")
        val viewChange = receivedData.getBooleanExtra("viewchange", false)
        println(viewChange)
        if (viewChange) {
            Navigation.findNavController(binding.btGPay).navigate(R.id.shopOrderListFragment)
            println("yes")
        } else {
            println("no")
        }

    }

    fun convertBytesToBase64(bytes: ByteArray): String {
        return Base64.encodeToString(bytes, Base64.DEFAULT)
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