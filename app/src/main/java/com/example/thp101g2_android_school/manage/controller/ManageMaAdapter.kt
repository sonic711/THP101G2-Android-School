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
import com.example.thp101g2_android_school.databinding.ManageMaItemViewBinding
import com.example.thp101g2_android_school.manage.model.Classes
import com.example.thp101g2_android_school.manage.model.ManageAccountBean
import com.example.thp101g2_android_school.manage.model.Mas
import com.example.thp101g2_android_school.manage.viewmodel.ManageClassViewModel
import com.example.thp101g2_android_school.manage.viewmodel.ManageMaViewModel


class ManageMaAdapter(private var mas: List<ManageAccountBean>) :
    RecyclerView.Adapter<ManageMaAdapter.MaViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateMas(mas: List<ManageAccountBean>) {
        this.mas = mas
        notifyDataSetChanged()
    }

    class MaViewHolder(val itemViewBinding: ManageMaItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaViewHolder {
        val itemViewBinding = ManageMaItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = ManageMaViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return MaViewHolder(itemViewBinding)
    }

    override fun getItemCount(): Int {
        return mas.size
    }

    override fun onBindViewHolder(holder: MaViewHolder, position: Int) {
        val mas = mas[position]
        with(holder) {
            // 將欲顯示的class物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.mao?.value = mas
            val bundle = Bundle()
            bundle.putSerializable("ma", mas)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_manageMaFragment_to_manageMaDetailFragment, bundle)
            }
        }
    }
}