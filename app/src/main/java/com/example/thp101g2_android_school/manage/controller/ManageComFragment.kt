package com.example.thp101g2_android_school.manage.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.ManageMainActivity
import com.example.thp101g2_android_school.databinding.FragmentManageComBinding
import com.example.thp101g2_android_school.manage.model.Comms
import com.example.thp101g2_android_school.manage.viewmodel.ManageComViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageCommsViewModel


class ManageComFragment : Fragment() {
    private lateinit var binding: FragmentManageComBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ManageComAdapter
    private var comList: List<Comms> = emptyList()


//    private lateinit var viewModel: ManageComViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: ManageCommsViewModel by viewModels()
        binding = FragmentManageComBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 隱藏標題列
        (requireActivity() as ManageMainActivity).supportActionBar?.hide()

        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            adapter = ManageComAdapter(comList)
            viewModel?.comms?.observe(viewLifecycleOwner) {
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = ManageComAdapter(comList)
                } else {
                    adapter.updateComms(comList)
                }
            }

        }
//        recyclerView = binding.recyclerView
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        adapter = ManageComAdapter(comList)
//        recyclerView.adapter = adapter

        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchComms(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchComms(newText)
                return true
            }
        })
    }

        private fun searchComms(query: String) {
            // 根據搜尋條件 query 更新 classList
            val filteredComm = comList.filter { comms ->
                comms.txtId.contains(query, ignoreCase = true)
            }
            adapter.updateComms(filteredComm)
        }


    }
