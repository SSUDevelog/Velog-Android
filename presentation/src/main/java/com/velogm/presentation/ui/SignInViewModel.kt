package com.velogm.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velogm.core_ui.view.UiState
import com.velogm.domain.model.Token
import com.velogm.domain.usecase.AccessTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val tokenUseCase: AccessTokenUseCase
):ViewModel(){

    private val _accessToken = MutableStateFlow<UiState<Token>>(UiState.Loading)
    val accessToken: StateFlow<UiState<Token>> = _accessToken.asStateFlow()

    fun getGoogleLogin(code:String)=viewModelScope.launch {
        tokenUseCase(code).collect{token ->
            _accessToken.value = UiState.Success(token)
            Timber.d("Success")
        }
    }
}