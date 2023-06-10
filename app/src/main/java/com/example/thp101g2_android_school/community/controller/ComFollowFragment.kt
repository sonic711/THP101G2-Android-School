package com.example.thp101g2_android_school.community.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.ActivityViewModel
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
    val activityViewModel: ActivityViewModel by activityViewModels()
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
        activityViewModel.memberObj.value?.let { viewModel.loadData(it) }
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.followedPosts?.observe(viewLifecycleOwner) {

                if (recyclerView.adapter == null) {
                    recyclerView.adapter = PostAdapter(it)
                } else {
                    // 搜尋更新
                    (recyclerView.adapter as PostAdapter).updatePosts(it)
                }
            }
            if (viewModel?.followedPostList.isNullOrEmpty()) {
                tvNoFollow.visibility = View.VISIBLE
            } else tvNoFollow.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        if (!activityViewModel.memberObj.value?.let { viewModel.loadData(it) }!!) {
            viewModel.followedPosts.value = listOf()
            binding.tvNoFollow.visibility = View.VISIBLE
        } else {
            binding.tvNoFollow.visibility = View.GONE
        }
    }
}