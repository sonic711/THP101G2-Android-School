package com.example.thp101g2_android_school.course.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.course.model.Chapter
import com.example.thp101g2_android_school.course.model.Comment
import com.example.thp101g2_android_school.course.viewmodel.CouRateViewModel
import com.example.thp101g2_android_school.databinding.FragmentCouRateBinding
import com.google.gson.JsonObject

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
        arguments?.let { bundle ->
            bundle.getSerializable("roomcourse")?.let {
                binding.viewModel?.rates?.value = it as Comment
            }
        }
        with(binding) {


            btnSubmit.setOnClickListener {

//                val rateValue = viewModel?.rates?.value?.rating?.toInt()

//                if (rateValue != null) {
//                    if (rateValue < 0 || rateValue > 5) {
//                        return@setOnClickListener
//                        Toast.makeText(requireContext(), "請輸入有效的評分（1-5）", Toast.LENGTH_SHORT).show()
//                    }else {
                        val test = Comment(
                            rating = viewModel?.point?.value!!,
                            comment = viewModel?.comment?.value!!
                        )
                        //串會員要把資料加進去

                        requestTask<JsonObject>(
                            "http://10.0.2.2:8080/THP101G2-WebServer-School/comment/",
                            method = "POST",
                            reqBody = test
                        )

                        Navigation.findNavController(it)
                            .navigate(R.id.action_couRateFragment_to_couRatingFragment)
                    }
                }
            }
        }
//    }
//}
