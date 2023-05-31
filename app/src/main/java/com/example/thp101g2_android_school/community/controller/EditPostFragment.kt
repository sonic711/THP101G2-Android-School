package com.example.thp101g2_android_school.community.controller

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.byteArrayToBitmap
import com.example.thp101g2_android_school.community.model.Post
import com.example.thp101g2_android_school.community.viewmodel.EditPostViewModel
import com.example.thp101g2_android_school.databinding.FragmentEditPostBinding

class EditPostFragment : Fragment() {
    private lateinit var binding: FragmentEditPostBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: EditPostViewModel by viewModels()
        binding = FragmentEditPostBinding.inflate(inflater, container, false)
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
                        println(it)
                        if (it.profilePhoto != null) {
                            val img = byteArrayToBitmap(it.profilePhoto!!)
                            imageView.setImageBitmap(img)
                        } else {
                            imageView.setBackgroundResource(R.drawable.com_user)
                        }
                    }
                } else {
                    bundle.getSerializable("post")?.let {
                        viewModel?.post?.value = it as Post
                    }
                }
            }

        }
    }
}