package com.example.koshelok.domain.repository

import io.reactivex.rxjava3.core.Completable

interface DeleteWalletRepository {

    fun deleteWallet(walletId: Long): Completable
}
