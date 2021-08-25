package com.example.koshelok.data.repository

import com.example.koshelok.data.service.AppService
import com.example.koshelok.domain.repository.DeleteTransactionRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class DeleteTransactionRepositoryImpl @Inject constructor(
    private val appService: AppService,
) :
    DeleteTransactionRepository {

    override fun deleteTransaction(transactionId: Long): Completable {
        return appService.deleteTransaction(transactionId)
    }
}
