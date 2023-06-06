package com.example.thp101g2_android_school.shop.viewmodel

import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.shop.model.PointSelect
import com.example.thp101g2_android_school.shop.model.Product
import com.example.thp101g2_android_school.shop.model.ShopingCart

class ShopingCartViewModel : ViewModel() {
    val cartproduct: MutableLiveData<ShopingCart> by lazy { MutableLiveData<ShopingCart>() }
    val pointselect:MutableLiveData<PointSelect> by lazy {MutableLiveData<PointSelect>()}

    companion object {
        @BindingAdapter("imageByteArray")
        @JvmStatic
        fun setImageByteArray(imageView: ImageView, byteArray: ByteArray?) {
            byteArray?.let {
                val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                imageView.setImageBitmap(bitmap)
            }
        }
    }
}