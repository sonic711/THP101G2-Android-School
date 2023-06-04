package com.example.thp101g2_android_school.member.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.databinding.FragmentMemFollowersBinding
import com.example.thp101g2_android_school.member.controller.FollowerAdapter
import com.example.thp101g2_android_school.member.viewModel.MemFollowersViewModel
import com.example.thp101g2_android_school.member.viewModel.MemberViewModel

class MemFollowersFragment : Fragment() {
    private lateinit var binding: FragmentMemFollowersBinding
    private val viewModel: MemberViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMemFollowersBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.followers?.observe(viewLifecycleOwner) { followers ->
                if (recyclerView.adapter == null) {
                    println(followers)
                    recyclerView.adapter = FollowerAdapter(followers)
                } else {
                    (recyclerView.adapter as FollowerAdapter).updateFollowers(followers)
                }

            }
        }
    }


}