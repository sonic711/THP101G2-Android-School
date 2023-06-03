package com.example.thp101g2_android_school.shop.controller

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.databinding.FragmentShopingCartItemviewBinding
import com.example.thp101g2_android_school.shop.model.ShopingCart
import com.example.thp101g2_android_school.shop.viewmodel.ShopingCartViewModel
import com.google.gson.JsonObject

class ShopingCartAdapter(private var cartproducts: List<ShopingCart> ) :
    RecyclerView.Adapter<ShopingCartAdapter.ShopingCartViewHolder>() {


    fun updateProduct(products: List<ShopingCart>) {
        this.cartproducts = products
        notifyDataSetChanged()
    }

    class ShopingCartViewHolder(val itemViewBinding: FragmentShopingCartItemviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root){
        init {
            itemViewBinding.btBuy.visibility = View.GONE
        }
    }

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
            itemViewBinding.viewModel?.cartproduct?.value = cartproduct
            itemViewBinding.etAddProduct.setText("0")
            val bundle = Bundle()
            itemViewBinding.btnShoppingAdd.setOnClickListener {
                val etAddProduct: EditText = itemViewBinding.root.findViewById(R.id.etAddProduct)
                val currentValue = etAddProduct.text.toString().toIntOrNull() ?: 0
                val newValue = if (currentValue < 99) currentValue + 1 else 99
//                itemViewBinding.btBuy.setOnClickListener {
//                    val etAddProduct: EditText = itemViewBinding.root.findViewById(R.id.etAddProduct)
//                    val quantity = etAddProduct.text.toString().toIntOrNull() ?: 0
//                    // 将数量传递到另一个界面
//                    val context = itemView.context
//                    val intent = Intent(context, ShopBuyFragment::class.java)
//                    intent.putExtra("quantity", quantity)
//                }
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
                        val finalValue = if (updatedValue > cartproduct.shopProductCount) cartproduct.shopProductCount else updatedValue
                        if (finalValue != newValue) {
                            cartproduct.shopProductCount = finalValue
                            itemViewBinding.etAddProduct.setText(finalValue.toString())
                        }
                        itemViewBinding.btBuy.visibility = if (finalValue != 0) View.VISIBLE else View.GONE
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

            itemViewBinding.ivDelete.setOnClickListener {
                val shoppingcartproduct = cartproducts[position]
                AlertDialog.Builder(holder.itemView.context)
                    .setMessage("確定要刪除自我的購物車嗎?")
                    .setTitle("警告!!!!")
                    .setPositiveButton("確定") { dialog, which ->
                        println("SCA購物車刪除一筆")
                        val productId = shoppingcartproduct.shopProductId
                        val respbody = requestTask<JsonObject>(
                            "$url/shop/shoppingcart/$productId",
                            "DELETE"
                        )
                        cartproducts = cartproducts.toMutableList().apply {
                            removeAt(adapterPosition)
                        }
                        notifyItemRemoved(adapterPosition)
                        notifyDataSetChanged()
                    }
                    .setNegativeButton("取消", null)
                    .show()
            }


            itemViewBinding.btBuy.setOnClickListener {
                val etAddProduct: EditText = itemViewBinding.root.findViewById(R.id.etAddProduct)
                val quantity = etAddProduct.text.toString().toIntOrNull() ?: 0

                val bundle = Bundle()
                bundle.putInt("quantity", quantity)
                bundle.putSerializable("cartproduct", cartproduct)

                val navController = Navigation.findNavController(itemView)
                navController.navigate(R.id.action_shopFrontFragment_to_shopBuyFragment, bundle)
            }
        }



    }
}