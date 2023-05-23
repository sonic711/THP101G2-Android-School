package com.example.thp101g2_android_school.firm.controller

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.example.thp101g2_android_school.databinding.FragmentFirmProductOnBinding
import com.example.thp101g2_android_school.firm.viewmodel.FirmProductOnViewModel

class FirmProductOnFragment : Fragment() {
    private lateinit var bindnig : FragmentFirmProductOnBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel : FirmProductOnViewModel by viewModels()
        bindnig = FragmentFirmProductOnBinding.inflate(inflater,container,false)
        bindnig.viewModel = viewModel
        bindnig.lifecycleOwner =this
        return bindnig.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(bindnig){
            btFirmProductOnPicture.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    // 照片來源 --> 相簿MediaStore
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                pickPictureLauncher.launch(intent)
            }
        }
    }

    private var pickPictureLauncher = //
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // data -> intent  data -> uri資源定位的路徑
                // let -> 當uri不為空職則執行
                result.data?.data?.let { uri -> bindnig.ivFirmProductOn.setImageURI(uri) }
                // 直接拿到圖檔本身setImage
                // 直接拿到圖檔的路徑setImageURI(uri - >相簿裡面的圖檔)
            }
        }
}