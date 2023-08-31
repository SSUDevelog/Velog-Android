package com.velogm.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velogm.core_ui.view.UiState
import com.velogm.domain.repository.AuthRepository
import com.velogm.domain.usecase.AccessTokenUseCase
import com.velogm.presentation.model.PostModelList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val tokenUseCase: AccessTokenUseCase,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _token= MutableStateFlow<UiState<String>>(UiState.Loading)
    val token: StateFlow<UiState<String>> = _token.asStateFlow()
    fun getGoogleLogin(code: String) = viewModelScope.launch {
        tokenUseCase(code).collect { token ->
            _token.value=UiState.Success(token.accessToken)
            Timber.d(authRepository.getAccessToken())
        }
    }
    fun getToken() = authRepository.getAccessToken()
    fun saveToken(token:String) = authRepository.saveAccessToken(token)
}