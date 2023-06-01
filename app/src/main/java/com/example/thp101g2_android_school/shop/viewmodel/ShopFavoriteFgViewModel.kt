package com.example.thp101g2_android_school.shop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.shop.model.Product
import com.example.thp101g2_android_school.shop.model.ShopFavorite
import com.google.gson.reflect.TypeToken

class ShopFavoriteFgViewModel : ViewModel() {
    private var favoriteproductList = mutableListOf<ShopFavorite>()
    val favoriteproducts : MutableLiveData<List<ShopFavorite>>by lazy {MutableLiveData<List<ShopFavorite>>()}

    init {
        loadProduct()
    }
    fun search(newText: String?) {
        if (newText.isNullOrEmpty()) {
            favoriteproducts.value = favoriteproductList
        } else {
            // 如果不是空字串，宣告新的集合，走訪每個元素的name屬性，如果符合就放到新的集合並指派
            val searchLessionList = mutableListOf<ShopFavorite>()
            favoriteproductList.forEach { favoriteproduct ->
                if ( favoriteproduct.shopProductName.contains(newText, true)) {
                    searchLessionList.add( favoriteproduct)
                }
            }
            // 指派給LiveData 就可以即時刷新View
            favoriteproducts.value = searchLessionList
        }
    }

    fun loadProduct() {
        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/shop/favorite"
        val type = object : TypeToken<List<ShopFavorite>>() {}.type
        val list = requestTask<List<ShopFavorite>>(url, respBodyType = type) ?: return
        val productList = mutableListOf<ShopFavorite>()


        for (shopfavorite in list!!) {
            productList.add(shopfavorite)
        }



        this.favoriteproductList = productList
        this.favoriteproducts.value = this.favoriteproductList
    }
}