package com.example.thp101g2_android_school.community.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thp101g2_android_school.community.viewmodel.ComFollowViewModel
import com.example.thp101g2_android_school.R

class ComFollowFragment : Fragment() {

    companion object {
        fun newInstance() = ComFollowFragment()
    }

    private lateinit var viewModel: ComFollowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_com_follow, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ComFollowViewModel::class.java)
        // TODO: Use the ViewModel
    }

}