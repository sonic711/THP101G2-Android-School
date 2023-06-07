package com.example.thp101g2_android_school.firm.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentFirmOrderDetailBinding
import com.example.thp101g2_android_school.databinding.FragmentFirmOrderManagerBinding
import com.example.thp101g2_android_school.firm.model.Order
import com.example.thp101g2_android_school.firm.viewmodel.FirmOrderViewModel
import com.example.thp101g2_android_school.firm.viewmodel.FirmOrdersViewModel
import com.google.android.material.color.MaterialColors.getColor

class FirmOrderManagerFragment : Fragment() {
    private lateinit var binding : FragmentFirmOrderManagerBinding
    private val viewModel: FirmOrdersViewModel by viewModels { requireParentFragment().defaultViewModelProviderFactory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel:FirmOrdersViewModel by viewModels()
        binding = FragmentFirmOrderManagerBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.loadOrders()
        with(binding){
            // STEP01.設定recyclerView顯示格式，因需要監控LiveData，去補充FirmOrdersViewModel
            recyclerViewOrder.layoutManager = LinearLayoutManager(requireContext())
            //STEP03.監控FirmOrdersViewModel的資料
            viewModel?.orders?.observe(viewLifecycleOwner) { orders ->
                // 如果Adapter尚未建立過，透過建構式建立FirmOrderAdapter
                if (recyclerViewOrder.adapter == null) {
                    recyclerViewOrder.adapter = FirmOrderAdapter(orders)
                } else {
                    // STEP08-1.如果已建立過，呼叫LessionAdapter.updateFriends更新資料
                    // adapter為null要建立新的adapter；之後只要呼叫updateFriends(friends)即可
                    (recyclerViewOrder.adapter as FirmOrderAdapter).updateOrders(orders)
                }
            }

            btAllOrder.setOnClickListener {
                viewModel?.orderAll()
            }

            btNotShip.setOnClickListener {
                viewModel?.orderNotShip()


            }

            btShipped.setOnClickListener{
                viewModel?.orderShipped()

            }
        }
    }
}