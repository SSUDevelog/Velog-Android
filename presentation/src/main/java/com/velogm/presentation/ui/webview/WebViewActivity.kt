package com.velogm.presentation.ui.webview

import android.os.Bundle
import android.widget.Toast
import com.velogm.core_ui.base.BindingActivity
import com.velogm.presentation.R
import com.velogm.presentation.databinding.ActivityWebviewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WebViewActivity : BindingActivity<ActivityWebviewBinding>(R.layout.activity_webview) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webViewSetting()
        val url = intent.getStringExtra("url")
        if (url != null) {
            binding.webview.loadUrl(url)
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