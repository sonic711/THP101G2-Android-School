package com.example.thp101g2_android_school.community.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.community.viewmodel.ComLabelForPostViewModel
import com.example.thp101g2_android_school.databinding.FragmentComLabelForPostBinding

class ComLabelForPostFragment : Fragment() {
    private lateinit var binding: FragmentComLabelForPostBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentComLabelForPostBinding.inflate(inflater, container, false)
        val viewModel: ComLabelForPostViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.labels?.observe(viewLifecycleOwner) { labels ->
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = ComLabelForPostAdapter(labels)
                }
            }
        }
    }
}