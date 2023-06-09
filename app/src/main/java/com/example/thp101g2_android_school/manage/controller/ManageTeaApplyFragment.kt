package com.example.thp101g2_android_school.manage.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.ManageMainActivity
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentManageTeaApplyBinding
import com.example.thp101g2_android_school.manage.model.ManageComReportBean
import com.example.thp101g2_android_school.manage.model.TeaApplyBean
import com.example.thp101g2_android_school.manage.viewmodel.ManageTeaApplyViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageTeaApplysViewModel

class ManageTeaApplyFragment : Fragment() {
    private lateinit var binding: FragmentManageTeaApplyBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ManageTeaApplyAdapter
    private val viewModel: ManageTeaApplysViewModel by viewModels()
    private var teaList: List<TeaApplyBean> = emptyList()

    companion object {
        fun newInstance() = ManageTeaApplyFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageTeaApplyBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as ManageMainActivity).supportActionBar?.hide()

        with(binding) {
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            adapter = ManageTeaApplyAdapter(teaList)
            binding.viewModel?.teaapplies?.observe(viewLifecycleOwner) { teas ->
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = ManageTeaApplyAdapter(teas)
                } else {
                    this@ManageTeaApplyFragment.adapter.updateTeaApplies(teas)
                }
            }
            binding.Back.setOnClickListener {
                Navigation.findNavController(requireView()).navigateUp()
            }
        }
    }
}