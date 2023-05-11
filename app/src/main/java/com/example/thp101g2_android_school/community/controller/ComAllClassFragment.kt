package com.example.thp101g2_android_school.community.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thp101g2_android_school.community.viewmodel.ComAllClassViewModel
import com.example.thp101g2_android_school.R

class ComAllClassFragment : Fragment() {

    companion object {
        fun newInstance() = ComAllClassFragment()
    }

    private lateinit var viewModel: ComAllClassViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_com_all_class, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ComAllClassViewModel::class.java)
        // TODO: Use the ViewModel
    }

}