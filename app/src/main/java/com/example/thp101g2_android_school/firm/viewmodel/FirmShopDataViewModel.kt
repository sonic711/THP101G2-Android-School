package com.example.thp101g2_android_school.firm.viewmodel

import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.firm.model.Firm
import com.example.thp101g2_android_school.firm.model.FirmProduct
import com.example.thp101g2_android_school.firm.model.FirmShopData
import com.google.gson.reflect.TypeToken

class FirmShopDataViewModel :ViewModel() {
    val firm: MutableLiveData<FirmShopData> by lazy { MutableLiveData<FirmShopData>() }

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

    private val firmDatas :MutableLiveData<List<FirmShopData>> by lazy { MutableLiveData<List<FirmShopData>>() }
    private var firmShopDataList = mutableListOf<FirmShopData>()

    fun init(){//初始化，這邊假設load遠端資料
        loadFirmShopData()
    }

    private fun loadFirmShopData() {
        val currentFirm: Firm? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/firms", "OPTIONS")
        val FNO = currentFirm?.firmNo
        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/firm/personaldata/$FNO"
        val result = requestTask<FirmShopData>(url)
        result?.let {
            firm.value = it
        }

//        val type = object : TypeToken<List<FirmShopData>>() {}.type
//        val list = requestTask<List<FirmShopData>>(url, respBodyType = type)
//
//        for(item in list!!){
//            firmShopDataList.add(item)
//        }
//
//        this.firmShopDataList = firmShopDataList
//        this.firmDatas.value = this.firmShopDataList
    }

}