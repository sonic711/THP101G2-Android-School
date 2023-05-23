package com.example.thp101g2_android_school.course.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.course.viewmodel.CouClassroomViewModel


class CouClassroomFragment : Fragment() {

    companion object {
        fun newInstance() = CouClassroomFragment()
    }

    private lateinit var viewModel: CouClassroomViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cou_classroom, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CouClassroomViewModel::class.java)
        // TODO: Use the ViewModel
    }

}