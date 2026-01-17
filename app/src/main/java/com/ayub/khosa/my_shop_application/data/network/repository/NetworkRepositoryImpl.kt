package com.ayub.khosa.my_shop_application.data.network.repository

import com.ayub.khosa.my_shop_application.data.network.api.ApiService
import com.ayub.khosa.my_shop_application.data.network.dto.Category
import com.ayub.khosa.my_shop_application.data.network.dto.Product
import com.ayub.khosa.my_shop_application.utils.PrintLogs
import com.ayub.khosa.my_shop_application.utils.Response
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


class NetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : NetworkRepository {


    override suspend fun getSingleProductByIdFromApi(productId: Int): Flow<Response<Product>> =
        callbackFlow {
            PrintLogs.printInfo("getSingleProductByIdFromApi  Network repository impl ")
            try {
                this@callbackFlow.trySendBlocking(Response.Loading)
                val response = apiService.getSingleProductByIdFromApi("" + productId)
                this@callbackFlow.trySendBlocking(Response.Success(response))
                awaitClose {
                    channel.close()
                    cancel()
                }
            } catch (e: Exception) {
                PrintLogs.printE("Error ->" + e.message)
                this@callbackFlow.trySendBlocking(Response.Error("Error ->" + e.message))
            }
        }


    override suspend fun getAllCategoriesListFromApi(): Flow<Response<List<Category>>> =
        callbackFlow {
            PrintLogs.printInfo("getAllCategoriesListFromApi  Network repository impl ")
            try {
                this@callbackFlow.trySendBlocking(Response.Loading)
                val response = apiService.getAllCategoriesListFromApi()
                this@callbackFlow.trySendBlocking(Response.Success(response.categories))
                awaitClose {
                    channel.close()
                    cancel()
                }
            } catch (e: Exception) {
                PrintLogs.printE("Error ->" + e.message)
                this@callbackFlow.trySendBlocking(Response.Error("Error ->" + e.message))
            }
        }


    override suspend fun getProductsListByCategoryNameFromApi(categoryName: String): Flow<Response<List<Product>>> =
        callbackFlow {
            PrintLogs.printInfo("getProductsListByCategoryNameFromApi  Network repository impl ")
            try {
                this@callbackFlow.trySendBlocking(Response.Loading)


                val response =
                    apiService.getProductsListByCategoryNameFromApi("\"" + categoryName + "\"")
                PrintLogs.printInfo("Api response --  " + response.toString())
                this@callbackFlow.trySendBlocking(Response.Success(response.products))
                awaitClose {
                    channel.close()
                    cancel()
                }
            } catch (e: Exception) {
                PrintLogs.printE("Error ->" + e.message)
                this@callbackFlow.trySendBlocking(Response.Error("Error ->" + e.message))
            }
        }


    override suspend fun getProductsListFromApi(): Flow<Response<List<Product>>> = callbackFlow {
        PrintLogs.printInfo("getProductsListFromApi  Network repository impl ")
        try {
            this@callbackFlow.trySendBlocking(Response.Loading)
            val response = apiService.getProductsListFromApi()
            this@callbackFlow.trySendBlocking(Response.Success(response.products))
            awaitClose {
                channel.close()
                cancel()
            }
        } catch (e: Exception) {
            PrintLogs.printE("Error ->" + e.message)
            this@callbackFlow.trySendBlocking(Response.Error("Error ->" + e.message))
        }
    }

}