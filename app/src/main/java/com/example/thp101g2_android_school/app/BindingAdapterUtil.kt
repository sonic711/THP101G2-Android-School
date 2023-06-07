package com.example.thp101g2_android_school.app

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import java.io.ByteArrayOutputStream
import java.util.*

// 單向取得圖片
// base64 -> 位元組 -> bitmap
@RequiresApi(Build.VERSION_CODES.O)
@BindingAdapter("imgBase64")
fun ImageView.setImgBase64(base64: String?){
    base64?.let {
//        val byteArray = Base64.decode(base64, Base64.DEFAULT)
        val byteArray = Base64.getDecoder().decode(base64)
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        setImageBitmap(bitmap)
//        Log.d("TAG_setImgBase64", "base64: $base64")
    }
}
// 雙向綁定圖片
@RequiresApi(Build.VERSION_CODES.O)
@InverseBindingAdapter(attribute = "imgBase64")
fun ImageView.getImgBase64(): String?{
    drawable?.let {
        val stream = ByteArrayOutputStream()
        it.toBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()
//        return Base64.encodeToString(byteArray, Base64.DEFAULT)
//        Log.d("TAG_getImgBase64", "byteArray: $byteArray")
        return Base64.getEncoder().encodeToString(byteArray)
    }
    return null
}

// 名稱要固定xxxAttrChanged
// 大致上就是新增一個onChange事件時，會去呼叫上面的Setter
// 如果無法啟動，要去build.gradle把java三個地方改成17板
@BindingAdapter("imgBase64AttrChanged")
fun ImageView.setOnImgBase64AttrChangedListener(listener: InverseBindingListener) {
    addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ -> listener.onChange() }
}