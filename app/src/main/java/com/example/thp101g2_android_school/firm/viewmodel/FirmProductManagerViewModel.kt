package com.example.thp101g2_android_school.firm.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.firm.model.Firm
import com.example.thp101g2_android_school.firm.model.FirmAll
import com.example.thp101g2_android_school.firm.model.FirmProduct
import org.json.JSONObject

class FirmProductManagerViewModel : ViewModel() {
    val productEdit : MutableLiveData<FirmProduct> by lazy { MutableLiveData<FirmProduct>() }
    val finished : MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    fun doPUT(){
        println(productEdit.value)
//       val result = requestTask<JSONObject>("$url/productmanage", "PUT", productEdit.value)
//        println(result)
        val result = FirmAll(
        shopProductId = productEdit.value?.shopProductId,
        shopProductName = productEdit.value?.shopProductName,
        shopProductPrice = productEdit.value?.shopProductPrice,
        shopProductSearch = productEdit.value?.shopProductSearch,
        shopProductClass = productEdit.value?.shopProductClass,
        shopProductDesc = productEdit.value?.shopProductDesc,
        shopProductStatus = productEdit.value?.shopProductStatus,
        shopProductCount = productEdit.value?.shopProductCount,
        shopName = productEdit.value?.shopName,
        firmNo = productEdit.value?.firmNo,
        shopProductImg = productEdit.value?.shopProductImg
        )
        var currentFirm: Firm? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/firms", "OPTIONS")
        val FNO = currentFirm?.firmNo
        val respbody = requestTask<JSONObject>("$url/productmanage/$FNO", "PUT", reqBody = result)
        finished.value = true


    }
}