package com.example.thp101g2_android_school.firm.viewmodel

import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.firm.model.FirmProduct

/**
 * 單一商品資料處理
 */
class FirmMainViewModel : ViewModel() {
    val firmProduct : MutableLiveData<FirmProduct> by lazy { MutableLiveData<FirmProduct>() }
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