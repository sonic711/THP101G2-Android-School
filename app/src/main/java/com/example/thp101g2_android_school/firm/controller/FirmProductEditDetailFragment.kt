package com.example.thp101g2_android_school.firm.controller

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.FirmActivityViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.databinding.FragmentFirmProductEditDetailBinding
import com.example.thp101g2_android_school.firm.model.Firm
import com.example.thp101g2_android_school.firm.model.FirmProduct
import com.example.thp101g2_android_school.firm.viewmodel.FirmProductManagerViewModel
import com.example.thp101g2_android_school.firm.viewmodel.FirmProductsEditViewModel
import com.google.gson.reflect.TypeToken

class FirmProductEditDetailFragment : Fragment() {
    private lateinit var binding: FragmentFirmProductEditDetailBinding
    private val FirmProductManagerViewModel: FirmProductManagerViewModel by viewModels { requireParentFragment().defaultViewModelProviderFactory }
    var shopProductId = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: FirmProductManagerViewModel by viewModels()
        binding = FragmentFirmProductEditDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // STEP07.接收參數並顯示
        // arguments
        println("arguments"+arguments)
        arguments?.let { bundle ->
            bundle.getSerializable("productsManager")?.let {
                binding.viewModel?.productEdit?.value = it as FirmProduct
                shopProductId = binding.viewModel?.productEdit?.value?.shopProductId.toString()
            }
        }
        with(binding) {
            viewModel?.loadProducts(shopProductId)
            // 若是採用onClick綁定事件，使用此方法可避免點擊衝突造成Toast無法回應
//            viewModel?.finished?.observe(viewLifecycleOwner) { finished ->
//                if (finished) {
//                    Toast.makeText(context, "編輯成功", Toast.LENGTH_SHORT).show()
//                }
//            }
            ibProductEditToBack.setOnClickListener {
                Navigation.findNavController(it).popBackStack()

            }
            btFirmCancelEdit.setOnClickListener { btCan ->
                Navigation.findNavController(btCan).popBackStack()
            }
            btFirmConEdit.setOnClickListener {
                AlertDialog.Builder(view.context)
                    .setMessage("確定編輯?")
                    .setPositiveButton("是") { _, _ ->

                        val finished: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
                        var currentFirm: Firm? = requestTask(
                            "http://10.0.2.2:8080/THP101G2-WebServer-School/firms",
                            "OPTIONS"
                        )
                        val FNO = currentFirm?.firmNo

                        requestTask<Unit>("$url/productmanage/$FNO", "PUT",viewModel?.productEdit?.value)
                        Navigation.findNavController(view).popBackStack()
                        finished.value = true
                        if (finished.value == true) {
                            Toast.makeText(context, "編輯成功", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .setNegativeButton("否", null)
                    .setCancelable(false)
                    .show()

            }

            // 監聽此圖片是否被點擊
            ivFirmProductEdit.setOnClickListener {
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
                // let -> 當uri不為空值則執行
                result.data?.data?.let { uri -> binding.ivFirmProductEdit.setImageURI(uri) }
                // 直接拿到圖檔本身setImage
                // 直接拿到圖檔的路徑setImageURI(uri - >相簿裡面的圖檔)
            }
        }

}