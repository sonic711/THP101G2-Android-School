package com.example.thp101g2_android_school.course.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.course.viewmodel.CouRatingViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentCouRateBinding
import com.example.thp101g2_android_school.databinding.FragmentCouRatingBinding

class CouRatingFragment : Fragment() {


    private lateinit var binding: FragmentCouRatingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).supportActionBar?.hide()
        val viewModel: CouRatingViewModel by viewModels()
        binding = FragmentCouRatingBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){

            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.ratings?.observe(viewLifecycleOwner){ ratings ->
                if (recyclerView.adapter == null){
                    recyclerView.adapter = CouRatingAdapter(ratings)
                }else{
                    (recyclerView.adapter as CouRatingAdapter).updateRatings(ratings)
                }
            }
            lifecycleOwner = viewLifecycleOwner
        }
    }

}