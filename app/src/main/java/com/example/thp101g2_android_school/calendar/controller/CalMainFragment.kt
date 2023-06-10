package com.example.thp101g2_android_school.calendar.controller

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation

import com.example.thp101g2_android_school.databinding.FragmentCalMainBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.calendar.viewModel.MemScheduleViewModel
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class CalMainFragment : Fragment() {
    private lateinit var binding: FragmentCalMainBinding
    private val myTag = "TAG_${javaClass.simpleName}"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: MemScheduleViewModel by viewModels()
        binding = FragmentCalMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.show()
        activity?.setTitle("日曆")
        with(binding) {
            viewModel?.initialize()
            // recyclerView
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.schedules?.observe(viewLifecycleOwner) { schedules ->
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = ScheduleAdapter(schedules)
                } else {
                    (recyclerView.adapter as ScheduleAdapter).updateSchedules(schedules)
                }
            }
            tvCalDate.setOnClickListener {
                val calendar = Calendar.getInstance()
                val datePickerDialog = DatePickerDialog(requireContext(),{ _, year, month, day ->
                    calendar.set(year, month, day)
                    val formattedDate = String.format("%04d/%02d/%02d", year, month + 1, day)

                    viewModel?.date?.value = formattedDate
                    Log.d(myTag, "NewScheduleDate: ${viewModel?.date?.value}")
                    viewModel?.loadSchedules(viewModel?.date?.value)
                    Log.d(myTag, "NewSchedules: ${viewModel?.schedules?.value}")
                },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH))

                datePickerDialog.show()

            }
            fbAdd.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.calAddFragment)
            }

        }
    }


}