package com.example.thp101g2_android_school.manage.controller

import androidx.lifecycle.ViewModelProvider
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
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentManageClassBinding
import com.example.thp101g2_android_school.databinding.FragmentManageMaBinding
import com.example.thp101g2_android_school.manage.model.Classes
import com.example.thp101g2_android_school.manage.model.ManageAccountBean
import com.example.thp101g2_android_school.manage.model.Mas
import com.example.thp101g2_android_school.manage.viewmodel.ManageMaViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageMasViewModel

class ManageMaFragment : Fragment() {
    private lateinit var binding: FragmentManageMaBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ManageMaAdapter
    private var maList: List<ManageAccountBean> = emptyList()


    private lateinit var viewModel: ManageMasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: ManageMasViewModel by viewModels()
        binding = FragmentManageMaBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.memberBack.setOnClickListener {
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
            adapter = ManageMaAdapter(maList)
            viewModel?.mas?.observe(viewLifecycleOwner) { mas ->
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = ManageMaAdapter(mas)
                } else {
                    adapter.updateMas(mas)
                }
            }

            managerSet.setOnClickListener {
                findNavController().navigate(R.id.action_manageMaFragment_to_manageMaSettingFragment)
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
            (ma.manageId?.toString() == query)
        }
        adapter.updateMas(filteredMa)
    }


}



