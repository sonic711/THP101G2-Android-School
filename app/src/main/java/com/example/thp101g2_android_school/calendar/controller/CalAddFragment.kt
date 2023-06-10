package com.example.thp101g2_android_school.calendar.controller

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.calendar.viewModel.AddScheduleViewModel
import com.example.thp101g2_android_school.calendar.viewModel.CalAddViewModel
import com.example.thp101g2_android_school.databinding.FragmentCalAddBinding
import com.example.thp101g2_android_school.member.viewModel.MemberViewModel
import java.sql.Date
import java.sql.Time
import java.util.*

// FIXME 新增是用schedule，viewModel根綁定的物件要記得改
class CalAddFragment : Fragment() {
    private lateinit var binding: FragmentCalAddBinding
    private val myTag = "TAG_${javaClass.simpleName}"
    private val viewModel: AddScheduleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalAddBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.show()
        activity?.setTitle("新增日程")
        with(binding) {
            btAdd.setOnClickListener {
                Log.d(myTag, "tagId: ${viewModel?.schedule?.value?.tagUserDefId}")
                viewModel?.addSchedule()
                Navigation.findNavController(it).popBackStack()
            }
            etTag.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.calTagFragment)
            }
            etDate.setOnClickListener {
                val calendar = Calendar.getInstance()
                val datePickerDialog = DatePickerDialog(requireContext(),{ _, year, month, day ->
                    calendar.set(year, month, day)
                    viewModel?.schedule?.value?.date = Date(calendar.timeInMillis)
                    viewModel?.date?.postValue("$year - ${month+1} - $day")
                    Log.d(myTag, "date: ${viewModel?.schedule?.value?.date}")
                },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH))

                datePickerDialog.show()
            }
            etStartTime.setOnClickListener {
                val calendar = Calendar.getInstance()
                TimePickerDialog(
                    requireContext(), { _, hourOfDay, minute ->
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        calendar.set(Calendar.MINUTE, minute)
                        val time = Time.valueOf("$hourOfDay:$minute:00")
                        viewModel?.schedule?.value?.startTime = time
                        viewModel?.startTime?.postValue("$hourOfDay : $minute")
                        Log.d(myTag, "startTime: ${viewModel?.schedule?.value?.startTime}")
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true
                ).show()
            }
            etEndTime.setOnClickListener {
                val calendar = Calendar.getInstance()
                TimePickerDialog(
                    requireContext(), { _, hourOfDay, minute ->
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        calendar.set(Calendar.MINUTE, minute)
                        val time = Time.valueOf("$hourOfDay:$minute:00")
                        viewModel?.schedule?.value?.endTime = time
                        viewModel?.endTime?.postValue("$hourOfDay : $minute")
                        Log.d(myTag, "endTime: ${viewModel?.schedule?.value?.endTime}")
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true
                ).show()
            }
            viewModel?.schedule?.value?.repeatPattern = 1
            viewModel?.schedule?.value?.reminder = 1

            val onItemSelectedListenerRP = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> { viewModel?.schedule?.value?.repeatPattern = 1 }
                        1 -> { viewModel?.schedule?.value?.repeatPattern = 2 }
                        2 -> { viewModel?.schedule?.value?.repeatPattern = 3 }
                        3 -> { viewModel?.schedule?.value?.repeatPattern = 4 }
                    }
                    Log.d(myTag, "repeat: ${viewModel?.schedule?.value?.repeatPattern}")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    viewModel?.schedule?.value?.repeatPattern = 1
                }
            }

            val onItemSelectedListenerRM = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> { viewModel?.schedule?.value?.reminder = 1 }
                        1 -> { viewModel?.schedule?.value?.reminder = 2 }
                        2 -> { viewModel?.schedule?.value?.reminder = 3 }
                        3 -> { viewModel?.schedule?.value?.reminder = 4 }
                    }
                    Log.d(myTag, "remind: ${viewModel?.schedule?.value?.reminder}")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    viewModel?.schedule?.value?.reminder = 1
                    Log.d(myTag, "nothing remind: ${viewModel?.schedule?.value?.reminder}")
                }
            }
            spRepeat.setSelection(0, true)
            spRemind.setSelection(0, true)
            spRepeat.onItemSelectedListener = onItemSelectedListenerRP
            spRemind.onItemSelectedListener = onItemSelectedListenerRM
        }

    }

}