package com.example.thp101g2_android_school.firm.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentOrderItemViewBinding
import com.example.thp101g2_android_school.firm.model.Order
import com.example.thp101g2_android_school.firm.viewmodel.FirmOrderViewModel

class FirmOrderAdapter(private var orders : List<Order>):
    RecyclerView.Adapter<FirmOrderAdapter.FirmOrderViewHolder>() {
    /**
     * 更新訂單列表內容
     * @param orders 新的訂單列表
     */

    fun updateOrders(orders: List<Order>) {
        this.orders = orders
        notifyDataSetChanged()
    }


    class FirmOrderViewHolder(val itemViewBinding: FragmentOrderItemViewBinding) :
    //畫面交由父去呈現
        RecyclerView.ViewHolder(itemViewBinding.root)//根view->1筆itemView的畫面

    // STEP06.override這三個方法
    // 回傳資料筆數
    override fun getItemCount(): Int {
        return orders.size
    }

    // STEP06-1.重複創建CardView與其子Views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirmOrderViewHolder {
        // STEP06-2.載入Layout檔案並做相關設定
        val itemViewBinding = FragmentOrderItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = FirmOrderViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        // STEP06-3.回傳給onBindViewHolder用
        return FirmOrderViewHolder(itemViewBinding)
    }

    // STEP06-4.把資料透過index binding到ViewModel上，就可以即時更新View
    override fun onBindViewHolder(holder: FirmOrderViewHolder, position: Int) {
        val order = orders[position]
        with(holder) {
            // 一旦把order指派給viewModel，上面的itemView都會秀出來
            itemViewBinding.viewModel?.firmOrder?.value = order
            // STEP06-5.把資料送到下一頁用
            val bundle = Bundle()
            bundle.putSerializable("order", order)
            // STEP06-6.按下CardView後把資料送到下一頁
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.firmOrderDetailFragment, bundle)
            }
        }
    }
}