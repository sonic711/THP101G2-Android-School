
package com.example.thp101g2_android_school.firm.controller

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.databinding.FragmentFirmProductManagerBinding
import com.example.thp101g2_android_school.databinding.FragmentFirmShopSettingBinding
import com.example.thp101g2_android_school.firm.model.Firm
import com.example.thp101g2_android_school.firm.viewmodel.FirmDatasCenterViewModel
import com.example.thp101g2_android_school.firm.viewmodel.FirmProductManagerViewModel
import com.example.thp101g2_android_school.firm.viewmodel.FirmShopDataViewModel
import com.example.thp101g2_android_school.firm.viewmodel.FirmShopSettingViewModel

class FirmShopSettingFragment : Fragment() {
    private lateinit var binding:FragmentFirmShopSettingBinding
    private val viewModel: FirmShopSettingViewModel by viewModels { requireParentFragment().defaultViewModelProviderFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel : FirmShopSettingViewModel by viewModels()
        binding = FragmentFirmShopSettingBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            viewModel?.init()
            ibShopSettingToBack.setOnClickListener{
                Navigation.findNavController(it).popBackStack()
            }

            ibIconUp.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    // 照片來源 --> 相簿MediaStore
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                pickPictureLauncher.launch(intent)
            }

            ibBackGround.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    // 照片來源 --> 相簿MediaStore
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                pickPictureLauncher1.launch(intent)
            }
            btFirmShopDataEdit.setOnClickListener {
                AlertDialog.Builder(view.context)
                    .setMessage("確定編輯?")
                    .setPositiveButton("是") { _, _ ->

                        requestTask<Unit>("$url/firmData", "PUT", viewModel?.firm?.value)
                        Navigation.findNavController(view).popBackStack()


                        Toast.makeText(context, "編輯成功", Toast.LENGTH_SHORT).show()

                    }
                    .setNegativeButton("否", null)
                    .setCancelable(false)
                    .show()
            }

        }
    }
    private var pickPictureLauncher = //
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // data -> intent  data -> uri資源定位的路徑
                // let -> 當uri不為空職則執行
                result.data?.data?.let {
                        uri -> binding.ibIconUp.setImageURI(uri)
                }
                // 直接拿到圖檔本身setImage
                // 直接拿到圖檔的路徑setImageURI(uri - >相簿裡面的圖檔)
            }
        }

    private var pickPictureLauncher1 = //
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // data -> intent  data -> uri資源定位的路徑
                // let -> 當uri不為空職則執行
                result.data?.data?.let { uri -> binding.ibBackGround.setImageURI(uri) }
                // 直接拿到圖檔本身setImage
                // 直接拿到圖檔的路徑setImageURI(uri - >相簿裡面的圖檔)
            }
        }
}