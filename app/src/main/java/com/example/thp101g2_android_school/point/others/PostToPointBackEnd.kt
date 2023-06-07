package com.example.thp101g2_android_school.point.others
import android.content.Context
import com.example.thp101g2_android_school.app.request
import com.example.thp101g2_android_school.app.url

class PostToPointBackEnd(val context: Context) {

    fun sendRequestForSC(){
        val url = "$url/point"
        val requestBody = "{\"type\":\"insertForSC\"}"

        val response = request<String>(url, "POST", requestBody)

    }

    fun sendRequestForCMT() {
        val url = "$url/point"
        val requestBody = "{\"type\":\"insertForCMT\"}"

        val response = request<String>(url, "POST", requestBody)

    }


    fun sendRequestForMLR(MId:Int) {
        val contextQu: Context
        val url = "$url/point"
        val requestBody = "{\"type\":\"insertForMLR\",\"memberNo\":\"MId\"}"

        val response = request<String>(url, "POST", requestBody)
        // 处理响应
        response?.let {
            val SAD = SetAlertDialog(context)
            SAD.showAlertDialogForMLR(context)
        }
    }


    fun sendRequestForSO() {
        val url = "$url/point"
        val requestBody = "{\"type\":\"insertForSO\"}"

        val response = request<String>(url, "POST", requestBody)

    }









}