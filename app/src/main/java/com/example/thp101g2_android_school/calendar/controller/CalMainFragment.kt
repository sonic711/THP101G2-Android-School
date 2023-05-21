package com.example.thp101g2_android_school.calendar.controller

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
import com.example.thp101g2_android_school.databinding.FragmentCalMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class CalMainFragment : Fragment() {

    private lateinit var binding: FragmentCalMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: CalMainViewModel by viewModels()
        binding = FragmentCalMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            fbAdd.setOnClickListener {
                Toast.makeText(requireContext(), "fbAdd Clicked", Toast.LENGTH_SHORT).show()
                val bsdAddSchedule = BottomSheetDialog(
                    requireContext(), R.style.BottomSheetDialogTheme)
                val bsvAddSchedule = LayoutInflater.from(requireContext()).inflate(
                    R.layout.cal_bottom_sheet_add,
                    null
                )
                bsdAddSchedule.setContentView(bsvAddSchedule)
                val behavior = bsdAddSchedule.behavior
                // 獲取裝置螢幕的高度
                val peekHeight = requireActivity().resources.displayMetrics.heightPixels
                behavior.peekHeight = peekHeight
                bsdAddSchedule.show()
                bsvAddSchedule.findViewById<View>(R.id.btAdd).setOnClickListener {
                    Toast.makeText(requireContext(), "btAdd Clicked", Toast.LENGTH_SHORT).show()
                    bsdAddSchedule.dismiss()
                }
            }
        }
    }


}