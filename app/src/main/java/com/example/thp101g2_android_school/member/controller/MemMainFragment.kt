package com.example.thp101g2_android_school.member.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.community.controller.ComMainFragment
import com.example.thp101g2_android_school.community.controller.PostAdapter
import com.example.thp101g2_android_school.community.viewmodel.ComAllPostViewModel
import com.example.thp101g2_android_school.community.viewmodel.ComPostViewModel
import com.example.thp101g2_android_school.databinding.FragmentMemMainBinding
import com.example.thp101g2_android_school.member.viewModel.MemPostViewModel
import com.example.thp101g2_android_school.member.viewModel.MemberViewModel

class MemMainFragment : Fragment() {
    private lateinit var binding: FragmentMemMainBinding
    private val viewModel: MemberViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val postViewModel: MemPostViewModel by viewModels()
        binding = FragmentMemMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.postViewModel = postViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as MainActivity).supportActionBar?.show()
        activity?.setTitle("個人主頁")
        with(binding) {
            viewModel?.initialize()
            postViewModel?.loadPosts(viewModel?.member?.value?.memberNo!!)
            btEdit.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.memEditProfileFragment)
            }
            tvFollowingNum.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.memFollowersFragment)
            }
            tvFansNum.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.memFansFragment)
            }
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            postViewModel?.posts?.observe(viewLifecycleOwner) { posts ->
                // 如果Adapter尚未建立過，透過建構式建立LessionAdapter
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = PostAdapter(posts)
                    if (requireParentFragment() is ComMainFragment) {
                        (requireParentFragment() as ComMainFragment).binding.loadingPanel.visibility =
                            View.GONE
                    }
                } else {
                    // 搜尋更新
                    (recyclerView.adapter as PostAdapter).updatePosts(posts)
                }
            }

        }
    }
}