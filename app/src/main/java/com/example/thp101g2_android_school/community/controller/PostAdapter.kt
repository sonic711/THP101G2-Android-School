package com.example.thp101g2_android_school.community.controller

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.byteArrayToBitmap
import com.example.thp101g2_android_school.community.model.Post
import com.example.thp101g2_android_school.community.model.Reply
import com.example.thp101g2_android_school.community.viewmodel.ComMainViewModel
import com.example.thp101g2_android_school.databinding.ComMainItemviewBinding


class PostAdapter(private var posts: List<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(val itemViewBinding: ComMainItemviewBinding) :

        RecyclerView.ViewHolder(itemViewBinding.root)
    /**
     * 更新文章列表內容
     * @param posts 新的文章列表
     */
    fun updatePosts(posts: List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }
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
            // 如果Po文者沒有大頭貼，就給一個預設的
            if (post.profilePhoto != null) {
                val img = byteArrayToBitmap(post.profilePhoto!!)
                itemViewBinding.imageView.setImageBitmap(img)
            }else{
                itemViewBinding.imageView.setBackgroundResource(R.drawable.com_user)
            }
            // TODO 按下會員資料後跳轉到該會員主頁

            // 按下文章後跳轉到該文章細節頁面
            val bundle = Bundle()
            bundle.putSerializable("post", post)

            itemView.setOnClickListener {
                // 按下CardView後把該文章編號存入偏好設定檔，做瀏覽記錄用
                saveArticleIdToSharedPreferences(itemView.context, post.comPostId!!)
                // 按下CardView後把資料送到該文章頁面
                Navigation.findNavController(it).navigate(R.id.postDetailFragment, bundle)

            }
            // 如果是Value是1，代表已讀過，就改變標題顏色
            if (getViewedArticleIdFromSharedPreferences(itemView.context, post.comPostId!!) == 1) {
                itemViewBinding.tvTitle.setTextColor(itemView.context.getColor(R.color.gray_500))
            } else {
                itemViewBinding.tvTitle.setTextColor(itemView.context.getColor(R.color.black))
            }
            // 載入labelRecyclerView文章標籤
            if(itemViewBinding.viewModel?.post?.value?.labels.isNullOrEmpty()){

                itemViewBinding.labelRecyclerView.visibility = View.GONE
                return
            }
            with(itemViewBinding.labelRecyclerView) {
                // 如果第一個標籤的id = 0 就不顯示標籤recyclerView
                if (post.labels!![0].comLabelId == "0") {
                    itemViewBinding.labelRecyclerView.visibility = View.GONE
                }else itemViewBinding.labelRecyclerView.visibility = View.VISIBLE
                layoutManager = GridLayoutManager(holder.itemView.context, 3)
                adapter = post.labels?.let { LabelAdapter(it) }
            }
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    /**
     *  把該文章編號作為Key存入偏好設定檔，做瀏覽記錄用，
     *  Value固定存入為1，代表點擊過，之後取用。
     *  @param context Context物件
     *  @param articleId 文章編號
     *  @author Sean
     */
    private fun saveArticleIdToSharedPreferences(context: Context, articleId: String) {
        // TODO 之後改成用JSON存
        val sharedPrefs = context.getSharedPreferences("com_app_prefs", Context.MODE_PRIVATE)
        sharedPrefs.edit()
            .putInt("viewed_article_id$articleId", 1)
            .apply()
    }

    /**
     *  從SharedPreferences中讀取文章編號，做瀏覽記錄用，
     *  Value為1，代表點擊過，null 代表沒點擊過。
     *  @param context Context物件
     *  @param articleId 文章編號
     *  @author Sean
     */
    private fun getViewedArticleIdFromSharedPreferences(context: Context, articleId: String): Int? {
        // TODO 之後改成取JSON
        val sharedPrefs = context.getSharedPreferences("com_app_prefs", Context.MODE_PRIVATE)
        // 如果找不到Key，就回傳 null。如果找到Key，就檢查值是否為預設值 -1。如果不是，就回傳實際值；否則回傳 null。
        return sharedPrefs.getInt("viewed_article_id$articleId", -1).takeIf { it != -1 }
    }
}