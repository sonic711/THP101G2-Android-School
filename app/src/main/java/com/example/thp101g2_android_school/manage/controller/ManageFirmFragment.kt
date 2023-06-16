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
import com.example.thp101g2_android_school.databinding.FragmentManageFirmBinding
import com.example.thp101g2_android_school.manage.model.Firms
import com.example.thp101g2_android_school.manage.viewmodel.ManageFirmsViewModel

class ManageFirmFragment : Fragment() {
    private lateinit var binding: FragmentManageFirmBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ManageFirmAdapter
    private val viewModel: ManageFirmsViewModel by viewModels()
//    companion object {
//        fun newInstance() = ManageFirmFragment()}
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


//        (requireActivity() as ManageMainActivity).supportActionBar?.hide()

        // 初始化 RecyclerView 和适配器
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ManageFirmAdapter(emptyList())
        recyclerView.adapter = adapter

        // 设置按钮点击事件监听
        //with(binding){}
        binding.Processed.setOnClickListener {
            viewModel.filterFirmsByCondition("0")
        }

        binding.Unprocessed.setOnClickListener {
            viewModel.filterFirmsByCondition("1")
            Toast.makeText(view.context,"已下架課程", Toast.LENGTH_SHORT).show()
        }
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
        // 观察 memberso 数据变化//就是隨時觀察有沒有搜尋 有舊更新畫面
        viewModel.firms.observe(viewLifecycleOwner) { members ->
            adapter.updateFirms(members)
        }
    }
}
