package com.example.thp101g2_android_school.manage.controller

import android.app.DownloadManager.Query
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.ManageMainActivity
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.FragmentManageComBinding
import com.example.thp101g2_android_school.manage.model.ManageComReportBean
import com.example.thp101g2_android_school.manage.viewmodel.ManageCommsViewModel
import java.lang.reflect.Member


class ManageComFragment : Fragment() {
    private lateinit var binding: FragmentManageComBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ManageComAdapter
    private var comList: List<ManageComReportBean> = emptyList()

    companion object {
        fun newInstance() = ManageComFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentManageComBinding.inflate(inflater, container, false)
        val viewModel: ManageCommsViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // 隱藏標題列
        (requireActivity() as ManageMainActivity).supportActionBar?.hide()

        with(binding) {
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            adapter = ManageComAdapter(comList)
            binding.viewModel?.comm?.observe(viewLifecycleOwner) {comms ->
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = ManageComAdapter(comms)
                } else {
                    this@ManageComFragment.adapter.updateComments(comms)
                }
            }
            binding.Back.setOnClickListener {
                Navigation.findNavController(requireView()).navigateUp()
            }
        }

        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchComments(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchComments(newText)
                return true
            }
        })
    }

        private fun searchComments(query: String) {
            // 根據搜尋條件 query 更新 classList
            val filteredComm = comList.filter { comms ->
                (comms.comPostId.toString() == query)
            } //同class有問題
            adapter.updateComments(filteredComm)
        }
    }
