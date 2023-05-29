package com.example.thp101g2_android_school.community.controller

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.byteArrayToBitmap
import com.example.thp101g2_android_school.community.model.Post
import com.example.thp101g2_android_school.community.viewmodel.PostDetailViewModel
import com.example.thp101g2_android_school.databinding.FragmentPostDetailBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class PostDetailFragment : Fragment() {
    private lateinit var binding: FragmentPostDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        val viewModel: PostDetailViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            arguments?.let { bundle ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    bundle.getSerializable("post", Post::class.java)?.let {
                        viewModel?.post?.value = it
                        // TODO 這邊理論上不用手動呼叫吧 想改一下 註解拿掉可以正常跑
//                        viewModel?.loadData()
                        if (it.profilePhoto == null) {
                            memberImg.setImageResource(R.drawable.com_user)
                        } else {
                            memberImg.setImageBitmap(byteArrayToBitmap(it.profilePhoto!!))
                        }
                    }
                } else {
                    bundle.getSerializable("post")?.let {
                        viewModel?.post?.value = it as Post
                    }
                }
            }
            // TODO 把 likeList傳到ReplyAdapter裏面 繼續寫 ...
            val likeList = viewModel?.replyLikes

            replyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.replys?.observe(viewLifecycleOwner) { replys ->
                if (replyRecyclerView.adapter == null) {
                    replyRecyclerView.adapter = ReplyAdapter(replys)
                } else {
                    (replyRecyclerView.adapter as ReplyAdapter).updateReply(replys)
                }
            }
            // bottomSheet部分：為了點擊可以直接觸發事件
            // 需要多做一些設定 ...
            val bottomSheetBehavior = BottomSheetBehavior.from(included.bottomSheet)

            included.tvContent.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    if (event?.action == MotionEvent.ACTION_DOWN) {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                        included.tvContent.requestFocus()
                    }
                    return false
                }

            })
            // 回覆文章的部分
            included.saveButton.setOnClickListener {
                viewModel?.replyContent?.value = included.tvContent.text.toString()
                viewModel?.sendReply()
                included.tvContent.text = null
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                // 當前畫面應該要刷新直接顯示，畫面滾動到最下方
                // 這個是指定滾到哪一筆資料，先註解
//                viewModel?.replyList?.size?.let { it -> replyRecyclerView.scrollToPosition(it - 1 ) }
                nestedScrollView.fullScroll(View.FOCUS_DOWN)


            }
        }
    }
}