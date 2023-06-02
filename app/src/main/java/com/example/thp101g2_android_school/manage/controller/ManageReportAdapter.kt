package com.example.thp101g2_android_school.manage.controller

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.ManageMemberItemViewBinding
import com.example.thp101g2_android_school.databinding.ManageReportItemViewBinding
import com.example.thp101g2_android_school.manage.model.Reports
import com.example.thp101g2_android_school.manage.viewmodel.ManageReportViewModel


class ManageReportAdapter(private var reports: List<Reports>) :
    RecyclerView.Adapter<ManageReportAdapter.ReportViewHolder>() {

    fun updateReports(reports: List<Reports>) {
        this.reports = reports
        notifyDataSetChanged()
    }

    class ReportViewHolder(val itemViewBinding: ManageReportItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val itemViewBinding = ManageReportItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        itemViewBinding.viewModel = ManageReportViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return ReportViewHolder(itemViewBinding)
    }

    override fun getItemCount(): Int {
        return reports.size
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val report = reports[position]
        with(holder) {
            itemViewBinding.viewModel?.reporto?.value = report
            val bundle = Bundle()
            //有發生key不一樣
            bundle.putSerializable("Report", report)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_manageReportFragment_to_manageReportDetailFragment, bundle)
            }
        }
    }
}

