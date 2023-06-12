package com.example.thp101g2_android_school.member.controller

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.thp101g2_android_school.member.viewModel.ForgetPasswordViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.FragmentForgetPasswordBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import java.util.concurrent.TimeUnit

class ForgetPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgetPasswordBinding
    private val viewModel: ForgetPasswordViewModel by activityViewModels()
    private val myTag = "TAG_${javaClass.simpleName}"
    private lateinit var auth: FirebaseAuth
    private lateinit var verificationId: String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var countdownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgetPasswordBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            tvSmallLogin.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.loginMainFragment)
            }
            btGetCAPTCHA.setOnClickListener {
                if (emailValid()) {
                    val phoneNumber = viewModel?.getMember()?.phoneNumber.toString()
                    if (viewModel?.getMember() != null) {
                        viewModel?.phoneNumber?.value = phoneNumber
                        requestVerificationCode("+886$phoneNumber")
                        startCountdown()
                        btGetCAPTCHA.visibility = View.GONE
                    } else {
                        etEmail.error = "沒有此帳號"
                    }
                }
            }

            btSubmit.setOnClickListener {
                if (verificationCodeValid()) {
                    verifyIdAndCode(verificationId, viewModel?.verificationCode?.value!!)
                }
            }

            tvResent.setOnClickListener {
                if (emailValid()) {
                    val phoneNumber = requestTask<String>("http://10.0.2.2:8080/THP101G2-WebServer-School/member/forgetPassword/${viewModel?.email?.value}")
                    if (phoneNumber != null) {
                        viewModel?.phoneNumber?.value = phoneNumber
                        resendVerificationCode("+886$phoneNumber", resendToken)
                        startCountdown()
                        binding.tvResent.isEnabled = false
                        binding.tvResent.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_500))
                    } else {
                        etEmail.error = "沒有此帳號"
                    }
                }
            }

        }
    }

    private fun startCountdown() {
        countdownTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // 更新倒計時的數值
                binding.viewModel?.countdown?.value = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                // 倒計時結束，啟用重新發送按鈕
                binding.tvResent.isEnabled = true
                binding.tvResent.setTextColor(ContextCompat.getColor(context!!, R.color.purple_400))
            }
        }

        countdownTimer.start()
    }
    private fun requestVerificationCode(mobile: String) {
        // 設定簡訊語系為繁體中文
        auth.setLanguageCode("zh-Hant")
        val phoneAuthOptions = PhoneAuthOptions.newBuilder(auth)
            // 電話號碼，驗證碼寄送的電話號碼
            .setPhoneNumber(mobile)
            // 驗證碼失效時間，設為60秒代表即使多次請求驗證碼，過了60秒才會發送第2次
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            // 監控電話驗證的狀態
            .setCallbacks(verifyCallbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)
    }

    private val verifyCallbacks: OnVerificationStateChangedCallbacks =
        object : OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d(myTag, "onVerificationCompleted: $credential")
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.e(myTag, "onVerificationFailed: ${e.message}")
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                Log.d(myTag, "onCodeSent: $id")
                verificationId = id
                resendToken = token
                // 顯示填寫驗證碼版面
                binding.llSentVerification.visibility = View.VISIBLE
                binding.llVerification.visibility = View.VISIBLE
                binding.llValidTime.visibility = View.VISIBLE
                binding.btSubmit.visibility = View.VISIBLE

            }

        }

    private fun verifyIdAndCode(verificationId: String, verificationCode: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId, verificationCode)
        firebaseAuthWithPhoneNumber(credential)
    }

    private fun resendVerificationCode(
        phone: String,
        token: PhoneAuthProvider.ForceResendingToken
    ) {
        val phoneAuthOptions = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(verifyCallbacks)
            /* 驗證碼發送後，verifyCallbacks.onCodeSent()會傳來token，
               使用者要求重傳驗證碼必須提供token */
            .setForceResendingToken(token)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)
    }

    private fun firebaseAuthWithPhoneNumber(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task: Task<AuthResult?> ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.changePasswordFragment)
                } else {
                    with(binding) {
                        val message: String
                        val e = task.exception
                        if (e != null) {
                            message = e.message ?: ""
                            // 使用者輸入的驗證碼與簡訊傳來的不同會產生錯誤
                            if (e is FirebaseAuthInvalidCredentialsException) {
                                etVerification.error = "驗證碼錯誤"
                            }
                        } else {
                            message = "驗證失敗"
                        }
                        Log.d(myTag, "message: $message")
                    }
                }
            }
    }

    private fun emailValid(): Boolean {
        var valid = true
        with(binding) {
            val email = viewModel?.email?.value?.trim()
            if (email == null || email.isEmpty()) {
                etEmail.error = "請輸入信箱"
                valid = false
            }
        }
        return valid
    }

    private fun verificationCodeValid(): Boolean {
        var valid = true
        with(binding) {
            val verificationCode = viewModel?.verificationCode?.value?.trim()
            if (verificationCode == null || verificationCode.isEmpty()) {
                etVerification.error = "請輸入驗證碼"
                valid = false
            }
        }
        return valid
    }

    override fun onDestroy() {
        super.onDestroy()
        // 確保在Fragment銷毀時停止計時器，以避免內存洩漏
        countdownTimer.cancel()
    }

}