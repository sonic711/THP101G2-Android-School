package com.example.thp101g2_android_school.firm.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.renderscript.ScriptGroup.Binding
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.firm.model.Firm
import com.example.thp101g2_android_school.firm.model.FirmAll
import com.example.thp101g2_android_school.firm.model.FirmProduct
import org.json.JSONObject
import java.io.ByteArrayOutputStream

class FirmProductOnViewModel : ViewModel() {
    val productOn : MutableLiveData<FirmProduct> by lazy { MutableLiveData<FirmProduct>(FirmProduct()) }

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

//    若用onClick1綁定點及事件，可用此方法
//    fun doPOST(context:Context){
//        productOn.value?.firmNo = "3"
//        println(productOn.value)
//        var currentFirm: Firm? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/firms", "OPTIONS")
//        val FNO = currentFirm?.firmNo
//        val result = requestTask<JSONObject>("$url/productmanage/3", "POST", productOn.value)
//        println(result)
//
//        if (productOn.value?.shopProductDesc !=null) {
//            // 請求成功，執行相應的操作
//            Toast.makeText(context, "上架成功", Toast.LENGTH_SHORT).show()
//        } else {
//            // 請求失敗，執行相應的操作
//            Toast.makeText(context, "上架失敗", Toast.LENGTH_SHORT).show()
//        }
//    }

}