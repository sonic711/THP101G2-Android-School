package com.example.thp101g2_android_school

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MyFCMService : FirebaseMessagingService() {

    private val myTag = "TAG_${javaClass.simpleName}"
    override fun onMessageReceived(message: RemoteMessage) {

        var title = ""
        var body = ""
        // 取得notification資料，主要為title與body這2個保留字
        message.notification?.let { notification ->
            title = notification.title ?: ""
            body = notification.body ?: ""
        }
        // 取得自訂資料
        val data = message.data["data"]
//        Log.d(
//            myTag,
//            "onMessageReceived():\ntitle: $title, body: $body, data: $data"
//        )
        if (data != null) {
            sendLocalBroadcast(data)
        }
    }

    override fun onNewToken(token: String) {
        Log.d(myTag, "onNewToken: $token")
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
        Log.d(myTag, "onDeletedMessages")
    }

    private fun sendLocalBroadcast(message: String) {
        val intent = Intent("action_message")
        val bundle = Bundle()
        bundle.putString("message", message)
        intent.putExtras(bundle)
//        println("sendLocalBroadcast")
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }
}
