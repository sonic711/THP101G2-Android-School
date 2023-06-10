package com.example.thp101g2_android_school

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.community.model.Label
import com.example.thp101g2_android_school.community.model.Notification
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class ActivityViewModel : ViewModel() {
    // 社群用
    // 把該標籤放到這個集合中，並顯示
    val newLabels by lazy { LinkedHashSet<Label>() }
    val labelList: MutableLiveData<LinkedHashSet<Label>> by lazy { MutableLiveData<LinkedHashSet<Label>>() }

    // 應該是要向後端請求取得member物件
    val member = Member(null, null)
    val memberObj: MutableLiveData<com.example.thp101g2_android_school.member.model.Member> by lazy {
        MutableLiveData<com.example.thp101g2_android_school.member.model.Member>(
            com.example.thp101g2_android_school.member.model.Member()
        )
    }
    val db = FirebaseFirestore.getInstance()
    val notification: MutableLiveData<Notification> by lazy { MutableLiveData<Notification>() }

    init {
        getMember()
        getMemberToken(member)
    }

    /**
     *  社群用
     *  點擊標籤之後，把該標籤放到新的集合中，超過第三個會把第一個加入的擠掉
     *  重複的話會直接移除該標籤
     *  @param label 點擊到的標籤
     * */
    fun getLabelToInsert(label: Label) {
        if (newLabels.contains(label)) {
            newLabels.remove(label)
        } else {
            if (newLabels.size >= 3) {
                val firstEle = newLabels.first()
                newLabels.remove(firstEle)
            }
            newLabels.add(label)
        }
        labelList.value = newLabels
    }

    // 取得當前登入會員物件
    fun getMember() {
        val mem = requestTask<com.example.thp101g2_android_school.member.model.Member>("$url/members", "OPTIONS")
        memberObj.postValue(mem)
        member.memberId = mem?.memberNo.toString()
        println("目前登入會員Id${member.memberId}")
    }

    // 取得token並送到server
    fun getTokenSendServer() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result?.let { token ->
                    sendTokenToServer(token)
                    Log.d("ActivityViewModel", "token:$token")
                }
            }
        }
    }

    // 將token送到server
    fun sendTokenToServer(token: String) {
        member.token = token
        // 這邊要向後端送請求，取得目前登入會員物件
        viewModelScope.launch {
            // 送Token去資料庫，並新增or更新會員資料
            db.collection("members").document(member.memberId!!).set(member).await()
        }
    }

    fun getMemberToken(member: Member) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("memberId", member.memberId)
        db.collection("members").document(member.memberId!!).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result?.let { document ->
                    jsonObject.addProperty("token", document.data?.get("token").toString())
                }
            }
        }
    }


    fun getNotifi(notifiId: Int) {
        val notifi = requestTask<Notification>("$url/member/notification/$notifiId")
        notification.value = notifi
    }

    inner class Member(var memberId: String? = null, var token: String? = null) {
        override fun toString(): String {
            return "Member(memberId=$memberId, token=$token)"
        }
    }
}