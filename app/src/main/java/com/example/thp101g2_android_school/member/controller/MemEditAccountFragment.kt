package com.example.thp101g2_android_school.member.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thp101g2_android_school.member.viewModel.MemEditAccountViewModel
import com.example.thp101g2_android_school.R

class MemEditAccountFragment : Fragment() {

    companion object {
        fun newInstance() = MemEditAccountFragment()
    }

    private lateinit var viewModel: MemEditAccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mem_edit_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MemEditAccountViewModel::class.java)
        // TODO: Use the ViewModel
    }

}