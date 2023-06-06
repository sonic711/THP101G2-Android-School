package com.example.thp101g2_android_school.calendar.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.calendar.viewModel.CalTagViewModel

class CalTagFragment : Fragment() {

    companion object {
        fun newInstance() = CalTagFragment()
    }

    private lateinit var viewModel: CalTagViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cal_tag, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CalTagViewModel::class.java)
        // TODO: Use the ViewModel
    }

}