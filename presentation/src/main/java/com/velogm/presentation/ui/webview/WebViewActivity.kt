package com.velogm.presentation.ui.webview

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.velogm.core_ui.base.BindingActivity
import com.velogm.presentation.R
import com.velogm.presentation.databinding.ActivityWebviewBinding
import com.velogm.presentation.ui.follow.AddFollowerViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WebViewActivity :
    BindingActivity<ActivityWebviewBinding>(R.layout.activity_webview) {


    private val webViewViewModel by viewModels<WebviewViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webViewSetting()
        val url = intent.getStringExtra("url")
        if (url != null) {
            binding.webview.loadUrl(url)
        }
        //수정할게여
        binding.toolbarHomeSearch.setOnClickListener {
            onBackPressed()
        }
//        binding.webviewBookmark.setOnClickListener {
//            it.isSelected=true
//        }
//
//        binding.webviewFollowBtn.setOnClickListener {
//            webViewViewModel.addFollower()
//        }
    }

    private fun webViewSetting() {
        binding.webview.settings.apply {
            javaScriptEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
        }
    }

}