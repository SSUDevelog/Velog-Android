package com.velogm.presentation.ui.webview

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.velogm.core_ui.base.BindingActivity
import com.velogm.presentation.R
import com.velogm.presentation.databinding.ActivityWebviewBinding
import com.velogm.presentation.ui.follow.AddFollowerViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class WebViewActivity :
    BindingActivity<ActivityWebviewBinding>(R.layout.activity_webview) {


    private val webViewViewModel by viewModels<WebviewViewModel>()
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // 뒤로 버튼 이벤트 처리
            Timber.tag("WebView").d("뒤로가기")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webViewSetting()
        val url = intent.getStringExtra("url")
        val followName = intent.getStringExtra("followName")
        this.onBackPressedDispatcher.addCallback(this, callback)
        Timber.tag("Post").d(followName)
        if (url != null) {
            binding.webview.loadUrl(url)
        }
        //수정할게여
        binding.toolbarHomeSearch.setOnClickListener {
            callback
        }
//        binding.webviewBookmark.setOnClickListener {
//            it.isSelected=true
//        }
//
        binding.webviewFollowBtn.setOnClickListener {
            webViewViewModel.addFollower(followName?:"")
        }
    }

    private fun webViewSetting() {
        binding.webview.settings.apply {
            javaScriptEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
        }
    }

}