package com.velogm.presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.velogm.domain.SharedPreferenceToken
import com.velogm.presentation.BuildConfig.CLIENT_ID
import com.velogm.presentation.databinding.ActivitySignInBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var googleSignResultLauncher: ActivityResultLauncher<Intent>
    private val viewModel by viewModels<SignInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 임시 여기 로직 다시 짜야됨 스플래쉬로 옮길거임 그리고
         if (viewModel.getToken().isNullOrBlank()){
             Timber.tag("token").d("Not token")
             initView()
         } else{
             Timber.tag("token").d(viewModel.getToken())
             navigateTo<MainActivity>()
         }
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
            val googleSignInOption =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestServerAuthCode(CLIENT_ID)
                    .build()
            val mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOption)

            var signIntent: Intent = mGoogleSignInClient.signInIntent
            googleSignResultLauncher.launch(signIntent)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val email = account?.email.toString()
            var googletoken = account?.idToken.toString()
            var googleTokenAuth = account?.serverAuthCode.toString()
            if (!googleTokenAuth.isNullOrBlank()){
                viewModel.getGoogleLogin(googleTokenAuth)
            }
            Timber.d(email)
            Timber.d(googletoken)
            Timber.d(googleTokenAuth)
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