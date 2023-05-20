package com.example.thp101g2_android_school.shop.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.databinding.FragmentShopFavoriteItemviewBinding
import com.example.thp101g2_android_school.shop.model.Product
import com.example.thp101g2_android_school.shop.viewmodel.ShopFavoriteViewModel

class ShopFavoriteAdapter(private var products: List<Product>) :
    RecyclerView.Adapter<ShopFavoriteAdapter.ShopFavoriteViewHolder>() {


    fun updateProduct(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    class ShopFavoriteViewHolder(val itemViewBinding: FragmentShopFavoriteItemviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopFavoriteViewHolder{
        val itemViewBinding =  FragmentShopFavoriteItemviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = ShopFavoriteViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return ShopFavoriteViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ShopFavoriteViewHolder, position: Int) {
        val product = products[position]
        with(holder) {
            // 將欲顯示的product物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.product?.value = product
            val bundle = Bundle()
            bundle.putSerializable("product", product)

        }
    }
}