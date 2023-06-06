package com.example.thp101g2_android_school.firm.viewmodel

import android.content.Context
import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.firm.model.Firm
import com.example.thp101g2_android_school.firm.model.Order
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

class FirmShopSettingViewModel :ViewModel() {
    val firm: MutableLiveData<Firm> by lazy { MutableLiveData<Firm>() }

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