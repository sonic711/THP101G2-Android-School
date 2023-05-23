package com.example.thp101g2_android_school.course.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.course.viewmodel.CouMyCourseViewModel
import com.example.thp101g2_android_school.databinding.FragmentCouMyCourseBinding


class CouMyCourseFragment : Fragment() {


    private lateinit var binding: FragmentCouMyCourseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).supportActionBar?.show()
        val viewModel: CouMyCourseViewModel by viewModels()
        binding = FragmentCouMyCourseBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.mycourses?.observe(viewLifecycleOwner){mycourses ->
                if (recyclerView.adapter == null){
                    recyclerView.adapter = CouMyCourseAdapter(mycourses)
                }else{
                    (recyclerView.adapter as CouMyCourseAdapter).updateMycourses(mycourses)
                }
            }
        }
    }

}