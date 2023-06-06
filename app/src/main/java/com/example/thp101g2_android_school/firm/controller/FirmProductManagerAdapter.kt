package com.example.thp101g2_android_school.firm.controller

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.content.ContextCompat
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.getStringResourceId
import com.example.thp101g2_android_school.databinding.FragmentFirmProductItemViewBinding
import com.example.thp101g2_android_school.firm.model.FirmProduct
import com.example.thp101g2_android_school.firm.viewmodel.FirmProductManagerViewModel

class FirmProductManagerAdapter(private var productsManager: List<FirmProduct>) :
    RecyclerView.Adapter<FirmProductManagerAdapter.FirmProductManagerViewHolder>() {

    // STEP08-2 更新資料時呼叫
    fun updateProductsManager(productsManager: List<FirmProduct>) {
        this.productsManager = productsManager
        notifyDataSetChanged()
    }

    class FirmProductManagerViewHolder(val itemViewBinding: FragmentFirmProductItemViewBinding) :
    //畫面交由父去呈現
        RecyclerView.ViewHolder(itemViewBinding.root)//根view->1筆itemView的畫面

    // STEP06.override這三個方法
    // 回傳資料筆數
    override fun getItemCount(): Int {
        return productsManager.size
    }

    // STEP06-1.重複創建CardView與其子Views
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FirmProductManagerViewHolder {

        val itemViewBinding = FragmentFirmProductItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = FirmProductManagerViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()

        return FirmProductManagerViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: FirmProductManagerViewHolder, position: Int) {
        val productsManager = productsManager[position]
        with(holder) {

            itemViewBinding.viewModel?.productEdit?.value = productsManager

            val bundle = Bundle()
            bundle.putSerializable("productsManager", productsManager)

//            if (productsManager.shopProductStatus == 1 || productsManager.shopProductStatus == 0) {
//                itemViewBinding.btProductEdit.visibility = View.INVISIBLE
//                itemViewBinding.btProductControlOffed.visibility = View.INVISIBLE
//                itemView.setOnClickListener {
//                    Navigation.findNavController(it)
//                        .navigate(R.id.firmProductEditDetailFragment, bundle)
//                }
//            } else {
//                itemViewBinding.btProductEdit.isEnabled = true
//                itemViewBinding.btProductControlOffed.isEnabled = true
//                itemViewBinding.btProductEdit.visibility = View.VISIBLE
//                itemViewBinding.btProductControlOffed.visibility = View.VISIBLE
//                itemViewBinding.btProductEdit.setOnClickListener {
//                    Navigation.findNavController(it)
//                        .navigate(R.id.firmProductEditDetailFragment, bundle)
//                }
//            }
//
            itemViewBinding.btProductEdit.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.firmProductEditDetailFragment, bundle)
            }


            // 這邊是假設如果商品狀態是1(下架)或0(被下架)時，將不會顯示編輯、下架按鈕
            if (productsManager.shopProductStatus == 1 || productsManager.shopProductStatus == 0) {
                itemViewBinding.btProductEdit.visibility = View.INVISIBLE
                itemViewBinding.btProductControlOffed.visibility = View.INVISIBLE

            } else {
                itemViewBinding.btProductEdit.isEnabled = true
                itemViewBinding.btProductControlOffed.isEnabled = true
                itemViewBinding.btProductEdit.visibility = View.VISIBLE
                itemViewBinding.btProductControlOffed.visibility = View.VISIBLE
            }
        }
    }
}
