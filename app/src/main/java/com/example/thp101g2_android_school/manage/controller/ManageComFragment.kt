package com.example.thp101g2_android_school.manage.controller

import android.app.DownloadManager.Query
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
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
import com.example.thp101g2_android_school.manage.viewmodel.ManageMembersViewModel
import java.lang.reflect.Member


class ManageComFragment : Fragment() {
    private lateinit var binding: FragmentManageComBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ManageComAdapter
    private val viewModel: ManageCommsViewModel by viewModels()


//    companion object {
//        fun newInstance() = ManageComFragment()
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentManageComBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.Back.setOnClickListener {
            Navigation.findNavController(requireView()).navigateUp()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ManageComAdapter(emptyList())
        recyclerView.adapter = adapter


        binding.Unprocessed.setOnClickListener {
            viewModel.filterComByCondition(true)
            Toast.makeText(view.context,"已下架貼文", Toast.LENGTH_SHORT).show()
        }

        binding.Processed.setOnClickListener {
            viewModel.filterComByCondition(false)
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


        viewModel.comm.observe(viewLifecycleOwner) { members ->
        adapter.updateComments(members)
             }
        }
    }
