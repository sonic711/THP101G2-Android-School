    package com.example.thp101g2_android_school.shop.viewmodel

    import android.graphics.BitmapFactory
    import android.util.Log
    import android.widget.ImageView
    import androidx.databinding.BindingAdapter
    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModel
    import com.example.thp101g2_android_school.app.requestTask
    import com.example.thp101g2_android_school.app.url
    import com.example.thp101g2_android_school.shop.model.Product
    import com.google.gson.JsonObject

    class ShopMainViewModel: ViewModel() {
        val product: MutableLiveData<Product> by lazy { MutableLiveData<Product>() }
        val productid : String? = product.value?.shopProductId ?: ""

        fun addtofavorite(){
            println(productid)
            Log.i("productid",product.value?.shopProductId.toString())
        }
        companion object {
            @BindingAdapter("imageByteArray")
            @JvmStatic
            fun setImageByteArray(imageView: ImageView, byteArray: ByteArray?) {
                byteArray?.let {
                    val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                    imageView.setImageBitmap(bitmap)
                }
            }
        }


    }