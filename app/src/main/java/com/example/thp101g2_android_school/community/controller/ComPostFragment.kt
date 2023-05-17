package com.example.thp101g2_android_school.community.controller

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.community.viewmodel.ComPostViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentComPostBinding
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.io.File

class ComPostFragment : Fragment() {
    private val myTag = "TAG_${javaClass.simpleName}"
    private lateinit var binding: FragmentComPostBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).supportActionBar?.title = "發表貼文"
        binding = FragmentComPostBinding.inflate(inflater, container, false)
        val viewModel: ComPostViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {

        }
    }

    override fun onStart() {
        super.onStart()
        // TODO 這邊要判斷如果最後送出文章了，就刪除草稿
        loadInternal()
    }

    override fun onStop() {
        super.onStop()
        // TODO 應該是要寫一個彈出視窗 問要不要儲存草稿嗎？，但如果閃退怎麼辦
        saveInternal()

    }

    override fun onDestroy() {
        super.onDestroy()
        println("發表文章頁面銷毀")
    }

    private fun saveInternal() {
        with(binding) {
            if (!inputValid()) {
                return
            }
            // 將資料轉成JSON
            val jsonObject = JsonObject()
            // TODO 這邊應該還要存會員的編號，避免換帳號之後代錯草稿，先寫死會員編號1
            jsonObject.addProperty("memberId", "1")
            jsonObject.addProperty("title", viewModel?.title?.value)
            jsonObject.addProperty("content", viewModel?.content?.value)
            requireContext().openFileOutput("PostInternal", Context.MODE_PRIVATE)
                .bufferedWriter().use {
                    it.write(jsonObject.toString())
                }
            Toast.makeText(requireContext(), "已儲存至草稿", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadInternal() {
        with(binding) {
            // filesDir可以取得內部儲存目錄
            val file = File(requireContext().filesDir, "PostInternal")
            // 欲開啟的檔案存在才讀取內容
            if (file.exists()) {
                requireContext().openFileInput("PostInternal").bufferedReader().useLines { lines ->
                    // TODO 這邊應該還要存入會員的編號，避免換帳號之後代錯草稿，先寫死會員編號1
                    val jsonStr = lines.fold("") { text, line -> "$text$line" }
                    val jsonObject = Gson().fromJson(jsonStr, JsonObject::class.java)
                    // TODO 判斷目前登入的會員id跟存入的會員id 有無一至，有的話才取出內容
                    // 如果目前登入的會員編號 屬於 草稿屬於的會員編號的話
                    if(jsonObject.get("memberId")?.asString == "1"){
                        viewModel?.title?.value = jsonObject.get("title").asString
                        viewModel?.content?.value = jsonObject.get("content").asString
                    }
                    else return
                }
                Toast.makeText(requireContext(), "從草稿載入內容", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun inputValid(): Boolean {
        var valid = true
        with(binding) {
            val uid = viewModel?.title?.value?.trim()
            val password = viewModel?.content?.value?.trim()
            if (uid.isNullOrEmpty()) {
                // TODO 先寫死文字
                etTitle.error = "標題未輸入"
                valid = false
            }
            if (password.isNullOrEmpty()) {
                // TODO 先寫死文字
                etContent.error = "文章內容未輸入"
                valid = false
            }
        }
        return valid
    }

}