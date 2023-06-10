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
    val productEdit : MutableLiveData<FirmProduct> by lazy { MutableLiveData<FirmProduct>() }
    val finished : MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }


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

    fun changeStatus(context: Context){
        AlertDialog.Builder(context)
            .setMessage("確定下架?")
            .setPositiveButton("是") { _, _ ->
                var currentFirm: Firm? = requestTask(
                    "http://10.0.2.2:8080/THP101G2-WebServer-School/firms",
                    "OPTIONS"
                )
                val FNO = currentFirm?.firmNo
                requestTask<Unit>("$url/productstatus/$FNO", "PUT",productEdit.value)
                finished.value = true
                if (finished.value == true) {
                    Toast.makeText(context, "下架成功", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("否", null)
            .setCancelable(false)
            .show()

//        var currentFirm: FirmProduct? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/productstatus", "OPTIONS")
//        val FNO = currentFirm?.shopProductId
//        val result = requestTask<JSONObject>("$url/productstatus/$FNO", "PUT", productEdit.value)
//        println(result)
//
//        finished.value = true

    }
}