package com.example.thp101g2_android_school.manage.controller

import android.annotation.SuppressLint
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.manage.viewmodel.ManageMainViewModel
import com.example.thp101g2_android_school.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.manage.controller.ManageClassAdapter

class ManageMainFragment : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText




    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameEditText = findViewById(R.id.etUsername)
        passwordEditText = findViewById(R.id.etPassword)

        val loginButton = findViewById<Button>(R.id.btLogin)
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (isValidCredentials(username, password)) {
                // 帳號和密碼驗證成功，執行頁面跳轉
//                val intent = Intent(this@ManageMainFragment, ManageHomeFragment::class.java)
//                startActivity(intent)
            } else {
                // 帳號或密碼錯誤，顯示錯誤提示
                Toast.makeText(this@ManageMainFragment, "您輸入的帳號或密碼錯誤", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        // 在這裡執行帳號和密碼的驗證邏輯
        // 例如，與資料庫中的帳號和密碼進行比較
        val defaultUsername = "admin" // 假設預設的帳號
        val defaultPassword = "password" // 假設預設的密碼

        return username == defaultUsername && password == defaultPassword
    }
}