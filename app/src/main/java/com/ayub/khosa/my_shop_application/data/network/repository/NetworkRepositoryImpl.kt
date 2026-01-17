package com.ayub.khosa.my_shop_application.data.network.repository

import com.ayub.khosa.my_shop_application.data.network.api.ApiService
import com.ayub.khosa.my_shop_application.data.network.dto.Category
import com.ayub.khosa.my_shop_application.data.network.dto.Product
import com.ayub.khosa.my_shop_application.utils.PrintLogs
import com.ayub.khosa.my_shop_application.utils.Response
import javax.inject.Inject


class NetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : NetworkRepository {
    override suspend fun getProductsListFromApi(): Response<List<Product>> {
        return try {
            val response = apiService.getProductsListFromApi()
            if (response.products.isEmpty()) {
                Response.Error("No products found")
            }
            Response.Success(response.products)
        } catch (e: Exception) {
            e.printStackTrace()
            PrintLogs.printE("Exception "+e.message)
            Response.Error("Error-> "+e.message)
        }
    }

    override suspend fun getSingleProductByIdFromApi(productId: Int): Response<Product> {
        return try {
            val response = apiService.getSingleProductByIdFromApi(""+productId)
            if (response.name.isEmpty()) {
                Response.Error("No products found")
            }

            var product: Product= Product()
            product.id=response.id
            product.name=response.name
            product.category=response.category
            product.description=response.description
            product.price=response.price
            product.img=response.img
            Response.Success(product)
        } catch (e: Exception) {
            e.printStackTrace()
            PrintLogs.printE("Exception "+e.message)
            Response.Error("Error-> "+e.message)
        }
    }

    override suspend fun getAllCategoriesListFromApi(): Response<List<Category>> {
        return try {
            val response = apiService.getAllCategoriesListFromApi()
            if (response.categories.isEmpty()) {
                Response.Error("No Category found")
            }
            Response.Success(response.categories)
        } catch (e: Exception) {
            e.printStackTrace()
            PrintLogs.printE("Exception "+e.message)
            Response.Error("Error-> "+e.message)
        }
    }

    override suspend fun getProductsListByCategoryNameFromApi(categoryName: String): Response<List<Product>> {

        return try {
            val response = apiService.getProductsListByCategoryNameFromApi(categoryName)
            if (response.products.isEmpty()) {
                Response.Error("No products found")
            }
            Response.Success(response.products)
        } catch (e: Exception) {
            e.printStackTrace()
            PrintLogs.printE("Exception "+e.message)
            Response.Error("Error-> "+e.message)
        }
    }


}