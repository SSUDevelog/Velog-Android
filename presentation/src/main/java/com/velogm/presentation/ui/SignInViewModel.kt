package com.velogm.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velogm.domain.repository.AuthRepository
import com.velogm.domain.usecase.AccessTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val tokenUseCase: AccessTokenUseCase,
    private val authRepository: AuthRepository
) : ViewModel() {
    fun getGoogleLogin(code: String) = viewModelScope.launch {
        tokenUseCase(code).collect { token ->
            authRepository.saveAccessToken(token.accessToken)
            Timber.d(authRepository.getAccessToken())
        }
    }
}