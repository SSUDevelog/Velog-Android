package com.velogm.presentation.ui.home.screenhome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velogm.core_ui.view.UiState
import com.velogm.domain.usecase.GetTrendPostUseCase
import com.velogm.presentation.mapper.toPostModelList
import com.velogm.presentation.model.PostModelList
import com.velogm.presentation.model.TagModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ScreenViewModel @Inject constructor(
    private val getTrendPostUseCase: GetTrendPostUseCase
) : ViewModel() {

    private val _postListData = MutableStateFlow<UiState<PostModelList>>(UiState.Loading)
    val postListData: StateFlow<UiState<PostModelList>> = _postListData.asStateFlow()


    fun getTrendPost() = viewModelScope.launch {
       getTrendPostUseCase().collect {
            val postList= it.toPostModelList()
           _postListData.value=UiState.Success(postList)
        }
    }
}