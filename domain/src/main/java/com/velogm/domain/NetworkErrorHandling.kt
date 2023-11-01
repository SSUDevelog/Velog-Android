package com.velogm.domain

sealed class NetworkErrorHandling {
    object Unauthorized : NetworkErrorHandling()
    object ServerError : NetworkErrorHandling()
    object OtherError : NetworkErrorHandling()
}