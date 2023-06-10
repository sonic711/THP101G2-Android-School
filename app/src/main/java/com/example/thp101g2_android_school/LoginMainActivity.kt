package com.example.thp101g2_android_school

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class LoginMainActivity : AppCompatActivity() {

    var data = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_main)
        val bundle = intent.extras ?: return
        for (key in bundle.keySet()) {
            val value = bundle.get(key)
            Log.d("myTAG_", "收到bundle:  Key: $key Value: $value")
        }
        data = bundle.getString("data").toString()
    }
}