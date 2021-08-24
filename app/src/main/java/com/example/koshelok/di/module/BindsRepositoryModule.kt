package com.example.koshelok.di.module

import com.example.koshelok.data.repository.ActionTransactionRepositoryImpl
import com.example.koshelok.data.repository.CreateWalletRepositoryImpl
import com.example.koshelok.data.repository.DeleteTransactionRepositoryImpl
import com.example.koshelok.data.repository.DetailWalletRepositoryImpl
import com.example.koshelok.data.repository.ListWalletRepositoryImpl
import com.example.koshelok.data.repository.LoadCategoriesRepositoryImpl
import com.example.koshelok.domain.repository.ActionTransactionRepository
import com.example.koshelok.domain.repository.CreateWalletRepository
import com.example.koshelok.domain.repository.DeleteTransactionRepository
import com.example.koshelok.domain.repository.DetailWalletRepository
import com.example.koshelok.domain.repository.ListWalletRepository
import com.example.koshelok.domain.repository.LoadCategoriesRepository
import dagger.Binds
import dagger.Module

@Module
interface BindsRepositoryModule {

    @Binds
    fun bindDetailWalletRepository(detailWalletRepositoryImpl: DetailWalletRepositoryImpl)
            : DetailWalletRepository

    @Binds
    fun bindListWalletRepository(listWalletRepositoryImpl: ListWalletRepositoryImpl)
            : ListWalletRepository

    @Binds
    fun bindOptionTransactionRepository(optionsTransactionRepositoryImpl: DeleteTransactionRepositoryImpl)
            : DeleteTransactionRepository

    @Binds
    fun bindActionTransactionRepository(actionTransactionRepositoryImpl: ActionTransactionRepositoryImpl)
            : ActionTransactionRepository

    @Binds
    fun bindCreateWalletRepository(createWalletRepositoryImpl: CreateWalletRepositoryImpl)
            : CreateWalletRepository

    @Binds
    fun bindLoadCategoriesRepository(loadCategoriesRepositoryImpl: LoadCategoriesRepositoryImpl)
            : LoadCategoriesRepository
}
