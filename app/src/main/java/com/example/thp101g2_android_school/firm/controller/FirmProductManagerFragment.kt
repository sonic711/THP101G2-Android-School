package com.example.thp101g2_android_school.firm.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.FragmentFirmProductManagerBinding
import com.example.thp101g2_android_school.firm.viewmodel.FirmProductsEditViewModel

class FirmProductManagerFragment : Fragment() {
    private lateinit var binding : FragmentFirmProductManagerBinding
    private val viewModel: FirmProductsEditViewModel by viewModels { requireParentFragment().defaultViewModelProviderFactory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel : FirmProductsEditViewModel by viewModels()
        binding = FragmentFirmProductManagerBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            viewModel?.loadProductsManger()
            productManagerRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.productsManager?.observe(viewLifecycleOwner) { productsManager ->
                if (productManagerRecyclerView.adapter == null) {
                    productManagerRecyclerView.adapter = FirmProductManagerAdapter(productsManager)//最終顯示
                } else {
                    (productManagerRecyclerView.adapter as FirmProductManagerAdapter).updateProductsManager(
                        productsManager
                    )
                }

            }
            ibFirmProductOn.setOnClickListener{
                Navigation.findNavController(it).navigate(R.id.firmProductOnFragment)
            }
            btProductSum.setOnClickListener {
                viewModel?.allProduct()
            }

            btProductOn.setOnClickListener {
                // 把FirmProductsEditViewModel 的productsManager
                viewModel?.reload()
            }

            btProductOff.setOnClickListener {
                viewModel?.reloadOne()
            }

//            btProductOffed.setOnClickListener {
//                viewModel?.reloadZero()
//            }

        }
    }
}