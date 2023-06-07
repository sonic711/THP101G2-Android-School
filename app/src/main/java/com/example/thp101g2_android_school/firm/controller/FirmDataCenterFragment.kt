package com.example.thp101g2_android_school.firm.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentFirmDataCenterBinding
import com.example.thp101g2_android_school.firm.viewmodel.FirmDatasCenterViewModel

class FirmDataCenterFragment : Fragment() {
    private lateinit var binding : FragmentFirmDataCenterBinding
    private val viewModel: FirmDatasCenterViewModel by viewModels { requireParentFragment().defaultViewModelProviderFactory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel : FirmDatasCenterViewModel by viewModels()
        binding = FragmentFirmDataCenterBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.loadDatas()
        with(binding){
            // STEP01.設定recyclerView顯示格式，因需要監控LiveData，去補充DataCenterViewModel
            recyclerViewDataCenter.layoutManager=LinearLayoutManager(requireContext())
            //STEP03.監控FirmOrdersViewModel的資料
            viewModel?.datas?.observe(viewLifecycleOwner){datas->
                // 如果Adapter尚未建立過，透過建構式建立FirmOrderAdapter
                if (recyclerViewDataCenter.adapter == null){
                    recyclerViewDataCenter.adapter = FirmDataCenterAdapter(datas)
                }else{
                // STEP08-1.如果已建立過，呼叫FirmDataCenterAdapter.updateData更新資料
                // adapter為null要建立新的adapter；之後只要呼叫updateFriends(friends)即可
                    (recyclerViewDataCenter.adapter as FirmDataCenterAdapter).updateDatas(datas)
                }
            }
        }
    }

}