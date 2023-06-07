    package com.example.thp101g2_android_school.shop.viewmodel

    import android.graphics.BitmapFactory
    import android.widget.ImageView
    import androidx.databinding.BindingAdapter
    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModel
    import com.example.thp101g2_android_school.shop.model.Product
    import com.example.thp101g2_android_school.shop.model.ShopFavorite

    class ShopFavoriteViewModel : ViewModel() {
        val favoriteproduct: MutableLiveData<ShopFavorite> by lazy { MutableLiveData<ShopFavorite>() }

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