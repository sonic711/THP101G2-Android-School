package com.example.thp101g2_android_school

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.firm.model.Firm

class FirmActivityViewModel : ViewModel() {
    val firm: MutableLiveData<Firm> by lazy { MutableLiveData<Firm>(Firm()) }
    init {
        viewModelScope.let {
            loadFirm()
        }
    }
    fun loadFirm() {
        var currentFirm: Firm? =

            requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/firms", "OPTIONS")
        firm.value = currentFirm
    }
}