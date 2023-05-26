package com.example.thp101g2_android_school.manage.controller

import androidx.lifecycle.ViewModelProvider
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
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentManageClassBinding
import com.example.thp101g2_android_school.databinding.FragmentManageMaBinding
import com.example.thp101g2_android_school.manage.model.Classes
import com.example.thp101g2_android_school.manage.model.Mas
import com.example.thp101g2_android_school.manage.viewmodel.ManageMaViewModel

class ManageMaFragment : Fragment() {
    private lateinit var binding: FragmentManageMaBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ManageMaAdapter
    private var maList: List<Mas> = emptyList()


    private lateinit var viewModel: ManageMaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: ManageMaViewModel by viewModels()
        binding = FragmentManageMaBinding.inflate(inflater, container, false)
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
            adapter = ManageMaAdapter(maList)
            viewModel?.mao?.observe(viewLifecycleOwner) { mas ->
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = ManageMaAdapter(maList)
                } else {
                    adapter.updateMas(maList)
                }
            }

        }
        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchMa(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchMa(newText)
                return true
            }
        })
    }

    private fun searchMa(query: String) {
        // 根據搜尋條件 query 更新 classList
        val filteredMa = maList.filter { ma ->
            ma.manageID.contains(query, ignoreCase = true)
        }
        adapter.updateMas(filteredMa)
    }


}



