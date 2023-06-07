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
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentFirmProductEditDetailBinding
import com.example.thp101g2_android_school.firm.model.FirmProduct
import com.example.thp101g2_android_school.firm.viewmodel.FirmProductManagerViewModel

class FirmProductEditDetailFragment : Fragment() {
    private lateinit var binding : FragmentFirmProductEditDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel : FirmProductManagerViewModel by viewModels()
        binding = FragmentFirmProductEditDetailBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner =this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // STEP07.接收參數並顯示
        // arguments
        arguments?.let { bundle ->
            bundle.getSerializable("productsManager")?.let {
                binding.viewModel?.productEdit?.value = it as FirmProduct
            }
        }
        with(binding){
            viewModel?.finished?.observe(viewLifecycleOwner){ finished->
                if (finished){
                    Toast.makeText(context, "編輯成功", Toast.LENGTH_SHORT).show()
                }
            }

            ibProductEditToBack.setOnClickListener{
                Navigation.findNavController(it).popBackStack()

            }
            btFirmCancelEdit.setOnClickListener { btCan->
                Navigation.findNavController(btCan).popBackStack()
            }
            btFirmConEdit.setOnClickListener {
//                Navigation.findNavController(requireView()).navigateUp()
                Navigation.findNavController(view).popBackStack()


//                val result = Toast.makeText(context, "編輯成功", Toast.LENGTH_SHORT).show()
//                Log.d("TAG_","onViewCreated:$result")
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
                // let -> 當uri不為空職則執行
                result.data?.data?.let { uri -> binding.ivFirmProductEdit.setImageURI(uri) }
                // 直接拿到圖檔本身setImage
                // 直接拿到圖檔的路徑setImageURI(uri - >相簿裡面的圖檔)
            }
        }

}