package com.example.thp101g2_android_school.firm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.firm.model.Firm

class FirmShopSettingViewModel :ViewModel() {
    val firm : MutableLiveData<Firm> by lazy { MutableLiveData<Firm>() }

    // 待改
//    private val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/ordermanager/1"
//
//    fun init(){
//        firm.value = requestTask(url,"OPTIONS")
//    }


//    fun logout(view : View){ // 使用@{_ ->viewModel.logout()}會錯誤是因為沒有參數
//        AlertDialog.Builder(view.context)
//            .setMessage("確定登出")
//            .setPositiveButton("是"){_,_ ->
//                requestTask<JsonObject>(url,"DELETE")
//
//                // TODO 導覽至Login Fragment
//                Navigation.findNavController(view).navigate(R.id.memberLoginFragment)
//            }
//            .setNegativeButton("否",null)
//            .setCancelable(false)
//            .show()
//    }
}