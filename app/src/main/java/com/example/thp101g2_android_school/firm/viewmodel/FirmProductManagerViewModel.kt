package com.example.thp101g2_android_school.firm.viewmodel

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.firm.model.Firm
import com.example.thp101g2_android_school.firm.model.FirmAll
import com.example.thp101g2_android_school.firm.model.FirmProduct
import com.example.thp101g2_android_school.firm.model.FirmShopData
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class FirmProductManagerViewModel : ViewModel() {
    val productEdit: MutableLiveData<FirmProduct> by lazy { MutableLiveData<FirmProduct>() }
    val finished: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val firm: MutableLiveData<Firm> by lazy { MutableLiveData<Firm>(Firm()) }


    fun loadProducts(shopProductId: String) {
        val currentFirm: Firm? =
            requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/firms", "OPTIONS")
            firm.value = currentFirm
        productEdit.value?.shopProductId = shopProductId
        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/productstatus/${productEdit.value?.shopProductId}"
        val result = requestTask<FirmProduct>(url)
        result?.let {
            productEdit.value = it
        }
    }

    fun changeStatus(context: Context) {
        AlertDialog.Builder(context)
            .setMessage("確定下架?")
            .setPositiveButton("是") { _, _ ->
                var currentFirm: Firm? = requestTask(
                    "http://10.0.2.2:8080/THP101G2-WebServer-School/firms",
                    "OPTIONS"
                )
                val FNO = currentFirm?.firmNo
                val result = requestTask<Boolean>("$url/productstatus/$FNO", "PUT", productEdit.value)
                finished.value = result
                if (finished.value == true) {
                    Toast.makeText(context, "下架成功", Toast.LENGTH_SHORT).show()
                }else Toast.makeText(context, "下架失敗", Toast.LENGTH_SHORT).show()

            }
            .setNegativeButton("否", null)
            .setCancelable(false)
            .show()

    }

    // 若是用onClick點擊事件可綁定此方法
//    fun doPUT(){
//        println(productEdit.value)
//        var currentFirm: Firm? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/firms", "OPTIONS")
//        val FNO = currentFirm?.firmNo
//        val result = requestTask<JSONObject>("$url/productmanage/$FNO", "PUT", productEdit.value)
//        println(result)
//
//        finished.value = true
//    }
}