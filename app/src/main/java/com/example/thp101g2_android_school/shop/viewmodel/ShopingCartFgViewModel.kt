package com.example.thp101g2_android_school.shop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.shop.model.ShopingCart
import com.google.gson.reflect.TypeToken

class ShopingCartFgViewModel : ViewModel() {
    private var cartproductList = mutableListOf<ShopingCart>()
    val cartproducts : MutableLiveData<List<ShopingCart>>by lazy {MutableLiveData<List<ShopingCart>>()}

    init {
        loadProduct()
    }
    fun search(newText: String?) {
        if (newText.isNullOrEmpty()) {
            cartproducts.value = cartproductList
        } else {
            // 如果不是空字串，宣告新的集合，走訪每個元素的name屬性，如果符合就放到新的集合並指派
            val searchLessionList = mutableListOf<ShopingCart>()
            cartproductList.forEach {cartproduct ->
                if (cartproduct.shopProductName.contains(newText, true)) {
                    searchLessionList.add(cartproduct)
                }
            }
            // 指派給LiveData 就可以即時刷新View
            cartproducts.value = searchLessionList
        }
    }

    fun loadProduct() {
        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/shop/shoppingcart/"
        val type = object : TypeToken<List<ShopingCart>>() {}.type
        val list = requestTask<List<ShopingCart>>(url, respBodyType = type) ?: return
        val productList = mutableListOf<ShopingCart>()

        for (shopfavorite in list!!) {
            productList.add(shopfavorite)
        }



        this.cartproductList = productList
        this.cartproducts.value = this.cartproductList
    }
}