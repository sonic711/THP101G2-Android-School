package com.example.thp101g2_android_school.shop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.shop.model.Product

class ProductViewModel : ViewModel() {
    private var productList = mutableListOf<Product>()
    val products : MutableLiveData<List<Product>>by lazy {MutableLiveData<List<Product>>()}

    init {
        loadProduct()
    }
    fun search(newText: String?) {
        if (newText.isNullOrEmpty()) {
            products.value = productList
        } else {
            // 如果不是空字串，宣告新的集合，走訪每個元素的name屬性，如果符合就放到新的集合並指派
            val searchLessionList = mutableListOf<Product>()
            productList.forEach { product ->
                if (product.name.contains(newText, true)) {
                    searchLessionList.add(product)
                }
            }
            // 指派給LiveData 就可以即時刷新View
            products.value = searchLessionList
        }
    }

    private fun loadProduct() {
        val productList = mutableListOf<Product>()
        productList.add(Product(R.drawable.java, "java", "Willian", price = 300.0.toString(),"java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書java書"))
        productList.add(Product(R.drawable.kotlin, "kotlin", "黃彬華老師.註", price = 500.0.toString(),"Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書Kotlin書"))
        productList.add(Product(R.drawable.python, "python", "Jerry", price = 450.0.toString(),"python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書python書"))
        productList.add(Product(R.drawable.senpai, "senpai", "AHHHH", price = 77777777777.777.toString(),"野獸先輩是日本㚻片公司COAT CORPORATION（官方網站）2001年發售的影片《Babylon 34 真夏の夜の淫夢 the IMP》中出演「田所」一役的一名㚻片演員。他出場的影片第四章副標題為「昏睡レイプ!野獣と化した先輩」，因此得名。其本人雖然成為了日本的網絡紅人，但是真實姓名，身份，和出演之後的行蹤均不詳。\n" +
                "\n" +
                "本文引自萌娘百科(https://zh.moegirl.org.cn )"))

        this.productList = productList
        this.products.value = this.productList
    }
}