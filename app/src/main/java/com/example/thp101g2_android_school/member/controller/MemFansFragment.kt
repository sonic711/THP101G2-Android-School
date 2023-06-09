package com.example.thp101g2_android_school.member.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.databinding.FragmentMemFansBinding
import com.example.thp101g2_android_school.member.viewModel.MemberViewModel

class MemFansFragment : Fragment() {
    private lateinit var binding: FragmentMemFansBinding
    private val viewModel: MemberViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMemFansBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.fans?.observe(viewLifecycleOwner) { fans ->
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = FanAdapter(fans)
                } else {
                    (recyclerView.adapter as FanAdapter).updateFans(fans)
                }
            }
//            viewModel!!.loadFans()
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel?.searchFan(newText)
                    return true
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }
            })
        }
    }


}