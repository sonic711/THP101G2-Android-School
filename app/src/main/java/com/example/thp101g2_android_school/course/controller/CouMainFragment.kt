package com.example.thp101g2_android_school.course.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.course.viewmodel.CouMainViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentCouMainBinding
import com.example.thp101g2_android_school.databinding.FragmentMainBinding

class CouMainFragment : Fragment() {
    private lateinit var binding: FragmentCouMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        (requireActivity() as MainActivity).supportActionBar?.hide()
        val viewModel: CouMainViewModel by viewModels()
        binding = FragmentCouMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.courses?.observe(viewLifecycleOwner) { courses ->
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = CouMainAdapter(courses)
                } else {
                    (recyclerView.adapter as CouMainAdapter).updateCourses(courses)
                }
            }
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel?.search(newText)
                    return true
                }
                override fun onQueryTextSubmit(text: String): Boolean {
                    return false
                }
            })
        }
    }
}
