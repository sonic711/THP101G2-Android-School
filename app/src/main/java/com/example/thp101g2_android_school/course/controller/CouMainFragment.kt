package com.example.thp101g2_android_school.course.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thp101g2_android_school.course.viewmodel.CouMainViewModel
import com.example.thp101g2_android_school.R

class CouMainFragment : Fragment() {

    companion object {
        fun newInstance() = CouMainFragment()
    }

    private lateinit var viewModel: CouMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cou_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CouMainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}