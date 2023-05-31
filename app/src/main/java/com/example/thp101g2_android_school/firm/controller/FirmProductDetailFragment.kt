package com.example.thp101g2_android_school.firm.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentFirmProductDetailBinding
import com.example.thp101g2_android_school.firm.model.FirmProduct
import com.example.thp101g2_android_school.firm.viewmodel.FirmMainViewModel

class FirmProductDetailFragment : Fragment() {
    private lateinit var binding : FragmentFirmProductDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel : FirmMainViewModel by viewModels()
        binding = FragmentFirmProductDetailBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // STEP07.接收參數並顯示
        // arguments
        arguments?.let { bundle ->
            bundle.getSerializable("firmProduct")?.let {
                binding.viewModel?.firmProduct?.value = it as FirmProduct
            }
        }
        with(binding){

            ibFirmHomeBackTo.setOnClickListener{
                Navigation.findNavController(it).popBackStack()
            }

        }
    }
}