package com.example.thp101g2_android_school.calendar.controller

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.calendar.viewModel.AddScheduleViewModel
import com.example.thp101g2_android_school.databinding.FragmentCalTagBinding

class CalTagFragment : Fragment() {
    private lateinit var binding: FragmentCalTagBinding
    private val viewModel: AddScheduleViewModel by activityViewModels()
    private val myTag = "TAG_${javaClass.simpleName}"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalTagBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as MainActivity).supportActionBar?.show()
        activity?.setTitle("選擇標籤")
        with(binding) {
            viewModel?.initialize()
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.tags?.observe(viewLifecycleOwner) { tags ->
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = TagAdapter(tags, object : OnItemClickListener {
                        override fun onItemClick(tagId: Int?, tagName: String) {
                            viewModel?.schedule?.value?.tagUserDefId = tagId
                            viewModel?.tagName?.value = tagName
                            Log.d(myTag, "scheduleTagName1: ${viewModel?.tagName?.value}")
                            requireActivity().supportFragmentManager.popBackStack()
                        }
                    })
                } else {
                    (recyclerView.adapter as TagAdapter).updateTags(tags)
                }
            }
            btEditTag.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.calEditTagFragment)
            }
        }
    }

}