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
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.firm.model.Firm
import com.example.thp101g2_android_school.firm.model.FirmProduct
import com.example.thp101g2_android_school.firm.model.FirmShopData
import com.example.thp101g2_android_school.firm.model.Order
import com.example.thp101g2_android_school.member.model.Member
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

/**
 *  僅為變數，需要時可使用
 *  private var currentMember: Firm? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/firmData", "OPTIONS")
 * */

class FirmShopSettingViewModel :ViewModel() {
    val firm: MutableLiveData<Firm> by lazy { MutableLiveData<Firm>() }
    val navToLogin: MutableLiveData<Boolean> by lazy { MutableLiveData(false) }
    val newPassword: MutableLiveData<String> by lazy { MutableLiveData() }
    val confirmPassword: MutableLiveData<String> by lazy { MutableLiveData("") }

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
    fun init(){
        loadFirmMember()
    }

    private fun loadFirmMember() {
        firm.value?.firmNo=3

        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/firmData/3"
        val type = object : TypeToken<Firm>() {}.type
        val result = requestTask<Firm>(url, respBodyType = type)
//        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/firmData/3"
//        val result = requestTask<Firm>(url)
        println("result= $result")
        result?.let {
            firm.value = it
        }
    }
    fun doPUT(){
        val result = requestTask<JSONObject>("$url/firmData", "PUT", firm.value)
        println(result)
    }

//    fun logout(context: Context) {
//        AlertDialog.Builder(context)
//            .setMessage("確定要登出?")
//            .setPositiveButton("Yes") { _, _ ->
//                requestTask<Unit>(url + "firmData", "DELETE")
//                navToLogin.value = true
//            }
//            .setNegativeButton("No", null)
//            .setCancelable(false)
//            .show()
//    }

}