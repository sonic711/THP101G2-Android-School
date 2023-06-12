package com.example.thp101g2_android_school.firm.controller

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.databinding.FragmentFirmProductOnBinding
import com.example.thp101g2_android_school.firm.model.Firm
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
            ivFirmProductOn.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    // 照片來源 --> 相簿MediaStore
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                pickPictureLauncher.launch(intent)
            }
            ibProductOnToBack.setOnClickListener{
                Navigation.findNavController(it).popBackStack()
            }

            btFirmConOn.setOnClickListener {
                AlertDialog.Builder(view.context)
                    .setMessage("確定上架?")
                    .setPositiveButton("是") { _, _ ->
                        val finished: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
                        var currentFirm: Firm? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/firms", "OPTIONS")
                        val FNO = currentFirm?.firmNo
                        viewModel?.productOn?.value?.shopName = currentFirm?.shopName
                        viewModel?.productOn?.value?.firmNo = "$FNO" // 這裡有問題先寫死
                        requestTask<Unit>("$url/productmanage/$FNO", "POST", viewModel?.productOn?.value)
                        Navigation.findNavController(view).popBackStack()
                        finished.value = true
                        if (finished.value == true) {
                            Toast.makeText(context, "上架成功", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .setNegativeButton("否", null)
                    .setCancelable(false)
                    .show()
            }
            btFirmCancelOn.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
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