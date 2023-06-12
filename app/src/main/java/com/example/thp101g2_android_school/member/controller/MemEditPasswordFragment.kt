package com.example.thp101g2_android_school.member.controller

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.FragmentMemEditPasswordBinding
import com.example.thp101g2_android_school.member.viewModel.MemSettingViewModel
import com.google.gson.JsonObject
import org.mindrot.jbcrypt.BCrypt

class MemEditPasswordFragment : Fragment() {
    private lateinit var binding: FragmentMemEditPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: MemSettingViewModel by viewModels()
        binding = FragmentMemEditPasswordBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as MainActivity).supportActionBar?.show()
        activity?.setTitle("修改密碼")

        with(binding) {
            viewModel?.initialize()

            btSubmit.setOnClickListener {
                if (!inputValid()) {
                    return@setOnClickListener
                }

                AlertDialog.Builder(view.context)
                    .setMessage("確定更改?")
                    .setPositiveButton("是") {_,_ ->
                        savePreference()
                        viewModel?.member?.value?.password = hashPassword(viewModel?.nPassword?.value?.trim()!!)
                        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/members"
                        requestTask<JsonObject>(url, "PUT", viewModel!!.member.value!!)
                        Navigation.findNavController(view).popBackStack()
                    }
                    .setNegativeButton("否", null)
                    .setCancelable(false)
                    .show()
            }
        }
    }

    private fun inputValid(): Boolean {
        var valid = true
        with(binding) {
            val oPassword = viewModel?.oPassword?.value?.trim()
            val nPassword = viewModel?.nPassword?.value?.trim()
            val cPassword = viewModel?.cPassword?.value?.trim()

            // viewModel 中的member是從資料庫抓的，其 password 會是  hashcode
            if (!BCrypt.checkpw(oPassword, viewModel?.member?.value?.password?.trim())) {
                etOldPW.error = "密碼不正確"
                valid = false
            }

            if (nPassword?.length !in 6..12) {
                etNewPW.error = "密碼須為6-12字元"
                valid = false
            }
            if (cPassword != nPassword) {
                etConNewPW.error = "新密碼與確認密碼不相符"
                valid = false
            }
        }
        return valid
    }

    private fun savePreference() {
        requireActivity().getSharedPreferences("memberAccount", Context.MODE_PRIVATE).edit()
            .putString("password", binding.viewModel?.nPassword?.value?.trim())
            .apply()
        Toast.makeText(requireContext(), "data saved", Toast.LENGTH_SHORT).show()
    }

    private fun hashPassword(password: String): String {
        val saltRounds = 12 // 顏值得輪數
        val salt = BCrypt.gensalt(saltRounds) // 生成鹽值
        return BCrypt.hashpw(password, salt)
    }

}