package com.velogm.presentation.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velogm.core_ui.view.UiState
import com.velogm.domain.NetworkErrorHandling
import com.velogm.domain.collectOutResult
import com.velogm.domain.repository.AuthRepository
import com.velogm.domain.usecase.AccessTokenUseCase
import com.velogm.domain.usecase.GetTagUseCase
import com.velogm.presentation.mapper.toTagModelEntity
import com.velogm.presentation.model.TagModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val getTagUseCase: GetTagUseCase,
    private val tokenUseCase: AccessTokenUseCase,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _tagListData = MutableStateFlow<UiState<List<TagModel>>>(UiState.Loading)
    val tagListData: StateFlow<UiState<List<TagModel>>> = _tagListData.asStateFlow()

    private val _token = MutableStateFlow<UiState<String>>(UiState.Loading)
    val token: StateFlow<UiState<String>> = _token.asStateFlow()

    private val _logoutState = MutableStateFlow<SignCheck<Boolean>>(SignCheck.Empty)
    val logoutState: StateFlow<SignCheck<Boolean>> = _logoutState.asStateFlow()

    private val _withdrawal = MutableStateFlow<UiState<Boolean>>(UiState.Loading)
    val withdrawal: StateFlow<UiState<Boolean>> = _withdrawal.asStateFlow()

    init {
        getTag()
    }

    fun getTag() = viewModelScope.launch {
        getTagUseCase().collectOutResult(
            handleSuccess = {
                val tagList = it.data.toTagModelEntity()
                _tagListData.value = UiState.Success(tagList)
                Timber.d(it.toString())
            },
            handleFail = {
                val errorHandling = when (it.error?.message) {
                    "401" -> NetworkErrorHandling.Unauthorized
                    "500" -> NetworkErrorHandling.ServerError
                    else -> NetworkErrorHandling.OtherError
                }
                when (errorHandling) {
                    is NetworkErrorHandling.Unauthorized -> postLogout()
                    is NetworkErrorHandling.ServerError -> Timber.d("서버에러")
                    is NetworkErrorHandling.OtherError -> Timber.d("다른에러")
                }
            }
        )
    }
    fun getGoogleLogin(code: String) = viewModelScope.launch {
        tokenUseCase(code).collect { token ->
            _token.value = UiState.Success(token.accessToken)
            Timber.d(authRepository.getAccessToken())
        }
    }

    fun postLogout() {
        _logoutState.value = SignCheck.Success(true)
        saveToken("")
        saveCheckLogin(false)
    }

    fun postWithdrawal() = viewModelScope.launch {
        _withdrawal.value = UiState.Success(true)
        saveToken("")
        saveCheckLogin(false)
        saveWithdrawal(true)
    }

    fun getToken() = authRepository.getAccessToken()
    fun saveToken(token: String) = authRepository.saveAccessToken(token)
    fun getCheckLogin(): Boolean = authRepository.checkLogin()
    fun saveCheckLogin(check: Boolean) = authRepository.saveCheckLogin(check)
    fun getWithdrawal(): Boolean = authRepository.getWithdrawal()
    fun saveWithdrawal(check: Boolean) = authRepository.saveWithdrawal(check)
}