package com.example.thp101g2_android_school.calendar.controller

import android.app.DatePickerDialog

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation

import com.example.thp101g2_android_school.calendar.viewModel.SchedulesViewModel
import com.example.thp101g2_android_school.databinding.FragmentCalMainBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.util.*

class CalMainFragment : Fragment() {

    private lateinit var binding: FragmentCalMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: SchedulesViewModel by viewModels()
        binding = FragmentCalMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            // recyclerView
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.schedules?.observe(viewLifecycleOwner) { schedules ->
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = ScheduleAdapter(schedules)
                } else {
                    (recyclerView.adapter as ScheduleAdapter).updateSchedules(schedules)
                }
            }
            fbAdd.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.calAddFragment)
            }
        }
    }

    private fun makeDateString(year: Int, month: Int, day: Int): String {
        return "$year / $month / $day"
    }

}