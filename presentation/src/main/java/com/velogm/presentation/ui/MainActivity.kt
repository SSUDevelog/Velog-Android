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

        remoteConfig = Firebase.remoteConfig

        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0
        }

        remoteConfig.setConfigSettingsAsync(configSettings)

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
                    R.id.navigation_bookmark,
                    R.id.navigation_setting,
                    R.id.navigation_follow
                )
            ) View.VISIBLE else View.GONE

        }
    }
    private fun fetchAppVersion() {
        // val appVersion = remoteConfig[REMOTE_KEY_APP_VERSION].asString()
        var appVersion="0.0.0"
        Timber.tag("remote config1").d("$appVersion")
        appVersion=remoteConfig.getString(REMOTE_KEY_APP_VERSION)
        Timber.tag("remote config2").d("$appVersion")

        AlertDialog.Builder(this)
            .setTitle("Remote Config")
            .setMessage("App version :: $appVersion")
            .show()

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Timber.tag("remoteConfig").d("success")
                    // fetch and activate 성공
                } else {
                    Timber.tag("remoteConfig").d("fail")
                    // fetch and activate 실패
                }
            }
    }

    companion object {
        private const val REMOTE_KEY_APP_VERSION = "app_version"
    }
}