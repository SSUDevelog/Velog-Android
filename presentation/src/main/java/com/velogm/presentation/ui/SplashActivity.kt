package com.velogm.presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.velogm.presentation.ui.signin.SignInActivity
import com.velogm.presentation.ui.signin.SignViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel by viewModels<SignViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (viewModel.getCheckLogin()) {
            Timber.tag("token").d(viewModel.getToken())
            navigateTo<MainActivity>()
        } else {
            navigateTo<SignInActivity>()
        }
    }

    private inline fun <reified T : Activity> navigateTo() {
        Intent(this@SplashActivity, T::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }
    }
}