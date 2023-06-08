package com.example.thp101g2_android_school.manage.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.ManageMainActivity
import com.example.thp101g2_android_school.databinding.FragmentManageReportBinding
import com.example.thp101g2_android_school.manage.model.Reports
import com.example.thp101g2_android_school.manage.viewmodel.ManageReportViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageReportsViewModel

class ManageReportFragment : Fragment() {
    private lateinit var binding: FragmentManageReportBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ManageReportAdapter
    private var reportList: List<Reports> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: ManageReportsViewModel by viewModels()
        binding = FragmentManageReportBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.Back.setOnClickListener {
            Navigation.findNavController(requireView()).navigateUp()
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 隱藏標題列
        (requireActivity() as ManageMainActivity).supportActionBar?.hide()

        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            //多了底下這串
            adapter = ManageReportAdapter(reportList)
            viewModel?.reports?.observe(viewLifecycleOwner) { reports ->
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = ManageReportAdapter(reports)
                } else {
                    adapter.updateReports(reports)
                }
            }

        }

        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchreport(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchreport(newText)
                return true
            }
        })
    }

    private fun  searchreport(query: String) {
        // 根據搜尋條件 query 更新 classList
        val filteredReports = reportList.filter { reports ->
            reports.reportID.contains(query, ignoreCase = true)
        }
        adapter.updateReports(filteredReports)
    }

}
