package com.velogm.presentation.ui

import androidx.lifecycle.ViewModel
import com.velogm.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {


    fun testA(a:String) {
        authRepository.saveAccessToken(a)
    }

    fun testB():String{
        return authRepository.getAccessToken()
    }
}
