package com.example.thp101g2_android_school.member.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.thp101g2_android_school.member.viewModel.MemOthersHomeViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.FragmentMemOthersHomeBinding
import com.example.thp101g2_android_school.member.model.Follower
import com.example.thp101g2_android_school.member.model.Member

class MemOthersHomeFragment : Fragment() {
    private lateinit var binding: FragmentMemOthersHomeBinding
    private val viewModel: MemOthersHomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMemOthersHomeBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            arguments?.let {
                viewModel?.member?.value = it.getSerializable("member") as Member
                val member = viewModel?.member?.value
                viewModel?.initialize()
                checkFollowBack(member)



                btFollow.setOnClickListener {
                    if (btFollow.isChecked) {
                        viewModel?.addFollower(member)
                    } else {
                        viewModel?.unfollow(member?.memberNo)
                    }
                }

            }
            tvFansNum.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.memOthersFansFragment)
            }

            tvFollowingNum.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.memOthersFollowersFragment)
            }

        }

    }

    private fun checkFollowBack(member: Member?) {
        val list = binding.viewModel?.searchFollowBackList()
        list?.let { list ->
            for (follower in list) {
                // follower.memberFollowing 是 追蹤該會員的粉絲的會員編號
                if (follower.memberFollowing == member?.memberNo) {
                    binding.btFollow.isChecked = true
                    break
                }
            }
        }
    }

}