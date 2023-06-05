package com.example.thp101g2_android_school.community.viewmodel

import android.app.AlertDialog
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.community.model.Post

class EditPostViewModel : ViewModel() {
    val post: MutableLiveData<Post> by lazy { MutableLiveData<Post>(Post()) }
    fun editPost(view: View) {
        val post = this.post.value
        AlertDialog.Builder(view.context)
            .setMessage("確定修改文章？")
            .setPositiveButton("是"){_, _->
                requestTask<Post>("$url/community/post", "PUT", post)
                Navigation.findNavController(view).popBackStack()
            }
            .setNegativeButton("否", null)
            .setCancelable(false) // 點旁邊可不可以取消
            .show()
    }

}