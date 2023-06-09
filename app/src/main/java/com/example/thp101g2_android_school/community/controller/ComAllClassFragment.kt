package com.example.thp101g2_android_school.community.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.ActivityViewModel
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.community.viewmodel.ComClassViewModel
import com.example.thp101g2_android_school.databinding.FragmentComAllClassBinding

class ComAllClassFragment : Fragment() {

    private lateinit var binding: FragmentComAllClassBinding
    private val activityViewModel: ActivityViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as MainActivity).supportActionBar?.hide()
        val viewModel: ComClassViewModel by viewModels()
        binding = FragmentComAllClassBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            activityViewModel.memberObj.value?.let { viewModel?.getFollowClass(it) }
            parentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            val followList = viewModel?.followList
            viewModel?.parents?.observe(viewLifecycleOwner) { parents ->
                parentRecyclerView.adapter = followList?.let { ComClassParentAdapter(parents, it) }
            }

        }
    }

}