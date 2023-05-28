package com.example.thp101g2_android_school.shop.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentShopOrderItemBinding

import com.example.thp101g2_android_school.shop.model.ShopOrderList
import com.example.thp101g2_android_school.shop.viewmodel.ShopOrderListViewModel

class ShopOrderListAdapter(private var orders: List<ShopOrderList>) :
    RecyclerView.Adapter<ShopOrderListAdapter.ShopOrderListViewHolder>() {


    fun updateProduct(orders: List<ShopOrderList>) {
        this.orders = orders
        notifyDataSetChanged()
    }

    class ShopOrderListViewHolder(val itemViewBinding: FragmentShopOrderItemBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopOrderListViewHolder{
        val itemViewBinding =  FragmentShopOrderItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = ShopOrderListViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return ShopOrderListViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ShopOrderListViewHolder, position: Int) {
        val order = orders[position]
        with(holder) {
            // 將欲顯示的product物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.order?.value = order
            val bundle = Bundle()
            bundle.putSerializable("order", order)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_shopFrontFragment_to_shopOrderDetailFragment, bundle)
            }

        }
    }
}