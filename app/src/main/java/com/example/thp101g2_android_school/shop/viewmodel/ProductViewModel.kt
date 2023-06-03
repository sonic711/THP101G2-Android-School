package com.example.thp101g2_android_school.shop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.shop.model.Product
import com.example.thp101g2_android_school.shop.model.ShopFavorite
import com.example.thp101g2_android_school.shop.model.ShopingCart
import com.google.gson.reflect.TypeToken

class ProductViewModel : ViewModel() {
    private var productList = mutableListOf<Product>()
    val products : MutableLiveData<List<Product>>by lazy {MutableLiveData<List<Product>>()}
    var favoriteProducts = MutableLiveData<List<ShopFavorite>>()
    var shoppingCartProducts = MutableLiveData<List<ShopingCart>>()

    init {
        loadProduct()
        loadFavoriteProducts()
        loadShoppingCartProducts()
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
    fun getFavoriteProducts(): LiveData<List<ShopFavorite>> {
        return favoriteProducts
    }
    fun getShoppingCartProducts(): LiveData<List<ShopingCart>>{
        return shoppingCartProducts
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
    private fun loadFavoriteProducts() {
        // Implement the logic to load favorite products and update the favoriteProducts LiveData
        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/shop/favorite"
        val type = object : TypeToken<List<ShopFavorite>>() {}.type

        // 发起网络请求，获取喜爱的产品数据
        val favoriteProductList = requestTask<List<ShopFavorite>>(url, respBodyType = type) ?: return

        // 将获取的喜爱的产品数据更新到 favoriteProducts 的 LiveData 对象中
        favoriteProducts.value = favoriteProductList
    }

    private fun loadShoppingCartProducts() {
        // Implement the logic to load favorite products and update the favoriteProducts LiveData
        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/shop/shoppingcart"
        val type = object : TypeToken<List<ShopingCart>>() {}.type

        // 发起网络请求，获取喜爱的产品数据
        val shoppingCartProductList = requestTask<List<ShopingCart>>(url, respBodyType = type) ?: return

        // 将获取的喜爱的产品数据更新到 favoriteProducts 的 LiveData 对象中
        shoppingCartProducts.value = shoppingCartProductList
    }

    fun onFavoriteProductClicked(productId: String) {
        val product = products.value?.find { it.shopProductId == productId } ?: return
        val favoriteProductList = favoriteProducts.value ?: listOf()
        val isFavorite = favoriteProductList.any { it.shopProductId == productId } ?: false
        if (isFavorite) {
            println("PVM刪除一筆")
            favoriteProducts.value = favoriteProductList.dropWhile { it.shopProductId == productId }
        } else {
            println("PVM增加一筆")
            favoriteProducts.value = favoriteProductList.plus(product.toShopFavorite())
        }
        products.value = products.value
    }
//    fun onShoppingCartProductClicked(productId: String) {
//        val product = products.value?.find { it.shopProductId == productId } ?: return
//        val shoppingCartProductList = shoppingCartProducts.value ?: listOf()
//        val isShoppingCart = shoppingCartProductList.any { it.shopProductId == productId } ?: false
//        if (isShoppingCart) {
//            println("刪除一筆")
//            shoppingCartProducts.value = shoppingCartProductList.dropWhile { it.shopProductId == productId }
//        } else {
//            println("增加一筆")
//            shoppingCartProducts.value = shoppingCartProductList.plus(product.toShoppingCart())
//        }
//        products.value = products.value
//    }

}