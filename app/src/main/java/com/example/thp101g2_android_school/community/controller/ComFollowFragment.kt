package com.example.thp101g2_android_school.community.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.byteArrayToBitmap
import com.example.thp101g2_android_school.community.model.Post
import com.example.thp101g2_android_school.community.viewmodel.ComFollowPostViewModel
import com.example.thp101g2_android_school.community.viewmodel.ComFollowViewModel
import com.example.thp101g2_android_school.databinding.ComFollowedpostItemviewBinding
import com.example.thp101g2_android_school.databinding.FragmentComFollowBinding

class ComFollowFragment : Fragment() {
    private lateinit var binding: FragmentComFollowBinding
    val viewModel: ComFollowViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComFollowBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.followedPosts?.observe(viewLifecycleOwner) {
                recyclerView.adapter = ComFollowAdapter(it)
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = PostAdapter(it)
                } else {
                    // 搜尋更新
                    (recyclerView.adapter as ComFollowAdapter).updatePosts(it)
                }
            }
            if (viewModel?.followedPostList.isNullOrEmpty()) {
                tvNoFollow.visibility = View.VISIBLE
            } else tvNoFollow.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        if (!viewModel.loadData()) {
            viewModel.followedPosts.value = listOf()
            binding.tvNoFollow.visibility = View.VISIBLE
        } else {
            binding.tvNoFollow.visibility = View.GONE
        }
    }
}

class ComFollowAdapter(private var list: List<Post>) :
    RecyclerView.Adapter<ComFollowAdapter.ViewHolder>() {
    class ViewHolder(val binding: ComFollowedpostItemviewBinding) : RecyclerView.ViewHolder(binding.root)

    /**
     * 更新文章列表內容
     * @param posts 新的文章列表
     */
    fun updatePosts(posts: List<Post>) {
        this.list = posts
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ComFollowedpostItemviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.viewModel = ComFollowPostViewModel()
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = list[position]
        with(holder) {
            binding.viewModel?.post?.value = post
            if (post.profilePhoto != null) {
                val img = byteArrayToBitmap(post.profilePhoto!!)
                binding.imageView.setImageBitmap(img)
            } else {
                binding.imageView.setBackgroundResource(R.drawable.com_user)
            }

            if (post.labels?.get(0)?.comLabelId == "0") {
                binding.labelCardView1.visibility = View.GONE
            }
            when (post.labels?.size) {
                0 -> {
                    binding.labelCardView1.visibility = View.GONE
                    binding.labelCardView2.visibility = View.GONE
                    binding.labelCardView3.visibility = View.GONE
                }

                1 -> {
                    binding.tvLabelName1.text = post.labels!!.get(0).comLabelName
                    binding.labelCardView1.visibility = View.VISIBLE
                    binding.labelCardView2.visibility = View.GONE
                    binding.labelCardView3.visibility = View.GONE
                }

                2 -> {
                    binding.tvLabelName1.text = post.labels!!.get(0).comLabelName
                    binding.tvLabelName2.text = post.labels!!.get(1).comLabelName
                    binding.labelCardView1.visibility = View.VISIBLE
                    binding.labelCardView2.visibility = View.VISIBLE
                    binding.labelCardView3.visibility = View.GONE
                }

                3 -> {
                    binding.tvLabelName1.text = post.labels!!.get(0).comLabelName
                    binding.tvLabelName2.text = post.labels!!.get(1).comLabelName
                    binding.tvLabelName3.text = post.labels!!.get(2).comLabelName
                    binding.labelCardView1.visibility = View.VISIBLE
                    binding.labelCardView2.visibility = View.VISIBLE
                    binding.labelCardView3.visibility = View.VISIBLE
                }
            }
        }

    }
}