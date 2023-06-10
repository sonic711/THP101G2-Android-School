package com.example.thp101g2_android_school.calendar.controller

import androidx.lifecycle.ViewModelProvider
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
import com.example.thp101g2_android_school.calendar.viewModel.CalEditTagViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.calendar.viewModel.AddScheduleViewModel
import com.example.thp101g2_android_school.databinding.FragmentCalEditTagBinding
import com.example.thp101g2_android_school.databinding.FragmentCalTagBinding

class CalEditTagFragment : Fragment() {
    private lateinit var binding: FragmentCalEditTagBinding
    private val viewModel: AddScheduleViewModel by activityViewModels()
    private val myTag = "TAG_${javaClass.simpleName}"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalEditTagBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as MainActivity).supportActionBar?.show()
        activity?.setTitle("編輯標籤")
        with(binding) {
            viewModel?.initialize()
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            // FIXME
            viewModel?.tags?.observe(viewLifecycleOwner) { tags ->
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = EditTagAdapter(tags)

                } else {
                    (recyclerView.adapter as EditTagAdapter).updateEditTags(tags)
                }
            }
            btSubmit.setOnClickListener {
                val list = (recyclerView.adapter as EditTagAdapter).getEditTags()
                for (editTag in list) {
                    viewModel?.editTag(editTag)
                    Log.d(myTag, "EditTag: $editTag")
                }

                Navigation.findNavController(it).popBackStack()
            }




        }
    }


}