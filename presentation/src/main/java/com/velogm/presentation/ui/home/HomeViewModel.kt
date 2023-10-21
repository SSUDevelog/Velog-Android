package com.velogm.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velogm.core_ui.view.UiState
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
class HomeViewModel @Inject constructor(
    private val getTagUseCase: GetTagUseCase
) : ViewModel() {

    private val _tagListData = MutableStateFlow<UiState<List<TagModel>>>(UiState.Loading)
    val tagListData: StateFlow<UiState<List<TagModel>>> = _tagListData.asStateFlow()

    init {
        getTag()
    }
    fun getTag() = viewModelScope.launch {
        getTagUseCase().collect {
            val tagList = it.toTagModelEntity()
            _tagListData.value = UiState.Success(tagList)
            Timber.d(it.toString())
        }
    }

}