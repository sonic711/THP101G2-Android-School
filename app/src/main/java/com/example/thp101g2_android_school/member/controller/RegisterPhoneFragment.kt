package com.example.thp101g2_android_school.member.controller

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.FragmentRegisterPhoneBinding
import com.example.thp101g2_android_school.member.model.Member
import com.example.thp101g2_android_school.member.viewModel.RegisterViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.gson.JsonObject
import java.util.concurrent.TimeUnit

class RegisterPhoneFragment : Fragment() {

    private lateinit var binding: FragmentRegisterPhoneBinding
    private val viewModel: RegisterViewModel by activityViewModels()
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
        binding = FragmentRegisterPhoneBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btGetCaptcha.setOnClickListener {
                if (!inputValid()) {
                    return@setOnClickListener
                }
                viewModel?.phoneNumber?.value = viewModel?.member?.value?.phoneNumber?.trim()
                requestVerificationCode("+886${viewModel?.member?.value?.phoneNumber?.trim()}")
                startCountdown()

//                val newBundle = Bundle()
//                arguments?.let {bundle ->
//                    val member = bundle.getSerializable("member") as Member?
//                    member?.let { member ->
//                        member.phoneNumber = viewModel!!.member.value!!.phoneNumber.trim()
//                    }
//                    newBundle.putSerializable("member", member)
//                }
//                Navigation.findNavController(it).navigate(R.id.registerVerificationFragment, newBundle)
            }
            btSubmit.setOnClickListener {
                if (verificationCodeValid()) {
                    verifyIdAndCode(verificationId, viewModel?.verificationCode?.value!!)
                }
            }

            tvResent.setOnClickListener {
                resendVerificationCode("+886${viewModel?.member?.value?.phoneNumber}", resendToken)
                startCountdown()
                binding.tvResent.isEnabled = false
                binding.tvResent.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_500))
            }

            ivBack.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
            tvChangePhone.setOnClickListener {
                tvPhoneDescribe.visibility = View.VISIBLE
                llPhone.visibility = View.VISIBLE
                btGetCaptcha.visibility = View.VISIBLE
                llSentVerification.visibility = View.GONE
                llVerification.visibility = View.GONE
                llValidTime.visibility = View.GONE
                btSubmit.visibility = View.GONE
                etPhone.setText("")
            }

        }
    }

    private fun inputValid(): Boolean {
        var valid = true
        val phoneRegex = Regex("^09\\d{8}\$")
        with(binding) {
            val phone = viewModel?.member?.value?.phoneNumber?.trim()
            if (phone?.length != 10 || !phone.matches(phoneRegex)) {
                etPhone.error = "手機格式不正確"
                valid = false
            }
        }
        return valid
    }

    private fun startCountdown() {
        if (::countdownTimer.isInitialized) {
            cancelCountdown()
        }
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


    private fun cancelCountdown() {
        countdownTimer.cancel()
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

    private val verifyCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
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
                with(binding) {
                    tvPhoneDescribe.visibility = View.GONE
                    llPhone.visibility = View.GONE
                    btGetCaptcha.visibility = View.GONE
                    llSentVerification.visibility = View.VISIBLE
                    llVerification.visibility = View.VISIBLE
                    llValidTime.visibility = View.VISIBLE
                    btSubmit.visibility = View.VISIBLE
                }

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
            .setForceResendingToken(token)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)
    }

    private fun firebaseAuthWithPhoneNumber(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task: Task<AuthResult?> ->
                if (task.isSuccessful) {
                    val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/members"
                    val respBody =
                        requestTask<JsonObject>(url, "POST", binding.viewModel?.member?.value)
                    Log.d(myTag, respBody.toString())
                    if (respBody?.get("successful")?.asBoolean == true) {
                        Toast.makeText(requireContext(), "註冊成功", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack(R.id.loginFragment, false)
                    } else {
                        Toast.makeText(requireContext(), "註冊失敗", Toast.LENGTH_SHORT).show()
                    }
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