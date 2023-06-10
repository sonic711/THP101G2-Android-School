package com.example.thp101g2_android_school

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import com.example.thp101g2_android_school.app.getCurrentMemberId
import com.example.thp101g2_android_school.app.saveMemberId
import com.example.thp101g2_android_school.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val activityViewModel: ActivityViewModel by viewModels()
    val myTag = "TAG_${javaClass.simpleName}"
    var memberId = ""
    var message = ""
    private lateinit var navHostFragment: NavHostFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        binding.activityViewModel = activityViewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)
        setUpActionBar()
        setupMenu()
        initDrawer()
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // 頁面切換可以整合BottomNavigationView功能
        NavigationUI.setupWithNavController(
            binding.bottomNavigationView,
            navHostFragment.navController
        )
        // 頁面切換可以整合抽屜選單功能
        NavigationUI.setupWithNavController(
            binding.navigationView,
            navHostFragment.navController
        )

        // 課程當登入後的首頁
        binding.bottomNavigationView.selectedItemId = R.id.couMainFragment
        val messageReceiver = MessageReceiver()

        registerReceiver(messageReceiver)
        val message = intent.extras?.getString("data")
        if (message != null) {
            this@MainActivity.message = message
        }
        // 登入後把該會員token存到資料庫
        ActivityViewModel().getTokenSendServer()
        // 把會員Id存到偏好設定檔，方便取用
        saveMemberId(this, activityViewModel?.memberObj?.value?.memberNo.toString())
        // 從偏好設定檔取得目前登入會員Id
        memberId = getCurrentMemberId(this)!!
        activityViewModel.member.memberId = memberId

    }

    override fun onStart() {
        super.onStart()
        // API 33開始需要加上requestPermissionLauncher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private var requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            Log.d("myTag", "granted: $granted")
        }

    private fun setUpActionBar() {
        // 設定ActionBar標題列的左上角可以加上按鈕
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    // 初始化抽屜選單功能
    private fun initDrawer() {
        // 建立ActionBarDrawerToggle監聽器，監聽抽屜開關的狀態，但不用實作，呼叫建構式就好
        with(binding) {
            val actionBarDrawerToggle =
                ActionBarDrawerToggle(
                    this@MainActivity,
                    drawerLayout,
                    R.string.txtOpen,
                    R.string.txtClose
                )
            drawerLayout.addDrawerListener(actionBarDrawerToggle)
            // 將左上角按鈕動畫與抽屜選單開關同步化
            actionBarDrawerToggle.syncState()
        }
    }

    // 點擊標題列上的按鈕會呼叫此方法
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 如果抽屜開啟，將之關閉；反之則開啟
        when (item.itemId) {
            android.R.id.home -> {
                with(binding) {
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START)
                        // GravityCompat.START 表示抽屜的方向，要跟XML設定的一樣，不然會閃退
                    } else {
                        drawerLayout.openDrawer(GravityCompat.START)
                    }
                }
                return true
                // 表示事件已處理，以防事件繼續向下傳遞
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupMenu() {
        addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.action_menu, menu)
            }

            // FIXME 先導覽至會員主頁 (在 action_menu 更改)
            override fun onMenuItemSelected(item: MenuItem): Boolean {
                return item.onNavDestinationSelected(navHostFragment.navController)
            }

            override fun onPrepareMenu(menu: Menu) {
                // Handle for example visibility of menu items
            }
        }, this, Lifecycle.State.RESUMED)
    }

    private inner class MessageReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val message = intent.extras?.getString("message")
            if (message != null) {
                this@MainActivity.message = message
            }
            if (!message.isNullOrBlank()) {
                // 從資料庫找對應的通知訊息
                activityViewModel.getNotifi(message.toInt())
                AlertDialog.Builder(this@MainActivity)
                    .setMessage("${activityViewModel?.notification?.value?.notificationContent}")
                    .setPositiveButton("確定") { _, _ ->
                        // TODO 導去對應文章

                    }
                    .setCancelable(false) // 點旁邊可不可以取消
                    .show()
                // 清除通知變數
                this@MainActivity.message = ""
            }
        }
    }

    // 註冊廣播接收器攔截"action_news"的廣播
    private fun registerReceiver(messageReceiver: MessageReceiver) {
        val intentFilter = IntentFilter("action_message")
        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver, intentFilter)
    }
}