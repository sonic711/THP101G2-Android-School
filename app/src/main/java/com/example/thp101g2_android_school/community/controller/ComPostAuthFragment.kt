package com.example.thp101g2_android_school.community.controller

import android.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.community.viewmodel.ComPostAuthViewModel
import com.example.thp101g2_android_school.databinding.FragmentComPostauthBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import java.util.concurrent.TimeUnit


class ComPostAuthFragment : Fragment() {
    private val myTag = "TAG_${javaClass.simpleName}"
    private lateinit var binding: FragmentComPostauthBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var verificationId: String
    private lateinit var resendToken: ForceResendingToken
    private lateinit var countDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        Log.d(myTag, "金耀初始化$auth")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = getString(R.string.txtPhoneDescribe)
        val viewModel: ComPostAuthViewModel by viewModels()
        binding = FragmentComPostauthBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.textComAuthNeeded)
            .setPositiveButton(R.string.textComAuthGo) { _, _ ->

            }.setNegativeButton(R.string.txtCancel) { _, _ ->
                Navigation.findNavController(view).navigate(R.id.comMainFragment)
            }.show()
        with(binding) {
            btSend.setOnClickListener {
                if (mobileValid()) {
                    // 電話號碼格式要符合E.164，要加上country code，台灣為+886
                    requestVerificationCode("+886${viewModel?.mobile?.value}")
                }
            }
            // 簡訊傳送的手機與應用程式所在的手機可以不同
            btVerify.setOnClickListener {
                if (verificationCodeValid()) {
                    // 將應用程式收到的驗證ID與使用者輸入的簡訊驗證碼送至Firebase
                    verifyIdAndCode(verificationId, viewModel?.verificationCode?.value!!)
                }
            }
            btResend.setOnClickListener {
                if (mobileValid()) {
                    btResend.isEnabled = false
                    startCountDown()
                    resendVerificationCode("+886${viewModel?.mobile?.value}", resendToken)
                }
            }
        }
    }

    private fun requestVerificationCode(mobile: String) {
        with(binding) {
            viewModel?.layoutVisible?.value = true
        }
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

    private fun resendVerificationCode(
        phone: String,
        token: ForceResendingToken
    ) {
        println("phone: $phone")
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

    private fun verifyIdAndCode(verificationId: String, verificationCode: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId, verificationCode)
        firebaseAuthWithPhoneNumber(credential)
    }

    private fun firebaseAuthWithPhoneNumber(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task: Task<AuthResult?> ->
                if (task.isSuccessful) {
                    // TODO 去Ｐｏ文頁面
                    Toast.makeText(requireContext(), "驗證成功", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.comPostFragment)
                } else {
                    with(binding) {
                        val message: String
                        val e = task.exception
                        if (e != null) {
                            message = e.message ?: ""
                            // 使用者輸入的驗證碼與簡訊傳來的不同會產生錯誤
                            if (e is FirebaseAuthInvalidCredentialsException) {
                                etVerificationCode.error = getString(R.string.textComAuthError)
                            }
                        } else {
                            message = getString(R.string.textComAuthFailed)
                        }
                        viewModel?.text?.value = message
                    }
                }
            }
    }

    // 有點像註冊了callback函式
    private val verifyCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            /** This callback will be invoked in two situations:
             * 1 - Instant verification. In some cases the phone number can be instantly
             * verified without needing to send or enter a verification code.
             * 2 - Auto-retrieval. On some devices Google Play services can automatically
             * detect the incoming verification SMS and perform verification without
             * user action.  */
            // 如果手機有自動機制，就可以把驗證碼
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d(myTag, "onVerificationCompleted: $credential")
            }

            /**
             * 發送驗證碼填入的電話號碼格式錯誤，或是使用模擬器發送都會產生發送錯誤，
             * 使用模擬器發送會產生下列執行錯誤訊息：
             * App validation failed. Is app running on a physical device?
             */
            override fun onVerificationFailed(e: FirebaseException) {
                Log.e(myTag, "onVerificationFailed: ${e.message}")
            }

            /**
             * The SMS verification code has been sent to the provided phone number,
             * we now need to ask the user to enter the code and then construct a credential
             * by combining the code with a verification ID.
             */
            // Firebase送出驗證碼後自動呼叫
            override fun onCodeSent(id: String, token: ForceResendingToken) {
                Log.d(myTag, "onCodeSent: $id")
                // id是一組超長字串
                verificationId = id
                // token等等重送會用到
                resendToken = token
                // 顯示填寫驗證碼版面
                binding.viewModel?.layoutVisible?.value = true
            }
        }


    private fun mobileValid(): Boolean {
        var valid = true
        with(binding) {
            val mobile = viewModel?.mobile?.value?.trim()
            if (mobile == null || mobile.isEmpty()) {
                etMobile.error = getString(R.string.txtConPassword)
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
                etVerificationCode.error = getString(R.string.txtVerification)
                valid = false
            }
        }
        return valid
    }

    override fun onStart() {
        super.onStart()
        // 檢查user是否已經登入，是則FirebaseUser物件不為null
        auth.currentUser?.let {
            // TODO 直接去Po文頁面
            findNavController().navigate(R.id.comPostFragment)
        }
    }

    private fun startCountDown() {
        // 總時長60秒，間隔1秒
        val totalSeconds = 60
        val intervalSeconds = 1

        countDownTimer = object : CountDownTimer(
            (totalSeconds * 1000).toLong(), (intervalSeconds * 1000).toLong()
        ) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = (millisUntilFinished / 1000).toInt()
                binding.btResend.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                binding.btResend.isEnabled = true
                binding.btResend.text = getString(R.string.txtResent)
            }
        }
        countDownTimer.start()
    }
}