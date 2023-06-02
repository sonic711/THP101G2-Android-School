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
import com.example.thp101g2_android_school.manage.viewmodel.ManageComViewModel


class ManageComAdapter(private var comms: List<Comms>) :
    RecyclerView.Adapter<ManageComAdapter.ComViewHolder>() {
    @SuppressLint("NotifyDataSetChanged")
    fun updateComments(comms: List<Comms>) {
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

        override fun onBindViewHolder(holder: ComViewHolder, position: Int) {
        val comments = comms[position]
        with(holder) {
            // 將欲顯示的class物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.commo?.value = comments
            val bundle = Bundle()
            //有發生key不一樣
            bundle.putSerializable("comm", comments)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_manageComFragment_to_manageComDetailFragment, bundle)
            }
        }
    }
}