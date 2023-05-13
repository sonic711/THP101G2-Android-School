package com.example.thp101g2_android_school.shop.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.ShopMainItemviewBinding
import com.example.thp101g2_android_school.shop.model.Product
import com.example.thp101g2_android_school.shop.viewmodel.ShopMainViewModel

class ProductAdapter(private var products: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.FriendViewHolder>() {


    fun updateProduct(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    class FriendViewHolder(val itemViewBinding: ShopMainItemviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val itemViewBinding = ShopMainItemviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = ShopMainViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return FriendViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val product = products[position]
        with(holder) {
            // 將欲顯示的product物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.product?.value = product
            val bundle = Bundle()
            bundle.putSerializable("product", product)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_shopMainFragment_to_productDetailFragment2, bundle)
            }

        }
    }
}