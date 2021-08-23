package com.example.koshelok.domain.repository

import com.example.koshelok.domain.Response
import com.example.koshelok.ui.entity.TransactionEntity
import io.reactivex.rxjava3.core.Single

interface ActionTransactionRepository {

    fun editTransaction(transactionEntity: TransactionEntity): Single<Response>

    fun createTransaction(transactionEntity: TransactionEntity): Single<Response>
}
