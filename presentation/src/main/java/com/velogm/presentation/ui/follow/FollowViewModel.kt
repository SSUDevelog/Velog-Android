package com.velogm.presentation.ui.follow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velogm.core_ui.view.UiState
import com.velogm.domain.model.DeleteFollower
import com.velogm.domain.model.Follower
import com.velogm.domain.usecase.DeleteFollowerUseCase
import com.velogm.domain.usecase.GetFollowerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FollowViewModel @Inject constructor(
    private val getFollowerUseCase: GetFollowerUseCase,
    private val deleteFollowerUseCase: DeleteFollowerUseCase
) : ViewModel() {
    private val _getFollower = MutableStateFlow<UiState<List<Follower>>>(UiState.Loading)
    val getFollower: StateFlow<UiState<List<Follower>>> = _getFollower.asStateFlow()

    private val _deleteFollower = MutableStateFlow<UiState<DeleteFollower>>(UiState.Loading)
    val deleteFollower: StateFlow<UiState<DeleteFollower>> = _deleteFollower.asStateFlow()


    fun getFollower() = viewModelScope.launch {
        getFollowerUseCase().collectLatest {
            _getFollower.value = UiState.Success(it)
            Timber.d("Success")
        }
        _getFollower.value = UiState.Loading
    }

    fun deleteFollower(followerName: String) = viewModelScope.launch {
        deleteFollowerUseCase(followerName).collectLatest {
            _deleteFollower.value = UiState.Success(it)
            Timber.d("Success")
        }
        _deleteFollower.value = UiState.Loading
    }

}