package com.example.thp101g2_android_school.course.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.thp101g2_android_school.course.viewmodel.CouUploadChapterViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentCouUploadChapterBinding

class CouUploadChapterFragment : Fragment() {

    private lateinit var binding: FragmentCouUploadChapterBinding
    private lateinit var viewModel: CouUploadChapterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCouUploadChapterBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(CouUploadChapterViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {

            val onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel?.text?.value = parent?.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    viewModel?.text?.value = getString(R.string.textNothingSelected)
                }
            }
            spinnerChapter.setSelection(0, true)
            spinnerChapter.onItemSelectedListener = onItemSelectedListener
        }
    }
}