package com.example.thp101g2_android_school.shop.controller

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentShopingCartItemviewBinding
import com.example.thp101g2_android_school.shop.model.Product
import com.example.thp101g2_android_school.shop.model.ShopingCart
import com.example.thp101g2_android_school.shop.viewmodel.ShopingCartViewModel

class ShopingCartAdapter(private var cartproducts: List<ShopingCart> ) :
    RecyclerView.Adapter<ShopingCartAdapter.ShopingCartViewHolder>() {


    fun updateProduct(products: List<ShopingCart>) {
        this.cartproducts = products
        notifyDataSetChanged()
    }

    class ShopingCartViewHolder(val itemViewBinding: FragmentShopingCartItemviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return cartproducts.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopingCartViewHolder{
        val itemViewBinding =  FragmentShopingCartItemviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = ShopingCartViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return ShopingCartViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ShopingCartViewHolder, position: Int) {
        val cartproduct = cartproducts[position]
        with(holder) {
            // 將欲顯示的product物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.cartproduct?.value = cartproduct
            itemViewBinding.etAddProduct.setText("0")
            val bundle = Bundle()


            itemViewBinding.btnShoppingAdd.setOnClickListener {
                val etAddProduct: EditText = itemViewBinding.root.findViewById(R.id.etAddProduct)
                val currentValue = etAddProduct.text.toString().toIntOrNull() ?: 0
                val newValue = if (currentValue < 99) currentValue + 1 else 99
                itemViewBinding.btBuy.setOnClickListener {
                    val etAddProduct: EditText = itemViewBinding.root.findViewById(R.id.etAddProduct)
                    val quantity = etAddProduct.text.toString().toIntOrNull() ?: 0
                    // 将数量传递到另一个界面
                    val context = itemView.context
                    val intent = Intent(context, ShopBuyFragment::class.java)
                    intent.putExtra("quantity", quantity)
                    context.startActivity(intent)
                }
                //若要即時更新 EditText 的內容並限制輸入數字的範圍，可以在 EditText 上設置 TextWatcher 監聽器。這樣當使用者輸入數字時，可以即時更新 EditText 的值並進行相關的邏輯處理。.jerry
                itemViewBinding.etAddProduct.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    }

                    override fun afterTextChanged(s: Editable?) {
                        val text = s.toString()
                        val newValue = text.toIntOrNull() ?: 0
                        val updatedValue = when {
                            newValue < 0 -> 0
                            newValue > 99 -> 99
                            else -> newValue
                        }
                        if (updatedValue != newValue) {
                            cartproduct.pquality = newValue
                            itemViewBinding.etAddProduct.setText(updatedValue.toString())
                        }
                    }
                })
                etAddProduct.setText(newValue.toString())
            }

            itemViewBinding.btnShoppingDelete.setOnClickListener {
                val etAddProduct: EditText = itemViewBinding.root.findViewById(R.id.etAddProduct)
                val currentValue = etAddProduct.text.toString().toIntOrNull() ?: 0
                val newValue = if (currentValue > 0) currentValue - 1 else 0
                etAddProduct.setText(newValue.toString())
            }
            bundle.putSerializable("cartproduct", cartproduct)
        }


    }
}