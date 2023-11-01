package com.velogm.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velogm.core_ui.view.UiState
import com.velogm.domain.usecase.AddRecentSearchWordUseCase
import com.velogm.domain.usecase.DeleteRecentSearchWordUseCase
import com.velogm.domain.usecase.GetPopularTagUseCase
import com.velogm.domain.usecase.GetRecentSearchWordUseCase
import com.velogm.domain.usecase.GetTagPostsUseCase
import com.velogm.domain.usecase.GetTagUseCase
import com.velogm.presentation.mapper.toPostModelList
import com.velogm.presentation.mapper.toRecentSearchWordEntity
import com.velogm.presentation.mapper.toTagModelEntity
import com.velogm.presentation.model.PostModelList
import com.velogm.presentation.model.RecentSearchWordModel
import com.velogm.presentation.model.TagModel
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
class SearchViewModel @Inject constructor(
    private val getTagUseCase: GetTagUseCase,
    private val getPopularTagUseCase: GetPopularTagUseCase,
    private val getTagPostsUseCase: GetTagPostsUseCase,
    private val getRecentSearchWordUseCase: GetRecentSearchWordUseCase,
    private val addRecentSearchWordUseCase: AddRecentSearchWordUseCase,
    private val deleteRecentSearchWordUseCase: DeleteRecentSearchWordUseCase
) : ViewModel() {


    private val _tagPopularListData = MutableStateFlow<UiState<List<TagModel>>>(UiState.Loading)
    val tagPopularListData: StateFlow<UiState<List<TagModel>>> = _tagPopularListData.asStateFlow()

    private val _eventData = MutableSharedFlow<UiState<Boolean>>()
    val eventData: SharedFlow<UiState<Boolean>> = _eventData.asSharedFlow()

    private val _postListData = MutableStateFlow<UiState<PostModelList>>(UiState.Loading)
    val postListData: StateFlow<UiState<PostModelList>> = _postListData.asStateFlow()

    private val _getRecentSearchWord =
        MutableStateFlow<UiState<List<RecentSearchWordModel>>>(UiState.Loading)
    val getRecentSearchWord: StateFlow<UiState<List<RecentSearchWordModel>>> = _getRecentSearchWord

    init {
        getPopularTag()
        getRecentSearchWord()
    }


    fun getPopularTag() = viewModelScope.launch {
        getPopularTagUseCase().collect {
            val tagList = it.toTagModelEntity()
            _tagPopularListData.value = UiState.Success(tagList)
            Timber.d(it.toString())
        }
    }

    fun getTagPost(tag: String) = viewModelScope.launch {
        getTagPostsUseCase(tag).collect {
            val postList = it.toPostModelList()
            _postListData.value = UiState.Success(postList)
        }
    }

    fun getRecentSearchWord() = viewModelScope.launch {
        getRecentSearchWordUseCase().collect {
            _getRecentSearchWord.value = UiState.Success(it.toRecentSearchWordEntity())
        }
    }

    fun addSearchWord(word: String) = viewModelScope.launch {
        addRecentSearchWordUseCase(word).collect {}
    }

    fun deleteRecentSearchWord() = viewModelScope.launch {
        deleteRecentSearchWordUseCase().collect {
            _eventData.emit(UiState.Success(true))
        }
    }
}