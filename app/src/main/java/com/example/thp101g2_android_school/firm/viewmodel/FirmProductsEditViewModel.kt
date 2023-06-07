package com.example.thp101g2_android_school.firm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.firm.model.Firm
import com.example.thp101g2_android_school.firm.model.FirmProduct
import com.google.gson.reflect.TypeToken

class FirmProductsEditViewModel : ViewModel(){

    private var productsManagerList = mutableListOf<FirmProduct>()

    val productsManager: MutableLiveData<List<FirmProduct>> by lazy { MutableLiveData<List<FirmProduct>>() }
    // 載入資料
    //初始化，這邊假設load遠端資料
    init {
        loadProductsManger()
    }
    /** 模擬取得遠端資料 */
    fun loadProductsManger() {
        var currentFirm: Firm? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/firms", "OPTIONS")
        val FNO = currentFirm?.firmNo
        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/productmanage/$FNO"
        val type = object : TypeToken<List<FirmProduct>>() {}.type
        val list = requestTask<List<FirmProduct>>(url, respBodyType = type)
        productsManagerList = list!!.toMutableList()
//        for(item in list!!){
//            productsManagerList.add(item)
//        }
        this.productsManagerList = productsManagerList
        this.productsManager.value = this.productsManagerList
        // update
    }



    fun allProduct(){
        // productsManagerList的每一筆資料取出
        val productAll =mutableListOf<FirmProduct>()
        for(a in productsManagerList){

                productAll.add(a)
        }
        this.productsManager.value = productAll
        println(productAll)
        //
    }
    fun reload(){
        // productsManagerList的每一筆資料取出
        val productStatusOnList =mutableListOf<FirmProduct>()
        for(a in productsManagerList){
            if(a.shopProductStatus == 2){
                productStatusOnList.add(a)
            }

        }

        this.productsManager.value = productStatusOnList
        println(productStatusOnList)
        //
    }
    fun reloadOne(){
        // productsManagerList的每一筆資料取出
        val productStatusOffList =mutableListOf<FirmProduct>()
        for(a in productsManagerList){
            if(a.shopProductStatus == 1){
                productStatusOffList.add(a)
            }
        }
        this.productsManager.value = productStatusOffList
        println(productStatusOffList)
        //
    }
    fun reloadZero(){
        // productsManagerList的每一筆資料取出
        val productStatusOffedList =mutableListOf<FirmProduct>()
        for(a in productsManagerList){
            if(a.shopProductStatus == 0){
                productStatusOffedList.add(a)
            }
        }
        this.productsManager.value = productStatusOffedList
        println(productStatusOffedList)
        //
    }


}