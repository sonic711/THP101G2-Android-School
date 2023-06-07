package com.example.thp101g2_android_school.firm.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.firm.model.FirmProduct
import org.json.JSONObject

class FirmProductManagerViewModel : ViewModel() {
    val productEdit : MutableLiveData<FirmProduct> by lazy { MutableLiveData<FirmProduct>() }
    val finished : MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    fun doPUT(){
        println(productEdit.value)
       val result = requestTask<JSONObject>("$url/productmanage", "PUT", productEdit.value)
        println(result)
        finished.value = true


    }
}