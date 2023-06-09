package com.example.thp101g2_android_school.member.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentMemMainBinding
import com.example.thp101g2_android_school.member.viewModel.MemberViewModel

class MemMainFragment : Fragment() {
    private lateinit var binding: FragmentMemMainBinding
    private val viewModel: MemberViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMemMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as MainActivity).supportActionBar?.show()
        activity?.setTitle("個人主頁")
        with(binding) {
            viewModel?.initialize()
            btEdit.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.memEditProfileFragment)
            }
            tvFollowingNum.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.memFollowersFragment)
            }
            tvFansNum.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.memFansFragment)
            }
        }

    }

}