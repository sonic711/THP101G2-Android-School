package com.example.thp101g2_android_school.manage.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.databinding.FragmentManageMemberBinding
import com.example.thp101g2_android_school.manage.model.Members
import com.example.thp101g2_android_school.manage.viewmodel.ManageMemberViewModel

class ManageMemberFragment : Fragment() {
    private lateinit var binding: FragmentManageMemberBinding
    private lateinit var viewModel: ManageMemberViewModel
    private lateinit var recycleView: RecyclerView
    private lateinit var  adapter:ManageMemberAdapter
    private var memberList: List<Members> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ManageMemberViewModel::class.java)
        binding = FragmentManageMemberBinding.inflate(inflater, container, false)
//        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            recycleView = recyclerView
            adapter = ManageMemberAdapter(memberList) // 初始化 adapter
            recycleView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.memberuse?.observe(viewLifecycleOwner) { members ->
                if (recycleView.adapter == null) {
                    recycleView.adapter = ManageMemberAdapter(memberList)
                } else {
                    adapter.updateMembers(memberList)
                }

            }
        }

        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchMember(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchMember(newText)
                return true
            }
        })
    }

    private fun searchMember(query: String) {
        // 根據搜尋條件 query 更新 classList
        val filteredClasses = memberList.filter { member ->
            member.memberID.contains(query, ignoreCase = true)
        }
        adapter.updateMembers(filteredClasses)
    }


}


//






