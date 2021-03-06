package com.example.koshelok.data.service

import com.example.koshelok.data.service.api.CategoryApi
import com.example.koshelok.data.service.api.CreateTransactionApi
import com.example.koshelok.data.service.api.MainScreenDataApi
import com.example.koshelok.data.service.api.TransactionApi
import com.example.koshelok.data.service.api.UserApi
import com.example.koshelok.data.service.api.WalletApi
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

@SuppressWarnings("TooManyFunctions")
interface AppService {

    @GET("wallets/{walletId}")
    fun getWallet(@Path("walletId") walletId: Long): Single<WalletApi>

    @POST("wallets")
    fun createWallet(@Body walletApi: WalletApi): Single<Long>

    @DELETE("wallets/{walletId}")
    fun deleteWallet(@Path("walletId") walletId: Long): Completable

    @GET("wallets/person/{personId}/all")
    fun getDataForMainScreen(@Path("personId") personId: Long): Single<MainScreenDataApi>

    @POST("transactions")
    fun createTransaction(@Body transactionApi: CreateTransactionApi): Completable

    @GET("transactions/withCategory/{walletId}")
    fun getTransactions(@Path("walletId") walletId: Long): Single<List<TransactionApi>>

    @PUT("transactions/{transactionId}")
    fun editTransaction(
        @Path("transactionId")
        id: Long,
        @Body transactionApi: CreateTransactionApi
    ): Completable

    @DELETE("transactions/{transactionId}")
    fun deleteTransaction(@Path("transactionId") id: Long): Completable

    @GET("categories/person/{personId}/{value}")
    fun getCategories(
        @Path("personId") personId: Long,
        @Path("value") type: Int
    ): Single<List<CategoryApi>>

    @POST("categories")
    fun createCategory(@Body categoryApi: CategoryApi): Completable

    @POST("person")
    fun registrationUser(@Body userApi: UserApi): Single<Long>
}
