package com.example.thp101g2_android_school.member.controller

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.databinding.FragmentMemOthersFollowersBinding
import com.example.thp101g2_android_school.member.controller.OthersFollowerAdapter
import com.example.thp101g2_android_school.member.viewModel.MemOthersHomeViewModel


class MemOthersFollowersFragment : Fragment() {
    private lateinit var binding: FragmentMemOthersFollowersBinding
    private val viewModel: MemOthersHomeViewModel by activityViewModels()
    private val my_tag = "TAG_${javaClass.simpleName}"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMemOthersFollowersBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as MainActivity).supportActionBar?.show()
        activity?.setTitle("追蹤中")
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            Log.d(my_tag, "FollowersSize: ${viewModel?.fans?.value?.size}")
            viewModel?.followers?.observe(viewLifecycleOwner) { followers ->

                if (recyclerView.adapter == null) {
                    recyclerView.adapter = OthersFollowerAdapter(followers)
                } else {
                    (recyclerView.adapter as OthersFollowerAdapter).updateFollowers(followers)
                }
            }
            viewModel!!.loadFollowers()
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel?.searchFollower(newText)
                    return true
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }
            })
        }
    }


}