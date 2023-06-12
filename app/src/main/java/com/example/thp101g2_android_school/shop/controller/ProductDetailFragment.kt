package com.example.thp101g2_android_school.shop.controller

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.databinding.FragmentProductDetailBinding
import com.example.thp101g2_android_school.shop.model.Product
import com.example.thp101g2_android_school.shop.model.ShopFavorite
import com.example.thp101g2_android_school.shop.model.ShopingCart
import com.example.thp101g2_android_school.shop.viewmodel.ProductViewModel
import com.example.thp101g2_android_school.shop.viewmodel.ShopMainViewModel
import androidx.databinding.BindingAdapter
import com.example.thp101g2_android_school.member.model.Member
import com.google.gson.JsonObject
import kotlin.random.Random

class ProductDetailFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailBinding
    private lateinit var product: Product
    private var isFavorite = false
    private var isShoppingCart = false
    private var favoriteProducts: List<ShopFavorite> = emptyList()
    private var shoppingcartProduct: List<ShopingCart> = emptyList()
    private val productViewModel: ProductViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // 呈現標題列
        (requireActivity() as MainActivity).supportActionBar?.apply {
            // Show the support action bar if it is hidden
            show()
            title = "商品細項頁面"
            setDisplayHomeAsUpEnabled(true)
        }
        val viewModel: ShopMainViewModel by viewModels()
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var currentMember: Member? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/members", "OPTIONS")
        println(currentMember)
        val memberno = currentMember?.memberNo
        println(memberno)

        arguments?.let { bundle ->
            bundle.getSerializable("product")?.let {
                product = it as Product
                binding.viewModel?.product?.value = product


            }
            //TODO 透過productid狀態存我的最愛or購物車，布林值判斷DB內有沒有該productid，有就ture沒有就false，不要用按鈕狀態判斷
            arguments?.let { bundle ->
                favoriteProducts =
                    bundle.getSerializable("favoriteProducts") as? List<ShopFavorite>
                        ?: emptyList()
                isFavorite = bundle.getBoolean("isFavorite")
                binding.favtoggleButton.isChecked = isFavorite
                Log.d("ProductDetailFragment", "isFavorite: $isFavorite")
            }

            arguments?.let { bundle ->
                shoppingcartProduct =
                    bundle.getSerializable("shoppingcartProduct") as? List<ShopingCart>
                        ?: emptyList()
                isShoppingCart = bundle.getBoolean("isShoppingCart")
                binding.CartToggleButton.isChecked = isShoppingCart
                Log.d("ProductDetailFragment", "isShoppingCart: $isShoppingCart")
            }


            binding.btnBack.setOnClickListener {
                Navigation.findNavController(requireView()).navigateUp()
                val currentFragment =
                    parentFragmentManager.findFragmentById(R.id.fragment_Container)
                currentFragment?.let {
                    parentFragmentManager.beginTransaction()
                        .remove(it)
                        .commit()
                }
            }
            binding.favtoggleButton.setOnClickListener {
                productViewModel.onFavoriteProductClicked(product.shopProductId.toString())
                productViewModel.onShoppingCartProductClicked(product.shopProductId.toString())
                if (isFavorite) {
                    // TODO 先寫死會員編號1 假定登入
                    Toast.makeText(context, "刪除自我的最愛", Toast.LENGTH_SHORT).show()
                    println("PDF最愛刪除一筆")
                    val productId = product.shopProductId
                    val respbody = requestTask<JsonObject>(
                        "$url/shop/favorite/$productId",
                        "DELETE"
                    )
                } else {
                    Toast.makeText(context, "新增至我的最愛", Toast.LENGTH_SHORT).show()
                    println("PDF最愛增加一筆")
                    val randomNumber = Random.nextInt(10000000)
                    val jsonObj = JsonObject()
                    jsonObj.addProperty("shopFavoriteId", randomNumber)
                    jsonObj.addProperty("memberNo", memberno)
                    jsonObj.addProperty("shopProductId", product.shopProductId)
                    val respbody = requestTask<JsonObject>(
                        "$url/shop/favorite",
                        "POST", jsonObj
                    )
                    isFavorite = !isFavorite // 更新状态

                }
            }
            binding.CartToggleButton.setOnClickListener {
                if (isShoppingCart) {
                    // TODO 先寫死會員編號1 假定登入
                    Toast.makeText(context, "刪除自我的購物車", Toast.LENGTH_SHORT).show()
                    println("PDF購物車刪除一筆")
                    val productId = product.shopProductId
                    val respbody = requestTask<JsonObject>(
                        "$url/shop/shoppingcart/$productId",
                        "DELETE"
                    )
                } else {
                    Toast.makeText(context, "增加至我的購物車", Toast.LENGTH_SHORT).show()
                    println("PDF購物車增加一筆")
                    val randomNumber = Random.nextInt(10000000)
                    val jsonObj = JsonObject()
                    jsonObj.addProperty("shoppingCartId", randomNumber)
                    jsonObj.addProperty("memberNo", memberno)
                    jsonObj.addProperty("shopProductId", product.shopProductId)
                    jsonObj.addProperty("shopOrderCount", product.shopProductCount)
                    val respbody = requestTask<JsonObject>(
                        "$url/shop/shoppingcart",
                        "POST", jsonObj
                    )
                }
                isShoppingCart = !isShoppingCart

            }

        }
    }
}

