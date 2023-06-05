    package com.example.thp101g2_android_school.shop.viewmodel

    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModel
    import com.example.thp101g2_android_school.shop.model.Product
    import com.example.thp101g2_android_school.shop.model.ShopFavorite

    class ShopFavoriteViewModel : ViewModel() {
        val favoriteproduct: MutableLiveData<ShopFavorite> by lazy { MutableLiveData<ShopFavorite>() }
    }