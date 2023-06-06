package com.example.thp101g2_android_school.course.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.course.viewmodel.CouCommentViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.course.viewmodel.CouMyCourseViewModel
import com.example.thp101g2_android_school.databinding.FragmentCouCommentBinding
import com.example.thp101g2_android_school.databinding.FragmentCouMyCourseBinding

class CouCommentFragment : Fragment() {

    private lateinit var binding: FragmentCouCommentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: CouCommentViewModel by viewModels()
        binding = FragmentCouCommentBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.comments?.observe(viewLifecycleOwner){comments ->
                if (recyclerView.adapter == null){
                    recyclerView.adapter = CouCommentAdapter(comments)
                }else{
                    (recyclerView.adapter as CouCommentAdapter).updateComments(comments)
                }

            }
        }
    }
}