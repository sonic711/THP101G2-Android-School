package com.example.thp101g2_android_school.manage.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.ManageMainActivity
import com.example.thp101g2_android_school.databinding.FragmentManageMemberBinding
import androidx.navigation.fragment.findNavController
import com.example.thp101g2_android_school.manage.viewmodel.ManageMembersViewModel



class ManageMemberFragment : Fragment() {
    private lateinit var binding: FragmentManageMemberBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ManageMemberAdapter
    private val viewModel: ManageMembersViewModel by viewModels()

    companion object {
        fun newInstance() = ManageMemberFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageMemberBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.Back.setOnClickListener {
            Navigation.findNavController(requireView()).navigateUp()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as ManageMainActivity).supportActionBar?.hide()

        // 初始化 RecyclerView 和适配器
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ManageMemberAdapter(emptyList())
        recyclerView.adapter = adapter

        // 设置按钮点击事件监听
        //with(binding){}
        binding.Processed.setOnClickListener {
            viewModel.filterMembersByCondition(true)
            Toast.makeText(view.context,"已處理會員檢舉",Toast.LENGTH_SHORT).show()
        }

        binding.Unprocessed.setOnClickListener {
            viewModel.filterMembersByCondition(false)
            Toast.makeText(view.context,"未處理會員檢舉",Toast.LENGTH_SHORT).show()
        }
//        該做一個下架或封鎖的
//        binding.Unprocessed.setOnClickListener {
//            viewModel.filterMembersByCondition(false)
//            Toast.makeText(view.context,"封鎖會員",Toast.LENGTH_SHORT).show()
//        }


        // 设置搜索功能
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.search(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.search(newText)
                return true
            }
        })

        // 观察 memberso 数据变化
        viewModel.memberso.observe(viewLifecycleOwner) { members ->
            adapter.updateMembers(members)
        }
    }
}






