package com.velogm.presentation.ui.follow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.velogm.core_ui.view.UiState
import com.velogm.domain.model.InputFollower
import com.velogm.domain.usecase.AddFollowerUseCase
import com.velogm.domain.usecase.GetInputFollowerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddFollowerViewModel @Inject constructor(
    private val getInputFollowerUseCase: GetInputFollowerUseCase,
    private val addFollowerUseCase: AddFollowerUseCase
) : ViewModel() {
    private val _getInputFollower = MutableStateFlow<UiState<InputFollower>>(UiState.Loading)
    val getInputFollower: StateFlow<UiState<InputFollower>> = _getInputFollower.asStateFlow()

    private val _eventData = MutableSharedFlow<UiState<String>>()
    val eventData: SharedFlow<UiState<String>> = _eventData.asSharedFlow()

    private var fcmToken: String = ""

    init {
        getFcmToken()
    }

    fun getInputFollower(followerName: String?) = viewModelScope.launch {
        getInputFollowerUseCase(followerName).collect {
            _getInputFollower.value = UiState.Success(it)
            Timber.d("Success")
        }
        _getInputFollower.value = UiState.Loading
    }

    fun addFollower(followerName: String) = viewModelScope.launch {
        addFollowerUseCase(followerName, fcmToken).collect {
            _eventData.emit(UiState.Success(it))
        }
    }

    private fun getFcmToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener {
            if (!it.isSuccessful) {
                Timber.d("fcm registration token failed")
                return@OnCompleteListener
            }
            fcmToken = it.result
            Timber.tag("fcmtoken").d(it.result.toString())
        })
    }
}