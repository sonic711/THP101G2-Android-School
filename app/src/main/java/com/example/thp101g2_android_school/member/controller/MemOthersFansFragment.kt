package com.example.thp101g2_android_school.member.controller

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.databinding.FragmentMemOthersFansBinding
import com.example.thp101g2_android_school.member.controller.OthersFanAdapter
import com.example.thp101g2_android_school.member.viewModel.MemOthersHomeViewModel

class MemOthersFansFragment : Fragment() {
    private lateinit var binding: FragmentMemOthersFansBinding
    private val viewModel: MemOthersHomeViewModel by activityViewModels()
    private val my_tag = "TAG_${javaClass.simpleName}"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMemOthersFansBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            Log.d(my_tag, "FansSize: ${viewModel?.fans?.value?.size}")
            viewModel?.fans?.observe(viewLifecycleOwner) { fans ->

                if (recyclerView.adapter == null) {
                    recyclerView.adapter = OthersFanAdapter(fans)
                } else {
                    (recyclerView.adapter as OthersFanAdapter).updateFans(fans)
                }
            }
            viewModel!!.loadFans()
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