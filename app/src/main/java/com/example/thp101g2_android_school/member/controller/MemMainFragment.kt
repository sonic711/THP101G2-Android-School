package com.example.thp101g2_android_school.member.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thp101g2_android_school.member.viewModel.MemMainViewModel
import com.example.thp101g2_android_school.R

class MemMainFragment : Fragment() {

    companion object {
        fun newInstance() = MemMainFragment()
    }

    private lateinit var viewModel: MemMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mem_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MemMainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}