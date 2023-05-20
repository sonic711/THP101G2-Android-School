package com.example.thp101g2_android_school.manage.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentManageClassBinding
import com.example.thp101g2_android_school.manage.model.Classes
import com.example.thp101g2_android_school.manage.viewmodel.ManageClassesViewModel


class ManageClassesFragment : Fragment() {
    private lateinit var binding: FragmentManageClassBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ManageClassAdapter
    private var classList: List<Classes> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: ManageClassesViewModel by viewModels()
        binding = FragmentManageClassBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 隱藏標題列
        (requireActivity() as ManageMainFragment).supportActionBar?.hide()

        with(binding){
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.classes?.observe(viewLifecycleOwner){classes ->
                if(recyclerView.adapter == null){
                    recyclerView.adapter = ManageClassAdapter(classes)
                }else{
                    adapter.updateClasses(classes)
                }
            }

        }








//        // 初始化 RecyclerView、LayoutManager 和 Adapter
//        recyclerView = binding.recyclerView
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        adapter = ManageClassAdapter(classList)
//        recyclerView.adapter = adapter
//
//        // 實例化 ViewModel
//        viewModel = ViewModelProvider(this).get(ManageClassesViewModel::class.java)
//
//        // 觀察 classList 變化，更新 Adapter
//        viewModel.classes.observe(viewLifecycleOwner) { classes ->
//            adapter.updateClasses(classes)
//        }

        // 實作搜尋功能
        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchClasses(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchClasses(newText)
                return true
            }
        })
    }

    private fun searchClasses(query: String) {
        // 根據搜尋條件 query 更新 classList
        val filteredClasses = classList.filter { classes ->
            classes.name.contains(query, ignoreCase = true)
        }
        adapter.updateClasses(filteredClasses)
    }


    }


