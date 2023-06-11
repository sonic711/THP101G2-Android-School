package com.example.thp101g2_android_school.member.controller

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.FragmentLoginBinding
import com.example.thp101g2_android_school.member.model.Member
import com.example.thp101g2_android_school.member.viewModel.LoginViewModel
import com.example.thp101g2_android_school.member.viewModel.MemberViewModel
import com.example.thp101g2_android_school.point.others.SetAlertDialog
import com.google.gson.reflect.TypeToken
import okio.Utf8
import org.mindrot.jbcrypt.BCrypt
import java.net.URLEncoder

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var contextMLR: Context
    private val myTag = "TAG_${javaClass.simpleName}"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: LoginViewModel by viewModels()
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        contextMLR = requireContext()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            tvForgetPassword.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.forgetPasswordFragment)
            }
            btLogin.setOnClickListener {
                if (!inputValid()) {
                    return@setOnClickListener
                }
                var member = Member()
                member.memberEmail = viewModel!!.member.value!!.memberEmail.trim()
                val password = viewModel!!.member.value!!.password.trim()
                val dbPassword = requestTask<String>("http://10.0.2.2:8080/THP101G2-WebServer-School/member/getDetail/${member.memberEmail}")
                if (dbPassword != null) {
                    member.password = dbPassword
                    if (BCrypt.checkpw(password, dbPassword)) {
                        // 將特殊字符轉換為URL安全形式
                        val encodedPassword = URLEncoder.encode(dbPassword, "UTF-8")
                        Log.d(myTag, encodedPassword)
                        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/member/getDetail"
                        val respBody =
                            requestTask<Member>(url, "POST", member)
                        if (respBody?.memberNo != null) {
                            val postUrl = "http://10.0.2.2:8080/THP101G2-WebServer-School/point"
                            val requestBody = mapOf(
                                "type" to "insertForMLR",
                                "memberNo" to respBody.memberNo
                            )
                            val responseType = object : TypeToken<SetAlertDialog.ApiResponse>() {}.type
                            val response = requestTask<SetAlertDialog.ApiResponse>(postUrl, "POST",
                                requestBody, responseType)
                            if (response != null && response.successful ) {
                                val daiBiao = SetAlertDialog(contextMLR)
                                daiBiao.showAlertDialogForMLR(contextMLR){
                                    val intent = Intent(requireContext(), MainActivity::class.java)
                                    intent.putExtra("type", "user")
                                    startActivity(intent)
                                }
                            } else {
                                val intent = Intent(requireContext(), MainActivity::class.java)
                                intent.putExtra("type", "user")
                                startActivity(intent)
                            }
                        } else {
                            etEmail.error = "信箱或密碼錯誤"
                            etPassword.error = "信箱或密碼錯誤"
                        }
                    } else {
                        etEmail.error = "信箱或密碼錯誤"
                        etPassword.error = "信箱或密碼錯誤"
                    }
                } else {
                    etEmail.error = "信箱或密碼錯誤"
                    etPassword.error = "信箱或密碼錯誤"
                }

            }
        }


    }

    private fun inputValid(): Boolean {
        var valid = true
        with(binding) {
            val email = viewModel?.member?.value?.memberEmail?.trim()
            val password = viewModel?.member?.value?.password?.trim()
            if (email == null || email.isEmpty()) {
                etEmail.error = "不可為空"
                valid = false
            }
            if (password == null || password.isEmpty()) {
                etPassword.error = "不可為空"
                valid = false
            }
        }
        return valid
    }

}