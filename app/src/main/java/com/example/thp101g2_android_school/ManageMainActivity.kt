package com.example.thp101g2_android_school

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.thp101g2_android_school.databinding.ActivityManageMainBinding
import com.example.thp101g2_android_school.databinding.FragmentManageHomeBinding
import com.example.thp101g2_android_school.databinding.FragmentManageMainBinding
import com.example.thp101g2_android_school.manage.controller.*


class ManageMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManageMainBinding
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView3) as NavHostFragment
        // 頁面切換可以整合BottomNavigationView功能
        NavigationUI.setupWithNavController(
            binding.bottomNavigationView,
            navHostFragment.navController
        )
        // 課程當登入後的首頁
        binding.bottomNavigationView.selectedItemId = R.id.manageHomeFragment
    }
}
