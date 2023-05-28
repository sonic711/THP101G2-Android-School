package com.example.thp101g2_android_school.firm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.community.model.Label
import com.example.thp101g2_android_school.firm.model.FirmProduct
import com.google.gson.reflect.TypeToken

class FirmProductsViewModel : ViewModel() {
    // 首頁賣場名稱與介紹
    val firmNameText : MutableLiveData<Int> by lazy { MutableLiveData<Int>(R.string.txtFirmName) }
    val infoText : MutableLiveData<Int> by lazy { MutableLiveData<Int>(R.string.txtFirmInfo) }
    val backGroundId : MutableLiveData<Int> by lazy { MutableLiveData<Int>(R.drawable.background01) }
    val iconId : MutableLiveData<Int> by lazy { MutableLiveData<Int>(R.drawable.p04) }

    // STEP02.建立兩個集合，LiveData的是給搜尋時更新用，一般的MutableList是給資料庫來的所有資料用
    // 原始課程列表
    // 專門存取要顯示的畫面，受監控的LiveData，一旦指派新值就會更新商品列表畫面

    private var firmProductList = mutableListOf<FirmProduct>()

    // 受監控的LiveData，一旦指派新值就會更新商品列表畫面
    val firmProducts: MutableLiveData<List<FirmProduct>> by lazy { MutableLiveData<List<FirmProduct>>() }

    // 載入資料
    //初始化，這邊假設load遠端資料
    init {
        loadFirmProduct()
    }

    private fun loadFirmProduct() {
        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/shophomepage"
        val type = object : TypeToken<List<FirmProduct>>() {}.type
        val list = requestTask<List<FirmProduct>>(url, respBodyType = type)

        for(item in list!!){
            firmProductList.add(item)
        }

//        var firmProductList = mutableListOf<FirmProduct>()
//        firmProductList.add(FirmProduct(R.drawable.java, "java", "Willian", price = 300.0.toString(),"java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書",1,"緯育","2021.1.3","書籍","程式語言"))
//        firmProductList.add(FirmProduct(R.drawable.kotlin, "kotlin", "黃彬華老師.註", price = 500.0.toString(),"Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書",2,"緯育","2021.1.3","書籍","程式語言"))
//        firmProductList.add(FirmProduct(R.drawable.python, "python", "Jerry", price = 450.0.toString(),"python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書",1,"緯育","2021.1.3","書籍","程式語言"))
//        firmProductList.add(FirmProduct(R.drawable.senpai, "senpai", "AHHHH", price = 77777777777.toString(),"野獸先輩是日本㚻片公司COAT CORPORATION（官方網站）2001年發售的影片《Babylon 34 真夏の夜の淫夢 the IMP》中出演「田所」一役的一名㚻片演員。他出場的影片第四章副標題為「昏睡レイプ!野獣と化した先輩」，因此得名。其本人雖然成為了日本的網絡紅人，但是真實姓名，身份，和出演之後的行蹤均不詳。\n" +
//                "\n" +
//                "本文引自萌娘百科(https://zh.moegirl.org.cn )",0,"先輩出版社","2008.4.1","書籍","教育書籍"))

        this.firmProductList = firmProductList
        this.firmProducts.value = this.firmProductList
    }
}