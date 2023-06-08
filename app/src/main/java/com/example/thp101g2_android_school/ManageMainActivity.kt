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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_manage_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.manageHomeFragment -> navController.navigate(R.id.manageHomeFragment)
                R.id.manageClassesFragment -> navController.navigate(R.id.manageClassesFragment)
                R.id.manageComFragment -> navController.navigate(R.id.manageComFragment)
                R.id.manageFirmFragment -> navController.navigate(R.id.manageFirmFragment)
                R.id.manageMaFragment -> navController.navigate(R.id.manageMaFragment)
                R.id.manageMemberFragment -> navController.navigate(R.id.manageMemberFragment)
                R.id.manageReportFragment -> navController.navigate(R.id.manageReportFragment)
            }
            true
        }
    }
}

