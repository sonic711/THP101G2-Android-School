package com.example.thp101g2_android_school.firm.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentFirmDataCenterBinding
import com.example.thp101g2_android_school.databinding.FragmentFirmDataCenterItemViewBinding
import com.example.thp101g2_android_school.databinding.FragmentFirmProductItemViewBinding
import com.example.thp101g2_android_school.firm.model.Order
import com.example.thp101g2_android_school.firm.viewmodel.FirmDataCenterViewModel
import com.example.thp101g2_android_school.firm.viewmodel.FirmProductManagerViewModel

class FirmDataCenterAdapter(private var datas: List<Order>) :
    RecyclerView.Adapter<FirmDataCenterAdapter.FirmDataCenterViewHolder>() {
    /**
     * 更新數據中心列表內容
     * @param datas 新的列表
     */
    class FirmDataCenterViewHolder(val itemViewBinding: FragmentFirmDataCenterItemViewBinding) :
        //畫面交由父去呈現
        RecyclerView.ViewHolder(itemViewBinding.root)

    fun updateDatas(datas : List<Order>){
        this.datas = datas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirmDataCenterViewHolder {
        // STEP06-2.載入該dataCenter --> itemView的Layout檔案並做相關設定
        val itemViewBinding = FragmentFirmDataCenterItemViewBinding.inflate(
            // context 為該作業環境
            LayoutInflater.from(parent.context),parent,false
        )
        // 將itemViewBinding的viewModel綁訂至FirmDataCenterViewModel()
        itemViewBinding.viewModel=FirmDataCenterViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()

        // STEP06-3.回傳給onBindViewHolder用
        return FirmDataCenterViewHolder(itemViewBinding)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: FirmDataCenterViewHolder, position: Int) {
        val data = datas[position] // 讀取datas資料的位置
        with(holder){
            // 一旦把data指派給viewModel，上面的itemView都會秀出來
            itemViewBinding.viewModel?.firmSaleData?.value = data

        }
    }
}