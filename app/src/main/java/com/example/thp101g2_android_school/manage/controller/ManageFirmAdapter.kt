package com.example.thp101g2_android_school.manage.controller

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.ClassItemViewBinding
import com.example.thp101g2_android_school.databinding.ManageFirmItemViewBinding
import com.example.thp101g2_android_school.manage.model.Classes
import com.example.thp101g2_android_school.manage.model.Firms
import com.example.thp101g2_android_school.manage.viewmodel.ManageClassViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageFirmViewModel


class ManageFirmAdapter(private var firms: List<Firms>) :
    RecyclerView.Adapter<ManageFirmAdapter.FirmViewHolder>() {


    @SuppressLint("NotifyDataSetChanged")
    fun updateFirms(firms: List<Firms>) {
        this.firms = firms
        notifyDataSetChanged()
    }

    class  FirmViewHolder(val itemViewBinding: ManageFirmItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return firms.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirmViewHolder {
        val itemViewBinding = ManageFirmItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = ManageFirmViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return FirmViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: FirmViewHolder, position: Int) {
        val firms = firms[position]
        with(holder) {
            // 將欲顯示的class物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.firmo?.value = firms
            val bundle = Bundle()
            bundle.putSerializable("firm", firms)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_manageFirmFragment_to_manageFirmDetailFragment, bundle)
            }
        }
    }
}