package com.example.thp101g2_android_school

import android.content.Intent
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFCMService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {

    }

    override fun onNewToken(token: String) {

    }
    private fun sendLocalBroadcast(message: String) {
        val intent = Intent("action_message")
        val bundle = Bundle()
        bundle.putString("message", message)
        intent.putExtras(bundle)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }
}