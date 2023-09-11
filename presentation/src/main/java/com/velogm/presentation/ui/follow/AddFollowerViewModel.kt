package com.velogm.presentation.ui.follow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velogm.core_ui.view.UiState
import com.velogm.domain.model.InputFollower
import com.velogm.domain.usecase.GetInputFollowerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddFollowerViewModel @Inject constructor(
    private val getInputFollowerUseCase: GetInputFollowerUseCase
) : ViewModel() {
    private val _getInputFollower = MutableStateFlow<UiState<InputFollower>>(UiState.Loading)
    val getInputFollower: StateFlow<UiState<InputFollower>> = _getInputFollower.asStateFlow()

    fun getInputFollower(followerName: String?) = viewModelScope.launch {
        getInputFollowerUseCase(followerName).collect {
            _getInputFollower.value = UiState.Success(it)
            Timber.d("Success")
        }
        _getInputFollower.value = UiState.Loading
    }

}