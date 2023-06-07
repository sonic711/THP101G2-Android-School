package com.example.thp101g2_android_school.firm.viewmodel

import android.content.Context
import android.renderscript.ScriptGroup.Binding
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.firm.model.Firm
import com.example.thp101g2_android_school.firm.model.FirmAll
import com.example.thp101g2_android_school.firm.model.FirmProduct
import org.json.JSONObject

class FirmProductOnViewModel : ViewModel() {
    val productOn : MutableLiveData<FirmProduct> by lazy { MutableLiveData<FirmProduct>(FirmProduct()) }
    fun doPOST(context:Context){
        // 寫死廠商編號為1的廠商
//        productOn.value?.firmNo = "3"
//        productOn.value?.shopName = "土康三號流水線"
//        println(productOn.value)
//        val result = requestTask<JSONObject>("$url/productmanage", "POST", productOn.value)
//        println(result)

        val result = FirmAll(
            shopProductId = productOn.value?.shopProductId,
            shopProductName = productOn.value?.shopProductName,
            shopProductPrice = productOn.value?.shopProductPrice,
            shopProductSearch = productOn.value?.shopProductSearch,
            shopProductClass = productOn.value?.shopProductClass,
            shopProductDesc = productOn.value?.shopProductDesc,
            shopProductStatus = productOn.value?.shopProductStatus,
            shopProductCount = productOn.value?.shopProductCount,
            shopName = productOn.value?.shopName,
            firmNo = productOn.value?.firmNo,
            shopProductImg = productOn.value?.shopProductImg
        )
        var currentFirm: Firm? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/firms", "OPTIONS")
        val FNO = currentFirm?.firmNo
        val respbody = requestTask<JSONObject>("$url/productmanage/$FNO", "PUT", reqBody = result)

        if (productOn.value?.shopProductDesc !=null) {
            // 請求成功，執行相應的操作
            // 例如顯示 Toast
            Toast.makeText(context, "上架成功", Toast.LENGTH_SHORT).show()
        } else {
            // 請求失敗，執行相應的操作
            // 例如顯示錯誤訊息或重新嘗試
            Toast.makeText(context, "上架失敗", Toast.LENGTH_SHORT).show()
        }
    }

}