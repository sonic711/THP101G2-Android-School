package com.example.thp101g2_android_school.shop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.shop.model.Product
import com.example.thp101g2_android_school.shop.model.ShopFavorite

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
                if ( favoriteproduct.name.contains(newText, true)) {
                    searchLessionList.add( favoriteproduct)
                }
            }
            // 指派給LiveData 就可以即時刷新View
            favoriteproducts.value = searchLessionList
        }
    }

    private fun loadProduct() {
        val productList = mutableListOf<ShopFavorite>()
        productList.add(ShopFavorite(R.drawable.java, "java基礎教科書", "Willian"))
        productList.add(ShopFavorite(R.drawable.kotlin, "kotlin進階教科書", "黃彬華老師.註"))
        productList.add(ShopFavorite(R.drawable.python, "python高階教科書", "Jerry"))
        productList.add(ShopFavorite(R.drawable.senpai, "仲夏夜X夢兒童教育有聲書", "AHHHH"))



        this.favoriteproductList = productList
        this.favoriteproducts.value = this.favoriteproductList
    }
}