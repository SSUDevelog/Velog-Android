package com.velogm.presentation.ui.webview

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.velogm.core_ui.base.BindingActivity
import com.velogm.core_ui.fragment.toast
import com.velogm.core_ui.view.UiState
import com.velogm.presentation.R
import com.velogm.presentation.databinding.ActivityWebviewBinding
import com.velogm.presentation.ui.follow.AddFollowerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber


@AndroidEntryPoint
class WebViewActivity :
    BindingActivity<ActivityWebviewBinding>(R.layout.activity_webview) {


    private val webViewViewModel by viewModels<WebviewViewModel>()
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // 뒤로 버튼 이벤트 처리
            Timber.tag("WebView").d("뒤로가기")
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webViewSetting()
        val url = intent.getStringExtra("url")
        val followName = intent.getStringExtra("followName")
        webViewViewModel.getFollower()
        this.onBackPressedDispatcher.addCallback(this, callback)
        collectFollowerNameList(followName?:"")
        if (url != null) {
            binding.webview.loadUrl(url)
        }
        binding.ivNavigateBack.setOnClickListener {
            finish()
        }
        binding.webviewFollowBtn.setOnClickListener {
            if (it.isSelected) webViewViewModel.deleteFollower(followName ?: "")
            else webViewViewModel.addFollower(followName ?: "")
        }
        webViewViewModel.eventData.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> {}

                is UiState.Success -> {
                    binding.webviewFollowBtn.isSelected = it.data
                }

                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun collectFollowerNameList(followName:String) {
        webViewViewModel.getFollowerNameList.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> {
                }

                is UiState.Success -> {
                   if ( it.data.name.contains(followName)) binding.webviewFollowBtn.isSelected = true
                }

                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun webViewSetting() {
        binding.webview.settings.apply {
            javaScriptEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
        }
    }

}