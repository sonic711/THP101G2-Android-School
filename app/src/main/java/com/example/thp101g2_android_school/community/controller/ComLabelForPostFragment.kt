package com.example.thp101g2_android_school.community.controller

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.ActivityViewModel
import com.example.thp101g2_android_school.community.model.Label
import com.example.thp101g2_android_school.community.viewmodel.ComLabelForPostViewModel
import com.example.thp101g2_android_school.databinding.FragmentComLabelForPostBinding
import com.google.gson.JsonObject

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
            // 監控ActivityViewModel的標籤集合，只要有改變，就指派給這個viewModel
            // 並更改View的顯示方式
            activityViewModel?.labelList?.observe(viewLifecycleOwner) {
                viewModel?.newLabels?.value = activityViewModel.newLabels?.toList()
                when (viewModel?.newLabels?.value?.size) {
                    0 -> {
                        binding.tvLabelName1.visibility = View.GONE
                        binding.tvLabelName2.visibility = View.GONE
                        binding.tvLabelName3.visibility = View.GONE
                    }

                    1 -> {
                        binding.tvLabelName1.text = viewModel?.newLabels?.value?.get(0)?.comLabelName
                        binding.tvLabelName1.visibility = View.VISIBLE
                        binding.tvLabelName2.visibility = View.GONE
                        binding.tvLabelName3.visibility = View.GONE
                    }

                    2 -> {
                        binding.tvLabelName1.text = viewModel?.newLabels?.value?.get(0)?.comLabelName
                        binding.tvLabelName2.text = viewModel?.newLabels?.value?.get(1)?.comLabelName
                        binding.tvLabelName1.visibility = View.VISIBLE
                        binding.tvLabelName2.visibility = View.VISIBLE
                        binding.tvLabelName3.visibility = View.GONE
                    }

                    3 -> {
                        binding.tvLabelName1.text = viewModel?.newLabels?.value?.get(0)?.comLabelName
                        binding.tvLabelName2.text = viewModel?.newLabels?.value?.get(1)?.comLabelName
                        binding.tvLabelName3.text = viewModel?.newLabels?.value?.get(2)?.comLabelName
                        binding.tvLabelName1.visibility = View.VISIBLE
                        binding.tvLabelName2.visibility = View.VISIBLE
                        binding.tvLabelName3.visibility = View.VISIBLE
                    }
                }
            }
            // 監控如果搜尋後的標籤為0個
            // 就顯示新的標籤
            viewModel?.labels?.observe(viewLifecycleOwner) {
                if (viewModel?.labels?.value?.size == 0) {
                    binding.tvNewLabel.visibility = View.VISIBLE
                    binding.tvNewLabel.text = searchView.query
                } else binding.tvNewLabel.visibility = View.GONE
            }
            // 點擊新的標籤後，把該標籤做成Label物件，並新增到activityViewModel的集合中
            // 把searchView清空，並回到原始狀態
            tvNewLabel.setOnClickListener {
                val newLabel = Label()
                newLabel.comLabelName = searchView.query.toString().trim()
                activityViewModel.getLabelToInsert(newLabel)
                binding.tvNewLabel.visibility = View.GONE
                searchView.setQuery("", false);
                searchView.isIconified = true;
            }
        }
    }
}