package com.example.thp101g2_android_school.community.controller

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.community.viewmodel.ComAllPostViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentComAllPostBinding
import com.example.thp101g2_android_school.databinding.FragmentComMainBinding

class ComAllPostFragment : Fragment() {
    private lateinit var binding:FragmentComAllPostBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: ComAllPostViewModel by viewModels()
        binding = FragmentComAllPostBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.posts?.observe(viewLifecycleOwner) { posts ->
                // 如果Adapter尚未建立過，透過建構式建立LessionAdapter
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = PostAdapter(posts)
                } else {
                    // TODO 之後補上更新
//                    (recyclerView.adapter as PostAdapter).updateFriends(posts)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("所有文章頁面銷毀")
    }


    // 將指定文章的標題設置為灰色
    fun setArticleTitleGray(context: Context, articleId: Int) {
        val sharedPrefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val lastViewedArticleId = sharedPrefs.getInt("last_viewed_article_id", -1)
        if (lastViewedArticleId == articleId) {
            // 如果點擊過這篇文章，則將其標題設置為灰色
//            val textView = findViewById<TextView>(R.id.article_title)
//            textView.setTextColor(ContextCompat.getColor(context, R.color.gray))
        }
    }

}