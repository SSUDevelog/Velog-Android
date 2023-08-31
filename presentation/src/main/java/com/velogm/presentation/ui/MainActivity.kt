package com.velogm.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.velogm.core_ui.base.BindingActivity
import com.velogm.presentation.R
import com.velogm.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        val navController =
            supportFragmentManager.findFragmentById(R.id.fc_main)?.findNavController()!!

        with(binding) {
            botNavMain.itemIconTintList = null
            navController?.let { NavController ->
                botNavMain.setupWithNavController(NavController)
            }

        }

        setBottomVisible(navController)
    }

    private fun setBottomVisible(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.botNavMain.visibility = if (destination.id in listOf(
                    R.id.navigation_home,
                    R.id.navigation_bookmark,
                    R.id.navigation_setting,
                    R.id.navigation_follow
                )
            ) View.VISIBLE else View.GONE

        }
    }
}