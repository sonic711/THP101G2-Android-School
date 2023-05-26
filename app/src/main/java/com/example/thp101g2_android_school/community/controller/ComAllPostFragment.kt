package com.example.thp101g2_android_school.community.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.community.viewmodel.ComAllPostViewModel
import com.example.thp101g2_android_school.databinding.FragmentComAllPostBinding

class ComAllPostFragment : Fragment() {
    private lateinit var binding: FragmentComAllPostBinding
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
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.posts?.observe(viewLifecycleOwner) { posts ->
                // 如果Adapter尚未建立過，透過建構式建立LessionAdapter
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = PostAdapter(posts)
                } else {
                    // TODO 之後補上搜尋更新
//                    (recyclerView.adapter as PostAdapter).updateFriends(posts)
                }
            }
        }
    }

}