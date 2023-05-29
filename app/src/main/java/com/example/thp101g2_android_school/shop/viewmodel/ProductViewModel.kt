package com.example.thp101g2_android_school.shop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.community.model.Label
import com.example.thp101g2_android_school.shop.model.Product
import com.google.gson.reflect.TypeToken

class ProductViewModel : ViewModel() {
    private var productList = mutableListOf<Product>()
    val products : MutableLiveData<List<Product>>by lazy {MutableLiveData<List<Product>>()}

    init {
        loadProduct()
    }
    fun search(newText: String?) {
        if (newText.isNullOrEmpty()) {
            products.value = productList
        } else {
            // 如果不是空字串，宣告新的集合，走訪每個元素的name屬性，如果符合就放到新的集合並指派
            val searchLessionList = mutableListOf<Product>()
            productList.forEach { product ->
                if (product.shopProductName.contains(newText, true)) {
                    searchLessionList.add(product)
                }
            }
            // 指派給LiveData 就可以即時刷新View
            products.value = searchLessionList
        }
    }

    private fun loadProduct() {
        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/shop/"
        val type = object : TypeToken<List<Product>>() {}.type
        val list = requestTask<List<Product>>(url, respBodyType = type) ?: return
        val productList = mutableListOf<Product>()

        for (product in list!!) {
            productList.add(product)
        }


        this.productList = productList
        this.products.value = this.productList
    }
}