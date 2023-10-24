package com.velogm.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velogm.core_ui.view.UiState
import com.velogm.domain.usecase.*
import com.velogm.presentation.mapper.toPostModelList
import com.velogm.presentation.mapper.toTagModelEntity
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
class SearchViewModel@Inject constructor(
    private val getPopularTagUseCase: GetPopularTagUseCase,
    private val getTagPostsUseCase: GetTagPostsUseCase
) : ViewModel() {


    private val _tagPopularListData = MutableStateFlow<UiState<List<TagModel>>>(UiState.Loading)
    val tagPopularListData: StateFlow<UiState<List<TagModel>>> = _tagPopularListData.asStateFlow()

    private val _postListData = MutableStateFlow<UiState<PostModelList>>(UiState.Loading)
    val postListData: StateFlow<UiState<PostModelList>> = _postListData.asStateFlow()

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

    fun getTagPost(tag:String) = viewModelScope.launch {
        getTagPostsUseCase(tag).collect {
            val postList= it.toPostModelList()
            _postListData.value=UiState.Success(postList)
        }
    }
}