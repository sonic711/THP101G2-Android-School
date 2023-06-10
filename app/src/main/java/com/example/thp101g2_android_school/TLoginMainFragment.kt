package com.example.thp101g2_android_school

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.thp101g2_android_school.databinding.FragmentTLoginMainBinding


class TLoginMainFragment : Fragment() {

    private lateinit var binding: FragmentTLoginMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTLoginMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val data = (requireActivity() as LoginMainActivity).data
        with(binding) {
            // 會員登入成功
            button.setOnClickListener {
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.putExtra("data", data)
                startActivity(intent)
            }
            // 廠商登入成功
            button2.setOnClickListener {
                val intent = Intent(requireContext(), FirmMainActivity::class.java)
                intent.putExtra("type", "user")
                startActivity(intent)
            }
            // 後台登入成功
            button3.setOnClickListener {
                val intent = Intent(requireContext(), ManageMainActivity::class.java)
                intent.putExtra("type", "user")
                startActivity(intent)
            }
        }
    }

}