package com.example.thp101g2_android_school.manage.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.ManageMainActivity
import com.example.thp101g2_android_school.databinding.FragmentManageClassBinding
import com.example.thp101g2_android_school.manage.model.Classes
import com.example.thp101g2_android_school.manage.model.CourseReportBean
import com.example.thp101g2_android_school.manage.viewmodel.ManageClassesViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageMembersViewModel


open class ManageClassesFragment : Fragment() {
    private lateinit var binding: FragmentManageClassBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ManageClassAdapter
    private val viewModel: ManageClassesViewModel by viewModels()

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

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ManageClassAdapter(emptyList())
        recyclerView.adapter = adapter

//        (requireActivity() as ManageMainActivity).supportActionBar?.hide()

        with(binding) {

            Back.setOnClickListener {
                Navigation.findNavController(requireView()).navigateUp()
            }
            Unprocessed.setOnClickListener {
                viewModel?.filterClassesByCondition(true)
            }

            Processed.setOnClickListener {
                viewModel?.filterClassesByCondition(false)
                Toast.makeText(view.context,"已下架課程", Toast.LENGTH_SHORT).show()
            }
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

        viewModel.classes.observe(viewLifecycleOwner) { classes ->
            adapter.updateClasses(classes)
        }
    }
}

