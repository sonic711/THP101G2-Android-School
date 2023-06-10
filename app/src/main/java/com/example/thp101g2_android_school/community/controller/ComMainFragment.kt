package com.example.thp101g2_android_school.community.controller

import android.app.AlertDialog
import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.thp101g2_android_school.MainActivity
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.community.model.Notification
import com.example.thp101g2_android_school.community.model.Page
import com.example.thp101g2_android_school.community.model.Post
import com.example.thp101g2_android_school.community.viewmodel.ComNotificationViewModel
import com.example.thp101g2_android_school.databinding.FragmentComMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth

class ComMainFragment : Fragment() {
    lateinit var binding: FragmentComMainBinding
    private lateinit var auth: FirebaseAuth
    val myTag = "TAG_${javaClass.simpleName}"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: ComNotificationViewModel by viewModels()
        binding = FragmentComMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        handleViews()
        with(binding) {
            floatingBtn.setOnClickListener {
                val navController = Navigation.findNavController(it)
                /**
                 *  TODO 這邊可能會有問題，先記錄一下
                 *  回到上一頁會失效，因為主頁被pop掉了，解決辦法可能是之後要倒回ComMainFragment
                 */
                navController.popBackStack()
                if(auth.currentUser == null) navController.navigate(R.id.comPostAuthFragment)
                else navController.navigate(R.id.comPostFragment)
            }
            val activity = requireActivity() as MainActivity
            // 從MainActivity取得在背景時收到的廣播資料（目前設計成通知的id）
            var messageid = activity.message
            if (!messageid.isNullOrBlank()) {
                // 從資料庫找對應的通知訊息
                viewModel?.getNotifi(messageid.toInt())
                AlertDialog.Builder(requireContext())
                    .setMessage("${viewModel?.notification?.value?.notificationContent}")
                    .setPositiveButton("確定") { _, _ ->
                        // TODO 導去對應文章

                    }
                    .setCancelable(false) // 點旁邊可不可以取消
                    .show()
                // 清除通知變數
                activity.message = ""
            }
        }
    }

    private fun handleViews() {
        val pages = listOf(
            Page("追蹤", ComFollowFragment()),
            Page("全部文章", ComAllPostFragment()),
            Page("全部分類", ComAllClassFragment()),
        )

        with(binding) {

            viewPager2.adapter = MyFragmentStateAdapter(this@ComMainFragment, pages)
            // 預設頁面為全部文章
            viewPager2.setCurrentItem(1, false)
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                // 設定tab標題文字
                tab.text = pages[position].title
            }.attach()
        }
    }

    class MyFragmentStateAdapter(activity: ComMainFragment, private var pages: List<Page>) :
        FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            return pages.size
        }

        override fun createFragment(position: Int): Fragment {
            return pages[position].fragment
        }
    }
}