package com.example.thp101g2_android_school.member.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.member.model.Follower
import com.example.thp101g2_android_school.member.model.Member
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import org.mindrot.jbcrypt.BCrypt

class ForgetPasswordViewModel : ViewModel() {
    private val myTag = "TAG_${javaClass.simpleName}"
    val email: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val phoneNumber: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val verificationCode: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val countdown: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val member: MutableLiveData<Member> by lazy { MutableLiveData<Member>(Member()) }

    // 變更密碼
    val nPassword: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val cPassword: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun getMember(): Member? {
        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/member/forgetPassword/${email.value}"
        val type = object : TypeToken<Member>() {}.type
        val member = requestTask<Member>(url, "GET", respBodyType = type)
        if (member == null) {
            return null
        } else{
            this.member.value = member
            return member
        }
    }

    fun changePassword(): Boolean? {
        val hashedPassword = hashPassword(nPassword.value!!.trim())
        member.value?.password = hashedPassword
        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/member/forgetPassword"
        val respBody = requestTask<JsonObject>(url, "PUT", member.value!!)
        Log.d(myTag, "更改密碼 $respBody")
        return respBody?.get("successful")?.asBoolean
    }

    private fun hashPassword(password: String): String {
        val saltRounds = 12 // 顏值得輪數
        val salt = BCrypt.gensalt(saltRounds) // 生成鹽值
        return BCrypt.hashpw(password, salt)
    }

}