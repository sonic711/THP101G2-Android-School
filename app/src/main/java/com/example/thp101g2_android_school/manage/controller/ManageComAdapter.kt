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
import com.example.thp101g2_android_school.manage.model.Comms
import com.example.thp101g2_android_school.manage.model.ManageComReportBean
import com.example.thp101g2_android_school.manage.viewmodel.ManageComViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageCommsViewModel


class ManageComAdapter(private var comms: List<ManageComReportBean>) :
    RecyclerView.Adapter<ManageComAdapter.ComViewHolder>() {
    fun updateComments(comms: List<ManageComReportBean>) {
        this.comms = comms
        notifyDataSetChanged()
    }

    class ComViewHolder(val itemViewBinding: ManageComItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComViewHolder {
        val itemViewBinding = ManageComItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        itemViewBinding.viewModel = ManageComViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return ComViewHolder(itemViewBinding)
    }
    override fun getItemCount(): Int {
        return comms.size
    }

    override fun onBindViewHolder(holder: ManageComAdapter.ComViewHolder, position: Int) {
        val comm = comms[position]
        with(holder) {
            itemViewBinding.viewModel?.commo?.value = comm
            val bundle = Bundle()
            bundle.putSerializable("comms" , comm)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_manageComFragment_to_manageComDetailFragment, bundle)
            }
        }
    }
}