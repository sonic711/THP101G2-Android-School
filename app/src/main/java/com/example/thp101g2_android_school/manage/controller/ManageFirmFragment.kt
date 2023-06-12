package com.example.thp101g2_android_school.manage.controller

import android.os.Bundle
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
import com.example.thp101g2_android_school.databinding.FragmentManageFirmBinding
import com.example.thp101g2_android_school.manage.model.Firms
import com.example.thp101g2_android_school.manage.viewmodel.ManageFirmsViewModel

class ManageFirmFragment : Fragment() {
    private lateinit var binding: FragmentManageFirmBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ManageFirmAdapter
    private var firmList: List<Firms> = emptyList()


    companion object {
        fun newInstance() = ManageFirmFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: ManageFirmsViewModel by viewModels()
        binding = FragmentManageFirmBinding.inflate(inflater, container, false)
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
            adapter = ManageFirmAdapter(firmList)
            viewModel?.firms?.observe(viewLifecycleOwner) { firms ->
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = ManageFirmAdapter(firms)
                } else {
                    adapter.updateFirms(firms)
                }
            }
        }

        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
              searchFirms(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
               searchFirms(newText)
                return true
            }
        })
    }

    private fun searchFirms(query: String) {
        // 根據搜尋條件 query 更新 firmList
        val filteredFirm = firmList.filter { firms ->
            firms.shopProductName.toString() == query
        }
        adapter.updateFirms(filteredFirm)
    }
}