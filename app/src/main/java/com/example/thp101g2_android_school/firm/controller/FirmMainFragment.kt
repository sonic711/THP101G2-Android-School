package com.example.thp101g2_android_school.firm.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.thp101g2_android_school.FirmMainActivity
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.firm.viewmodel.FirmMainViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentFirmMainBinding
import com.example.thp101g2_android_school.firm.viewmodel.FirmProductsViewModel

class FirmMainFragment : Fragment() {
    private lateinit var binding: FragmentFirmMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as FirmMainActivity).supportActionBar?.hide()
        val viewModel : FirmProductsViewModel by viewModels()
        binding = FragmentFirmMainBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            // STEP01.設定recyclerView顯示格式，因需要監控LiveData，去補充FirmProductsViewModel
            recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            //STEP03.監控FirmProductsViewModel的資料
            viewModel?.firmProducts?.observe(viewLifecycleOwner){firmProducts ->
                if (recyclerView.adapter == null){
                    recyclerView.adapter = FirmProductAdapter(firmProducts)
                }else{
                    (recyclerView.adapter as FirmProductAdapter).updateFirmProduct(firmProducts)
                }
            }
        }
    }
}