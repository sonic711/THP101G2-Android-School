package com.example.thp101g2_android_school.community.controller

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKey
import com.example.thp101g2_android_school.ActivityViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.community.model.ChildItem
import com.example.thp101g2_android_school.community.viewmodel.ComPostViewModel
import com.example.thp101g2_android_school.databinding.FragmentComPostBinding
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.io.File

/*
*  日期要用比較新版
* */
@RequiresApi(Build.VERSION_CODES.O)
class ComPostFragment : Fragment() {
    private val myTag = "TAG_${javaClass.simpleName}"
    private lateinit var binding: FragmentComPostBinding
    private lateinit var file: File
    val viewModel: ComPostViewModel by viewModels()
    private val activityViewModel: ActivityViewModel by activityViewModels()
    private var submitStatus = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        file = File(requireContext().filesDir, "PostInternal")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // todo
//        val actionBar = (requireActivity() as MainActivity).supportActionBar
//        actionBar?.title = "發表貼文"
//        actionBar?.show()

        binding = FragmentComPostBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activityViewModel.memberObj.value?.let { viewModel.loadData(it) }
        with(binding) {
            val navController = Navigation.findNavController(requireView())
            val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
            savedStateHandle?.getLiveData<Bundle>("bundle")?.observe(viewLifecycleOwner) { bundle ->
                val data = bundle?.getSerializable("secClass") // 從Bundle中獲得傳回來的次分類
                val secClass = data as ChildItem
                viewModel?.secClassId?.value = secClass.comSecClassId
                viewModel?.secClassName?.value = secClass.comSecClassName
            }
            // 按下選擇看板後，跳去下個Fragment選擇次分類
            classCardView.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_comPostFragment_to_comAllClassForPostFragment)
            }
            nextStepBtn.setOnClickListener {
                // 去搜尋標籤頁面
                Navigation.findNavController(it).navigate(R.id.action_comPostFragment_to_comLabelForPostFragment)
            }
            btSubmit.setOnClickListener {
                inputValid()
                AlertDialog.Builder(requireContext())
                    .setMessage("確定發表文章？")
                    .setPositiveButton("是") { _, _ ->
                        val result = viewModel?.addPost()
                        when (result) {
                            0 -> Toast.makeText(requireContext(), "請選擇分類", Toast.LENGTH_SHORT).show()
                            1 -> Toast.makeText(requireContext(), "文章新增失敗", Toast.LENGTH_SHORT).show()
                            2 -> {
                                deleteInternalFile()
                                submitStatus = true
                                Navigation.findNavController(it).navigate(R.id.comMainFragment)
                            }
                        }
                    }.setNegativeButton("否") { _, _ ->

                    }.show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        loadInternal()
        // 載入選擇的分類 並顯示在標籤上
        // 沒有值的標籤要被隱藏起來
        if (activityViewModel.newLabels.isEmpty()) return
        viewModel.labels.value = activityViewModel.newLabels.toList()
        with(binding) {
            when (viewModel?.labels?.value?.size) {
                0 -> {
                    tvLabelName1.visibility = View.GONE
                    tvLabelName2.visibility = View.GONE
                    tvLabelName3.visibility = View.GONE
                }

                1 -> {
                    tvLabelName1.text = viewModel?.labels?.value?.get(0)?.comLabelName
                    tvLabelName1.visibility = View.VISIBLE
                    tvLabelName2.visibility = View.GONE
                    tvLabelName3.visibility = View.GONE
                }

                2 -> {
                    tvLabelName1.text = viewModel?.labels?.value?.get(0)?.comLabelName
                    tvLabelName2.text = viewModel?.labels?.value?.get(1)?.comLabelName
                    tvLabelName1.visibility = View.VISIBLE
                    tvLabelName2.visibility = View.VISIBLE
                    tvLabelName3.visibility = View.GONE
                }

                3 -> {
                    tvLabelName1.text = viewModel?.labels?.value?.get(0)?.comLabelName
                    tvLabelName2.text = viewModel?.labels?.value?.get(1)?.comLabelName
                    tvLabelName3.text = viewModel?.labels?.value?.get(2)?.comLabelName
                    tvLabelName1.visibility = View.VISIBLE
                    tvLabelName2.visibility = View.VISIBLE
                    tvLabelName3.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityViewModel.newLabels.clear()
        if (!submitStatus) {
            saveInternal()
        }
    }

    private fun saveInternal() {
        with(binding) {
            if (!inputValid()) {
                return
            }
            // 如果已經有該檔案，就刪除後新增
            if (file.exists()) file.delete()

            // 將資料轉成JSON
            val jsonObject = JsonObject()
            // 這邊應該還要存會員的編號，避免換帳號之後代錯草稿
            jsonObject.addProperty("memberId", activityViewModel.memberObj.value?.memberNo)
            jsonObject.addProperty("title", viewModel?.title?.value)
            jsonObject.addProperty("content", viewModel?.content?.value)
            getEncryptedFile(file).openFileOutput()
                .bufferedWriter().use {
                    it.write(jsonObject.toString())
                }
            Toast.makeText(requireContext(), "已儲存至草稿", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadInternal() {
        with(binding) {
            // filesDir可以取得內部儲存目錄
            // 欲開啟的檔案存在才讀取內容
            if (file.exists()) {
                getEncryptedFile(file).openFileInput().bufferedReader().useLines { lines ->
                    // 這邊存入會員的編號，避免換帳號之後代錯草稿
                    val jsonStr = lines.fold("") { text, line -> "$text$line" }
                    if (jsonStr.isNullOrBlank()) return
                    val jsonObject = Gson().fromJson(jsonStr, JsonObject::class.java)
                    // 判斷目前登入的會員id跟存入的會員id 有無一至，有的話才取出內容
                    // 如果目前登入的會員編號 屬於 草稿屬於的會員編號的話
                    if (jsonObject.get("memberId")?.asString == activityViewModel.memberObj.value?.memberNo?.toString()) {
                        viewModel?.title?.value = jsonObject.get("title").asString
                        viewModel?.content?.value = jsonObject.get("content").asString
                    } else return
                }

//                Toast.makeText(requireContext(), "從草稿載入內容", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun deleteInternalFile() {
        val file = File(requireContext().filesDir, "PostInternal")
        if (file.exists()) {
            val deleted = file.delete()
            if (deleted) {
                // 删除成功
            } else {
                // 删除失败
            }
        } else {
            // 文件不存在
        }
    }

    private fun getEncryptedFile(file: File): EncryptedFile {
        val masterKeyAlias = MasterKey.Builder(requireContext())
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        return EncryptedFile.Builder(
            requireContext(),
            file, /* 加密檔案可以為內部/外部檔案，完全依照檔案路徑來決定 */
            masterKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()
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