package com.example.thp101g2_android_school.manage.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.manage.viewmodel.ManageFirmViewModel

class ManageFirmFragment : Fragment() {

    companion object {
        fun newInstance() = ManageFirmFragment()
    }

    private lateinit var viewModel: ManageFirmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_manage_firm, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ManageFirmViewModel::class.java)
        // TODO: Use the ViewModel
    }

}