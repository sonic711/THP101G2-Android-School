package com.example.thp101g2_android_school.member.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.member.viewModel.MemBlockViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentMemBlockBinding

class MemBlockFragment : Fragment() {
    private lateinit var binding: FragmentMemBlockBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: MemBlockViewModel by viewModels()
        binding = FragmentMemBlockBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ((requireActivity()) as MainActivity).supportActionBar?.show()
        activity?.setTitle("封鎖名單")
        with(binding) {
            viewModel?.initialize()
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.blockedUsers?.observe(viewLifecycleOwner) { blockUsers ->
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = BlockedUserAdapter(blockUsers)
                } else {
                    (recyclerView.adapter as BlockedUserAdapter).updateBlockedUsers(blockUsers)
                }
            }
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel?.searchBlockedUser(newText)
                    return true
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }
            })

        }
    }


}