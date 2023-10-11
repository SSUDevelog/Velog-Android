package com.velogm.presentation.ui.webview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.velogm.core_ui.view.UiState
import com.velogm.domain.model.InputFollower
import com.velogm.domain.usecase.AddFollowerUseCase
import com.velogm.domain.usecase.DeleteFollowerUseCase
import com.velogm.domain.usecase.GetInputFollowerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WebviewViewModel @Inject constructor(
    private val addFollowerUseCase: AddFollowerUseCase,
    private val deleteFollowerUseCase: DeleteFollowerUseCase
) : ViewModel() {

    private val _eventData = MutableSharedFlow<UiState<Boolean>>()
    val eventData: SharedFlow<UiState<Boolean>> = _eventData.asSharedFlow()

    private var fcmToken: String = ""

    init {
        getFcmToken()
    }

    fun addFollower(followerName: String) = viewModelScope.launch {
        addFollowerUseCase(followerName, fcmToken).collect {
            _eventData.emit(UiState.Success(true))
        }
    }

    fun deleteFollower(followerName: String) = viewModelScope.launch {
        deleteFollowerUseCase(followerName).collect {
            _eventData.emit(UiState.Success(false))
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