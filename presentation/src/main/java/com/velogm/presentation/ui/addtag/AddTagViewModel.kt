package com.velogm.presentation.ui.addtag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velogm.core_ui.view.UiState
import com.velogm.domain.usecase.AddTagUseCase
import com.velogm.domain.usecase.DeleteTagUseCase
import com.velogm.domain.usecase.GetPopularTagUseCase
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
class AddTagViewModel @Inject constructor(
    private val getPopularTagUseCase: GetPopularTagUseCase,
    private val deleteTagUseCase: DeleteTagUseCase,
    private val addTagUseCase: AddTagUseCase
) : ViewModel() {

    private val _tagPopularListData = MutableStateFlow<UiState<List<TagModel>>>(UiState.Loading)
    val tagPopularListData: StateFlow<UiState<List<TagModel>>> = _tagPopularListData.asStateFlow()


    private val _eventData = MutableStateFlow<UiState<Boolean>>(UiState.Loading)
    val eventData: StateFlow<UiState<Boolean>> = _eventData.asStateFlow()

    init {
        getPopularTag()
    }

    fun getPopularTag() = viewModelScope.launch {
        getPopularTagUseCase().collect {
            val tagList = it.toTagModelEntity()
            _tagPopularListData.value = UiState.Success(tagList)
            Timber.d(it.toString())
        }
    }

    fun deleteTag(tag: String) = viewModelScope.launch {
        deleteTagUseCase(tag).collect {
            _eventData.value = UiState.Success(true)
        }
        _eventData.value = UiState.Loading
    }

    fun addTag(tag: String) = viewModelScope.launch {
        addTagUseCase(tag).collect {
            _eventData.value = UiState.Success(true)
        }
        _eventData.value = UiState.Loading
    }
}