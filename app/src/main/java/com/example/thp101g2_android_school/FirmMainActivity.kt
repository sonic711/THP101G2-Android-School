package com.example.thp101g2_android_school

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.thp101g2_android_school.databinding.ActivityFirmMainBinding

class FirmMainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFirmMainBinding
    private val firmActivityViewModel: FirmActivityViewModel by viewModels()
    var firmNo = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirmMainBinding.inflate(LayoutInflater.from(this))
        binding.viewModel = firmActivityViewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_firm_fragment) as NavHostFragment
        // 頁面切換可以整合BottomNavigationView功能
        NavigationUI.setupWithNavController(
            binding.bottomNavigationFirmView,
            navHostFragment.navController
        )
        binding.bottomNavigationFirmView.selectedItemId = R.id.firmMainFragment

        firmNo = firmActivityViewModel.firm.value?.firmNo.toString()
    }
}