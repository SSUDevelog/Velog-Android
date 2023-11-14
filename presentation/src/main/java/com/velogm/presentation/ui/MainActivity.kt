package com.velogm.presentation.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.velogm.presentation.BuildConfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.velogm.core_ui.base.BindingActivity
import com.velogm.core_ui.context.longToast
import com.velogm.core_ui.view.UiState
import com.velogm.presentation.R
import com.velogm.presentation.databinding.ActivityMainBinding
import com.velogm.presentation.ui.signin.SignCheck
import com.velogm.presentation.ui.signin.SignInActivity
import com.velogm.presentation.ui.signin.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel by viewModels<SignInViewModel>()
    private lateinit var remoteConfig: FirebaseRemoteConfig
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setupLogoutState()
        setUpWithdrawalState()
        setRemoteConfig()
        fetchAppVersion()
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

    private fun setupLogoutState() {
        mainViewModel.logoutState.flowWithLifecycle(lifecycle).onEach { state ->
            when (state) {
                is SignCheck.Success -> {
                    navigateToSignInActivity()
                    longToast("로그인이 필요합니다.")
                }

                is SignCheck.Empty -> {
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun setUpWithdrawalState() {
        mainViewModel.withdrawal.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    navigateToSignInActivity()
                    longToast("회원 탈퇴 되었습니다.")
                }

                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun navigateToSignInActivity() {
        Intent(this@MainActivity, SignInActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(this)
            finish()
        }
    }

    private fun setBottomVisible(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.botNavMain.visibility = if (destination.id in listOf(
                    R.id.navigation_home,
//                    R.id.navigation_bookmark,
                    R.id.navigation_setting,
                    R.id.navigation_follow
                )
            ) View.VISIBLE else View.GONE

        }
    }

    private fun setRemoteConfig() {
        remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 1800
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
    }

    private fun fetchAppVersion() {
        var appVersion = remoteConfig.getString(REMOTE_KEY_APP_VERSION)

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    if (appVersion.equals(BuildConfig.VERSION_NAMES))
                        Timber.tag("remoteConfig").d("${BuildConfig.VERSION_NAMES}")
                    else {
                        AlertDialog.Builder(this)
                            .setTitle("Alert Version")
                            .setMessage("새로운 ${appVersion}이 출시했습니다.")
                            .show()
                    }
                } else {
                    Timber.tag("remoteConfig").d("fail")
                }
            }
    }

    companion object {
        private const val REMOTE_KEY_APP_VERSION = "app_version"
    }
}