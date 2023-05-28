package com.example.thp101g2_android_school.member.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thp101g2_android_school.member.viewModel.MemEditPasswordViewModel
import com.example.thp101g2_android_school.R

class MemEditPasswordFragment : Fragment() {

    companion object {
        fun newInstance() = MemEditPasswordFragment()
    }

    private lateinit var viewModel: MemEditPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mem_edit_password, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MemEditPasswordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}