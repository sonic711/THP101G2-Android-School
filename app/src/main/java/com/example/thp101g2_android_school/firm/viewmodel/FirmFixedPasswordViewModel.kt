package com.example.thp101g2_android_school.firm.viewmodel

import android.app.AlertDialog
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.firm.model.Firm
import com.google.gson.JsonObject

// 先不用
class FirmFixedPasswordViewModel : ViewModel() {

    val firm: MutableLiveData<Firm> by lazy { MutableLiveData<Firm>(Firm()) }
    val newPassword: MutableLiveData<String> by lazy { MutableLiveData<String>("") }
    val confirmPassword: MutableLiveData<String> by lazy { MutableLiveData<String>("") }
    val result: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun edit(firmId: String) {
        firm.value?.run {
            if (

                password.isNotEmpty()
                &&
                (password.length < 8 || password.length > 13)
            ) {
                result.value = "密碼長度須為8~12"
                return
            }

            if (
                password.isNotEmpty()
                &&
                password != confirmPassword.value
            ){
                result.value = "新密碼與確認密碼不符合"
                return
            }
        }
        firm.value?.firmNo = firmId.toInt()
//        firm.value?.password = newPassword.value.toString()

        val respBody = requestTask<JsonObject>("$url/firmData", "PUT", firm.value)
        respBody?.run {
            result.value ="密碼編輯成功"
            println(result)
            println("result.value"+result.value)

        }

    }
}