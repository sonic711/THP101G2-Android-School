package com.example.thp101g2_android_school.shop.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.databinding.FragmentShopFavoriteItemviewBinding
import com.example.thp101g2_android_school.shop.model.ShopFavorite
import com.example.thp101g2_android_school.shop.viewmodel.ShopFavoriteViewModel
import com.google.gson.JsonObject

class ShopFavoriteAdapter(private var favoriteproducts: List<ShopFavorite>) :
    RecyclerView.Adapter<ShopFavoriteAdapter.ShopFavoriteViewHolder>() {

    fun updateProduct(favoriteproducts: List<ShopFavorite>) {
        this.favoriteproducts = favoriteproducts
        notifyDataSetChanged()
    }

    class ShopFavoriteViewHolder(val itemViewBinding: FragmentShopFavoriteItemviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return favoriteproducts.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopFavoriteViewHolder {
        val itemViewBinding = FragmentShopFavoriteItemviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = ShopFavoriteViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return ShopFavoriteViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ShopFavoriteViewHolder, position: Int) {
        val favoriteproduct = favoriteproducts[position]
        with(holder) {
            with(holder) {
                itemViewBinding.ivDelete.setOnClickListener {
                    AlertDialog.Builder(holder.itemView.context)
                        .setMessage("確定要刪除自我的最愛嗎?")
                        .setTitle("警告!!!!")
                        .setPositiveButton("確定") { dialog, which ->
                            println("SFA我的最愛刪除一筆")
                            val productId = favoriteproduct.shopProductId
                            val respbody = requestTask<JsonObject>(
                                "$url/shop/favorite/$productId",
                                "DELETE"
                            )
                            favoriteproducts = favoriteproducts.toMutableList().apply {
                                removeAt(adapterPosition)
                            }
                            notifyItemRemoved(adapterPosition)
                            notifyDataSetChanged()
                        }
                        .setNegativeButton("取消", null)
                        .show()
                }

                itemViewBinding.viewModel?.favoriteproduct?.value = favoriteproduct
                val bundle = Bundle()
                bundle.putSerializable("product", favoriteproduct)

            }
        }
    }
}