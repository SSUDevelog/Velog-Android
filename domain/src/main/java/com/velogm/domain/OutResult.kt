package com.velogm.domain

import kotlinx.coroutines.flow.Flow

sealed class OutResult<out T> {
    data class Success<T>(val data: T) : OutResult<T>()
    data class Failure<T>(val error: Throwable?) : OutResult<T>()
}

suspend fun <T> Flow<OutResult<T>>.collectOutResult(
    handleSuccess: (OutResult.Success<T>) -> Unit = {},
    handleFail: (OutResult.Failure<T>) -> Unit = {},
) {
    collect { outcome ->
        when (outcome) {
            is OutResult.Success -> handleSuccess(outcome)
            is OutResult.Failure -> handleFail(outcome)
        }
    }
}