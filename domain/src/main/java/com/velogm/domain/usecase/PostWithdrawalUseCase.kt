package com.velogm.domain.usecase

import com.velogm.domain.model.Withdrawal
import com.velogm.domain.repository.SignRepository
import kotlinx.coroutines.flow.Flow

class PostWithdrawalUseCase(
    private val repository: SignRepository
) {
    suspend operator fun invoke(): Flow<Withdrawal> =
        repository.postWithdrawal()
}