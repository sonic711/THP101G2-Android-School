package com.example.thp101g2_android_school.calendar.controller

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.calendar.viewModel.AddScheduleViewModel
import com.example.thp101g2_android_school.calendar.viewModel.CalAddViewModel
import com.example.thp101g2_android_school.databinding.FragmentCalAddBinding
import java.util.*

class CalAddFragment : Fragment() {
    private lateinit var binding: FragmentCalAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: AddScheduleViewModel by viewModels()
        binding = FragmentCalAddBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            tvDate.setOnClickListener {
                val calendar = Calendar.getInstance()
                val datePickerDialog = DatePickerDialog(requireContext(),{ _, year, month, day ->
                    // TODO
//                    viewModel?.schedule?.value?.date = makeDateString(year, month+1, day)
                },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH))

                datePickerDialog.show()
            }
            tvStartTime.setOnClickListener {
                val calendar = Calendar.getInstance()
                TimePickerDialog(
                    requireContext(),
                    { _, hour, minute ->
                        // TODO
//                        viewModel?.schedule?.value?.startTime = "$hour : $minute"
                    },
                    calendar.get(Calendar.HOUR),
                    calendar.get(Calendar.MINUTE),
                    true
                ).show()
            }
        }
    }

}