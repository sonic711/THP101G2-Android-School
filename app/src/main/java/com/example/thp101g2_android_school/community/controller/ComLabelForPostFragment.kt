package com.example.thp101g2_android_school.community.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.ActivityViewModel
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.MainViewModel
import com.example.thp101g2_android_school.community.viewmodel.ComLabelForPostViewModel
import com.example.thp101g2_android_school.databinding.FragmentComLabelForPostBinding

class ComLabelForPostFragment : Fragment() {
    private lateinit var binding: FragmentComLabelForPostBinding
    val activityViewModel: ActivityViewModel by activityViewModels()
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
                    recyclerView.adapter = ComLabelForPostAdapter(labels, activityViewModel)
                } else {
                    (recyclerView.adapter as ComLabelForPostAdapter).updateLabels(labels)
                }
            }
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                // 輸入的文字改變時呼叫
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel?.search(newText)
                    return true
                }

                // 點擊虛擬鍵盤上的提交鈕時呼叫
                override fun onQueryTextSubmit(text: String): Boolean {
                    return false
                }
            })
        }
    }
}