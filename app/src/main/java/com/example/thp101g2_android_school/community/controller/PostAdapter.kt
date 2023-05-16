package com.example.thp101g2_android_school.community.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.community.model.Post
import com.example.thp101g2_android_school.community.viewmodel.ComMainViewModel
import com.example.thp101g2_android_school.databinding.ComMainItemviewBinding
import kotlinx.coroutines.NonDisposableHandle.parent

class PostAdapter(private var posts: List<Post>):
    RecyclerView.Adapter<PostAdapter.PostViewHolder>(){

    class PostViewHolder(val itemViewBinding: ComMainItemviewBinding) :

        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemViewBinding = ComMainItemviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = ComMainViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()

        return PostViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        with(holder) {
            itemViewBinding.viewModel?.post?.value = post
            // TODO 按下會員資料後跳轉到該會員主頁
            val bundle = Bundle()
            bundle.putSerializable("post", post)

            itemView.setOnClickListener {
                // TODO 按下CardView後把資料送到下一頁
            }
            // 載入對應的文章標籤
            itemViewBinding.labelRecyclerView.layoutManager = GridLayoutManager(holder.itemView.context, 3)
            itemViewBinding.labelRecyclerView.adapter = LabelAdapter(post.labels)
        }
    }
    override fun getItemCount(): Int {
        return posts.size
    }

}