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
import com.example.thp101g2_android_school.shop.model.ShopFavorite
import com.example.thp101g2_android_school.shop.model.ShopingCart
import com.example.thp101g2_android_school.shop.viewmodel.ShopMainViewModel

class ProductAdapter(
    private var products: List<Product>,
    private var favproduct: List<ShopFavorite>,
    private var shoppingcart: List<ShopingCart>
) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    fun updateProduct(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    class ProductViewHolder(val itemViewBinding: ShopMainItemviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemViewBinding = ShopMainItemviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = ShopMainViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return ProductViewHolder(itemViewBinding)
    }


    fun setFavoriteProducts(favoriteProducts: List<ShopFavorite>) {
        this.favproduct = favoriteProducts
        notifyDataSetChanged()
    }

    fun setShoppingCartProducts(shoppingCartProducts: List<ShopingCart>) {
        this.shoppingcart = shoppingCartProducts
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        with(holder) {
            val bundle = Bundle()
            // 將欲顯示的product物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.product?.value = product
            var isFavorite = false
            var isShoppingCart = false
            for (favproduct in favproduct) {
                if (favproduct.shopProductId == product.shopProductId) {
                    isFavorite = true
                    break
                } else {
                    isFavorite = false
                }
            }

            for (shoppingcart in shoppingcart) {
                if (shoppingcart.shopProductId == product.shopProductId) {
                    isShoppingCart = true
                    break
                } else {
                    isShoppingCart = false
                }
            }

            bundle.putBoolean("isFavorite", isFavorite)
            bundle.putBoolean("isShoppingCart", isShoppingCart)
            bundle.putSerializable("product", product)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_shopFrontFragment_to_productDetailFragment, bundle)
            }

        }
    }

    override fun getItemCount(): Int {
        return products.size
    }
}