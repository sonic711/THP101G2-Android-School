package com.example.thp101g2_android_school.point.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.databinding.FragmentPointBinding
import com.example.thp101g2_android_school.databinding.FragmentPointItemviewBinding
import com.example.thp101g2_android_school.point.model.Point
import com.example.thp101g2_android_school.point.viewmodel.PointViewInfoModel

class PointAdapter(private var reasons: List<Point>) :
    RecyclerView.Adapter<PointAdapter.ReasonViewHolder>() {

    fun updateReasons(reasons: List<Point>) {
        this.reasons = reasons
        notifyDataSetChanged()
    }

    class ReasonViewHolder(val itemViewBinding: FragmentPointItemviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)


    override fun getItemCount(): Int {
        return reasons.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReasonViewHolder {
        val itemViewBinding = FragmentPointItemviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = PointViewInfoModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return ReasonViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ReasonViewHolder, position: Int) {
        val reason = reasons[position]
        with(holder) {
            itemViewBinding.viewModel?.reason ?. value = reason
            val bundle = Bundle()
            bundle.putSerializable("reason", reason)

        }
    }

}