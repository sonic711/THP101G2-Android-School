package com.example.thp101g2_android_school.firm.viewmodel

import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.community.model.Label
import com.example.thp101g2_android_school.firm.model.Firm
import com.example.thp101g2_android_school.firm.model.FirmProduct
import com.google.gson.reflect.TypeToken

class FirmProductsViewModel : ViewModel() {
    // 首頁賣場名稱與介紹
    // STEP02.建立兩個集合，LiveData的是給搜尋時更新用，一般的MutableList是給資料庫來的所有資料用
    // 原始課程列表
    // 專門存取要顯示的畫面，受監控的LiveData，一旦指派新值就會更新商品列表畫面

    private var firmProductList = mutableListOf<FirmProduct>()
//    private var firmDataList = mutableListOf<Firm>()

    // 受監控的LiveData，一旦指派新值就會更新商品列表畫面
    val firmProducts: MutableLiveData<List<FirmProduct>> by lazy { MutableLiveData<List<FirmProduct>>() }

//    val firmDatas :MutableLiveData<List<Firm>> by lazy { MutableLiveData<List<Firm>>() }

    // 載入資料
    //初始化，這邊假設load遠端資料
    init {
        loadFirmProduct()
//        loadFirmData()
    }

    private fun loadFirmProduct() {
        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/shophomepage/3" //先改成3，鎖死
        val type = object : TypeToken<List<FirmProduct>>() {}.type
        val list = requestTask<List<FirmProduct>>(url, respBodyType = type)

        for(item in list!!){
            firmProductList.add(item)
        }

        this.firmProductList = firmProductList
        this.firmProducts.value = this.firmProductList
    }
//    private  fun loadFirmData(){
//        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/firm/personaldata/3"
//        val type = object : TypeToken<List<Firm>>() {}.type
//        val list = requestTask<List<Firm>>(url, respBodyType = type)
//
//        for(item in list!!){
//            firmDataList.add(item)
//        }
//
//        this.firmDataList = firmDataList
//        this.firmDatas.value = this.firmDataList
//    }


}
