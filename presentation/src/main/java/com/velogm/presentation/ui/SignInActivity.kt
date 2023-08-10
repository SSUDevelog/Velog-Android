package com.velogm.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task
import com.velogm.presentation.databinding.ActivitySignInBinding
import timber.log.Timber

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var googleSignResultLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        getGoogleClient()

        googleSignResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleSignInResult(task)
        }
    }

    private fun getGoogleClient() {
        binding.btnSignInGoogle.setOnClickListener {
            val googleSignInOption =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestScopes(Scope("https://www.googleapis.com/auth/pubsub"))
                    .requestServerAuthCode("26369999400-177svh4fk05jhsvocjaeittgh1dq4eb1.apps.googleusercontent.com")// string 파일에 저장해둔 client id 를 이용해 server authcode를 요청한다.
                    .requestEmail() // 이메일도 요청할 수 있다.
                    .build()
            val mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOption)

            var signIntent: Intent = mGoogleSignInClient.getSignInIntent()
            googleSignResultLauncher.launch(signIntent)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val email = account?.email.toString()
            var googletoken = account?.idToken.toString()
            var googleTokenAuth = account?.serverAuthCode.toString()
            Timber.d(email)
            Timber.d(googletoken)
            Timber.d(googleTokenAuth)

        } catch (e: ApiException) {
            Timber.d("signInResult:failed Code = " + e.statusCode)
        }
    }

}