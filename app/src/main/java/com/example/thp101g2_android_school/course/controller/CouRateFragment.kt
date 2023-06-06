package com.example.thp101g2_android_school.course.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.course.viewmodel.CouRateViewModel
import com.example.thp101g2_android_school.databinding.FragmentCouRateBinding
//之後需要寫把資料傳送到後端
class CouRateFragment : Fragment() {

    private lateinit var binding: FragmentCouRateBinding
    private val viewModel: CouRateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCouRateBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->  }
            btnSubmit.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_couRateFragment_to_couCommentFragment)
            }
        }
    }
}