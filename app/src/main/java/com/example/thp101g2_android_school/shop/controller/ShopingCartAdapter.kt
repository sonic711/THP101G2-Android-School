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
import com.example.thp101g2_android_school.databinding.FragmentShopBuyBinding
import com.example.thp101g2_android_school.databinding.FragmentShopingCartBinding
import com.example.thp101g2_android_school.databinding.FragmentShopingCartItemviewBinding
import com.example.thp101g2_android_school.member.model.Member
import com.example.thp101g2_android_school.shop.model.ShopingCart
import com.example.thp101g2_android_school.shop.viewmodel.ShopingCartViewModel
import com.google.gson.JsonObject

class ShopingCartAdapter(private var cartproducts: List<ShopingCart>,private val binding: FragmentShopingCartBinding) :
    RecyclerView.Adapter<ShopingCartAdapter.ShopingCartViewHolder>() {

    private var totalPriceResult = 0
    private val quantityMap = mutableMapOf<Int, Int>()

    fun updateProduct(products: List<ShopingCart>) {
        this.cartproducts = products
        notifyDataSetChanged()
    }

    class ShopingCartViewHolder(val itemViewBinding: FragmentShopingCartItemviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        init {
            itemViewBinding.btBuy.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return cartproducts.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopingCartViewHolder {
        val itemViewBinding = FragmentShopingCartItemviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = ShopingCartViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return ShopingCartViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ShopingCartViewHolder, position: Int) {
        var currentMember: Member? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/members", "OPTIONS")
        println(currentMember)
        val memberno = currentMember?.memberNo
        println(memberno)
        val cartproduct = cartproducts[position]
            with(holder) {
                itemViewBinding.viewModel?.cartproduct?.value = cartproduct
                itemViewBinding.etAddProduct.setText("0")
                val bundle = Bundle()
                itemViewBinding.btnShoppingAdd.setOnClickListener {
                    val etAddProduct: EditText = itemViewBinding.root.findViewById(R.id.etAddProduct)
                    val currentValue = etAddProduct.text.toString().toIntOrNull() ?: 0
                    val newValue = when {
                        currentValue < 99 -> currentValue + 1
                        else -> 99
                    }
                    quantityMap[position] = newValue

                    // 更新 EditText 的值
                    etAddProduct.setText(newValue.toString())

                    // 执行相关逻辑
                    val updatedValue = when {
                        newValue < 0 -> 0
                        newValue > cartproduct.shopProductCount -> cartproduct.shopProductCount
                        else -> newValue
                    }
//                    cartproduct.shopProductCount = updatedValue
//                    itemViewBinding.btBuy.visibility = if (updatedValue != 0) View.VISIBLE else View.GONE
//                    val priceResult = updatedValue * cartproduct.shopProductPrice.toInt()
                    totalPriceResult += cartproduct.shopProductPrice.toInt()
                    binding.tvPriceResult?.text = totalPriceResult.toString()
                    println("我是price優 : $totalPriceResult")
                }




                itemViewBinding.btnShoppingDelete.setOnClickListener {
                    val etAddProduct: EditText =
                        itemViewBinding.root.findViewById(R.id.etAddProduct)
                    val currentValue = etAddProduct.text.toString().toIntOrNull() ?: 0
                    val newValue = if (currentValue > 0) currentValue - 1 else 0
                    etAddProduct.setText(newValue.toString())
                    val updatedValue = when {
                        newValue > cartproduct.shopProductCount -> cartproduct.shopProductCount
                        else -> newValue
                    }
                    if (currentValue == 0) {
                        quantityMap.remove(position)
                        return@setOnClickListener
                    }
                    quantityMap[position] = newValue
//                    val deletedPriceResult = updatedValue * cartproduct.shopProductPrice.toInt()
                    totalPriceResult -= cartproduct.shopProductPrice.toInt()
                    binding.tvPriceResult?.text = if (totalPriceResult != 0) totalPriceResult.toString() else ""
                    println("我是price優 : $totalPriceResult")
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


                binding.floatingActionButton.setOnClickListener {
                    val etAddProduct: EditText =
                        itemViewBinding.root.findViewById(R.id.etAddProduct)
                    val quantity = etAddProduct.text.toString().toIntOrNull() ?: 0

                    val bundle = Bundle().apply {
                        putParcelableArray("cartproduct", cartproducts.toTypedArray())
                        putIntArray("quantity", quantityMap.toList().sortedBy { it.first }.map { it.second }.toIntArray())
                    }

                    val navController = Navigation.findNavController(itemView)
                    navController.navigate(R.id.action_shopFrontFragment_to_shopBuyFragment, bundle)
                }
            }
    }
}
