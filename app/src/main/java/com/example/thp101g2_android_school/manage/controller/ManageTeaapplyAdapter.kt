package com.example.thp101g2_android_school.manage.controller

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.ManageComItemViewBinding
import com.example.thp101g2_android_school.databinding.ManageTeaapplyItemViewBinding
import com.example.thp101g2_android_school.manage.model.Comms
import com.example.thp101g2_android_school.manage.model.ManageComReportBean
import com.example.thp101g2_android_school.manage.model.TeaApplyBean
import com.example.thp101g2_android_school.manage.viewmodel.ManageComViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageCommsViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageMemberViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageTeaApplyViewModel


class ManageTeaApplyAdapter(private var teas: List<TeaApplyBean>) :
    RecyclerView.Adapter<ManageTeaApplyAdapter.TeaViewHolder>() {
    @SuppressLint("NotifyDataSetChanged")
    fun updateTeaApplies(teas: List<TeaApplyBean>) {
        this.teas = teas
        notifyDataSetChanged()
    }

    class TeaViewHolder(val itemViewBinding: ManageTeaapplyItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeaViewHolder {
        val itemViewBinding = ManageTeaapplyItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        itemViewBinding.viewModel = ManageTeaApplyViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return TeaViewHolder(itemViewBinding)
    }
    override fun getItemCount(): Int {
        return teas.size
    }

        override fun onBindViewHolder(holder: TeaViewHolder, position: Int) {
        val theteas = teas[position]
        with(holder) {
            // 將欲顯示的class物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.teaapply?.value = theteas
            val bundle = Bundle()
            //有發生key不一樣
            bundle.putSerializable("teaapply", theteas)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_manageTeaApplyFragment_to_manageTeaApplyDetailFragment, bundle)
            }
        }
    }
}