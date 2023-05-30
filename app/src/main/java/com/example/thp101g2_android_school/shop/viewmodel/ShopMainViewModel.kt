    package com.example.thp101g2_android_school.shop.viewmodel

    import android.util.Log
    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModel
    import com.example.thp101g2_android_school.shop.model.Product

    class ShopMainViewModel: ViewModel() {
        val product: MutableLiveData<Product> by lazy { MutableLiveData<Product>() }
        val productid : String? = product.value?.shopProductId ?: ""

        fun addtofavorite(){
            println(productid)
            Log.i("productid",product.value?.shopProductId.toString())
        }
    }