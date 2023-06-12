package com.example.thp101g2_android_school.member.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.member.viewModel.MemOthersHomeViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.community.controller.ComMainFragment
import com.example.thp101g2_android_school.community.controller.PostAdapter
import com.example.thp101g2_android_school.databinding.FragmentMemOthersHomeBinding
import com.example.thp101g2_android_school.member.model.Follower
import com.example.thp101g2_android_school.member.model.Member
import com.example.thp101g2_android_school.member.viewModel.MemPostViewModel
import com.google.gson.JsonObject

class MemOthersHomeFragment : Fragment() {
    private lateinit var binding: FragmentMemOthersHomeBinding
    private val viewModel: MemOthersHomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val postViewModel: MemPostViewModel by viewModels()
        binding = FragmentMemOthersHomeBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.postViewModel = postViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupMenu()
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

            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            postViewModel?.loadPosts(viewModel?.member?.value?.memberNo!!)
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

    private fun setupMenu() {

        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.more_menu, menu)
            }

            // FIXME 先導覽至會員主頁
            override fun onMenuItemSelected(item: MenuItem): Boolean {
                AlertDialog.Builder(requireContext())
                    .setMessage("確定要封鎖此用戶嗎？")
                    .setPositiveButton("確定") { dialog, which ->
                        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/member/block"
                        val respBody =
                            requestTask<JsonObject>(url, "POST", binding.viewModel?.member?.value)
                        if (respBody?.get("successful")?.asBoolean == true) {
                            Toast.makeText(requireContext(), "已封鎖此會員", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireContext(), "封鎖失敗", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .setNegativeButton("取消", null)
                    .show()
                return true
            }

            override fun onPrepareMenu(menu: Menu) {
                // Handle for example visibility of menu items
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

}