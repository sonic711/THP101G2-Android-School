package com.example.thp101g2_android_school.calendar.controller

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.thp101g2_android_school.calendar.viewModel.CalMainViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.calendar.viewModel.SchedulesViewModel
import com.example.thp101g2_android_school.databinding.FragmentCalMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
//      獲取裝置螢幕的高度，等等加載 bottom sheet 時會使用
        val peekHeight = requireActivity().resources.displayMetrics.heightPixels
//      加載 cal_bottom_sheet_add
        val bsdAddSchedule = BottomSheetDialog(
            requireContext(), R.style.BottomSheetDialogTheme)
        val bsvAddSchedule = LayoutInflater.from(requireContext()).inflate(
            R.layout.cal_bottom_sheet_add,
            null
        )
        bsdAddSchedule.setContentView(bsvAddSchedule)
        val behavior = bsdAddSchedule.behavior
        behavior.peekHeight = peekHeight

//      加載 cal_bottom_sheet_tag
        val bsdTag = BottomSheetDialog(
            requireContext(), R.style.BottomSheetDialogTheme)
        val bsvTag = LayoutInflater.from(requireContext()).inflate(
            R.layout.cal_bottom_sheet_tag,
            null
        )
        bsdTag.setContentView(bsvTag)
        val tagBehavior = bsdTag.behavior
        tagBehavior.peekHeight = peekHeight

//      加載 cal_bottom_sheet_tag_edit
        val bsdEditTag = BottomSheetDialog(
            requireContext(), R.style.BottomSheetDialogTheme)
        val bsvEditTag = LayoutInflater.from(requireContext()).inflate(
            R.layout.cal_bottom_sheet_edit_tag,
            null
        )
        bsdEditTag.setContentView(bsvEditTag)
        val editTagBehavior = bsdEditTag.behavior
        editTagBehavior.peekHeight = peekHeight

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
                bsdAddSchedule.show()

                bsvAddSchedule.findViewById<View>(R.id.ivCancel).setOnClickListener{
                    bsdAddSchedule.dismiss()
                }
                bsvAddSchedule.findViewById<View>(R.id.btAdd).setOnClickListener {
                    bsdAddSchedule.dismiss()
                }
                bsvAddSchedule.findViewById<View>(R.id.etTag).setOnClickListener {
                    bsdTag.show()
                    bsvTag.findViewById<View>(R.id.ivEdit).setOnClickListener {
                        bsdEditTag.show()
                    }
                }
                bsvAddSchedule.findViewById<View>(R.id.etDate).setOnClickListener {
                    val calendar = Calendar.getInstance()
                    val datePickerDialog = DatePickerDialog(requireContext(),{ _, year, month, day ->
                        viewModel?.schedule?.value?.date = makeDateString(year, month+1, day)

                    },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH))

                    datePickerDialog.show()

                }

                bsvAddSchedule.findViewById<View>(R.id.etStartTime).setOnClickListener {
                    val calendar = Calendar.getInstance()
                    TimePickerDialog(
                        requireContext(),
                        { _, hour, minute ->
                            viewModel?.schedule?.value?.startTime = "$hour : $minute"
                        },
                        calendar.get(Calendar.HOUR),
                        calendar.get(Calendar.MINUTE),
                        true
                    ).show()
                }
                bsvAddSchedule.findViewById<View>(R.id.etEndTime).setOnClickListener {
                    val calendar = Calendar.getInstance()
                    TimePickerDialog(
                        requireContext(),
                        { _, hour, minute ->
                            viewModel?.schedule?.value?.endTime = "$hour : $minute"
                        },
                        calendar.get(Calendar.HOUR),
                        calendar.get(Calendar.MINUTE),
                        true
                    ).show()
                }
            }
        }
    }

    private fun makeDateString(year: Int, month: Int, day: Int): String {
        return "$year / $month / $day"
    }


}