package com.velogm.presentation.ui.signin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.velogm.core_ui.view.UiState
import com.velogm.presentation.BuildConfig.CLIENT_ID
import com.velogm.presentation.databinding.ActivitySignInBinding
import com.velogm.presentation.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var googleSignResultLauncher: ActivityResultLauncher<Intent>
    private val viewModel by viewModels<SignViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        collectToken()
    }

    private fun collectToken() {
        viewModel.token.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    viewModel.saveToken(it.data)
                    viewModel.saveCheckLogin(true)
                    viewModel.saveWithdrawal(false)
                    navigateTo<MainActivity>()
                }

                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun initView() {
        getGoogleClient()
        googleSignResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            Timber.tag("test").d("${result.resultCode}")
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleSignInResult(task)
        }
    }

    private fun getGoogleClient() {
        binding.btnSignInGoogle.setOnClickListener {
            Timber.d("tasdasd")
            val googleSignInOption =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestServerAuthCode(CLIENT_ID)
                    .build()
            val mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOption)
            if (viewModel.getWithdrawal()) mGoogleSignInClient.revokeAccess()
            var signIntent: Intent = mGoogleSignInClient.signInIntent
            googleSignResultLauncher.launch(signIntent)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            var googleTokenAuth = account?.serverAuthCode.toString()
            Timber.tag("test").d(googleTokenAuth)
            if (!googleTokenAuth.isNullOrBlank()) {
                viewModel.getGoogleLogin(googleTokenAuth)
                viewModel.saveCheckLogin(true)
                viewModel.saveWithdrawal(false)
            }
        } catch (e: ApiException) {
            Timber.d("signInResult:failed Code = " + e.statusCode)
        }
    }

    private inline fun <reified T : Activity> navigateTo() {
        Intent(this@SignInActivity, T::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }
    }

}