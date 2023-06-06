package com.example.thp101g2_android_school.firm.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.byteArrayToBitmap
import com.example.thp101g2_android_school.databinding.FragmentFirmItemViewBinding
import com.example.thp101g2_android_school.firm.model.Firm
import com.example.thp101g2_android_school.firm.model.FirmProduct
import com.example.thp101g2_android_school.firm.viewmodel.FirmMainViewModel

//,private var firmDatas:List<Firm>

class FirmProductAdapter(private var firmProducts: List<FirmProduct>) :
    RecyclerView.Adapter<FirmProductAdapter.FirmProductViewHolder>() {

    /**
     * 更新商品列表內容
     * @param firmProducts 新的課程列表
     */
    // STEP08-2 更新資料時呼叫
    fun updateFirmProduct(firmProducts: List<FirmProduct>) {
        this.firmProducts = firmProducts
        notifyDataSetChanged()
    }

    // STEP05.將商品列表的資料綁定到一個 item view 上，以便在 RecyclerView 中顯示這些資料。
    class FirmProductViewHolder(val itemViewBinding: FragmentFirmItemViewBinding) :
    //畫面交由父去呈現
        RecyclerView.ViewHolder(itemViewBinding.root)

    // STEP06.override這三個方法
    // 回傳資料筆數
    override fun getItemCount(): Int {
        return firmProducts.size
    }

    // STEP06-1.重複創建CardView與其子Views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirmProductViewHolder {
        // STEP06-2.載入Layout檔案並做相關設定
        val itemViewBinding = FragmentFirmItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = FirmMainViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        // STEP06-3.回傳給onBindViewHolder用
        return FirmProductViewHolder(itemViewBinding)
    }

    // STEP06-4.把資料透過index binding到ViewModel上，就可以即時更新View
    // FirmProductViewHolder 26行
    override fun onBindViewHolder(holder: FirmProductViewHolder, position: Int) {
        val firmProduct = firmProducts[position]
        with(holder) {
            // 一旦把firmProduct指派給viewModel，上面的itemView都會秀出來
            itemViewBinding.viewModel?.firmProduct?.value = firmProduct
            // TODO 重要 圖片檔在這!!
            if (firmProduct.shopProductImg != null) {
                val img = byteArrayToBitmap(firmProduct.shopProductImg!!)
                itemViewBinding.ivFirmProduct.setImageBitmap(img)
            }

            // STEP06-5.把資料送到下一頁用
            val bundle = Bundle()
            bundle.putSerializable("firmProduct", firmProduct)
            // STEP06-6.按下CardView後把資料送到下一頁FirmProductDetailFragment
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.firmProductDetailFragment, bundle)
            }
        }
    }
}