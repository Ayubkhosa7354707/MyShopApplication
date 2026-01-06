package com.ayub.khosa.myloginapplication.ui.screens.dashboard.productsScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayub.khosa.myloginapplication.model.ListPRODUCTS
import com.ayub.khosa.myloginapplication.model.PRODUCT
import com.ayub.khosa.myloginapplication.room.MainActivityRepository
import com.ayub.khosa.myloginapplication.utils.NetworkHelper
import com.ayub.khosa.myloginapplication.utils.PrintLogs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    private val repository: MainActivityRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {


    private var myproducts: ArrayList<PRODUCT> = ArrayList<PRODUCT>()
    private var _tasks = MutableStateFlow(myproducts)
    val tasks: ArrayList<PRODUCT>
        get() = _tasks.value


    fun getproductsItems(): List<PRODUCT> {

        return tasks
    }

    private val errorMessage = mutableStateOf<String>("")


    fun geterrorMessage(): String {
        PrintLogs.printD(" geterrorMessage  error --> " + errorMessage.value)
        return errorMessage.value!!
    }


    fun seterrorMessage(error: String) {
        errorMessage.value = error
        PrintLogs.printD(" seterrorMessage  error --> " + error)
        PrintLogs.printD(" seterrorMessage  error value --> " + errorMessage.value)
    }

    private val _is_busy = mutableStateOf<Boolean>(false)


    fun get_is_busy(): Boolean {
        PrintLogs.printD("get_is_busy   " + _is_busy.value)
        return _is_busy.value!!
    }

    fun set_is_busy(b: Boolean) {
        _is_busy.value = b
        PrintLogs.printD("set_is_busy   " + _is_busy.value)
    }

    fun isNetworkConnected(): Boolean {
        if (networkHelper.isNetworkConnected()) {
            return true
        } else {
            return false
        }
    }

    fun onClickCallgetAllProducts() {
        PrintLogs.printD("onClickCallgetAllProducts  ")
        set_is_busy(true)
        viewModelScope.launch {
            try {
                if (networkHelper.isNetworkConnected()) {

                    seterrorMessage("Iternet is OKay")
                    val response = repository.getAllProducts()
                    PrintLogs.printD(" onResponse Success :  " + response.response)
                    PrintLogs.printD(" onResponse Success :  " + response.data)
                    PrintLogs.printD(" onResponse Success :  " + response.error)

                    if (response.response == "Success") {
                        val data = kotlin.collections.ArrayList<PRODUCT>()
                        response.data.products.forEach { it ->
                            PrintLogs.printD(" id of product : " + it.id)
                            PrintLogs.printD(" name of product : " + it.name)
                            PrintLogs.printD(" product_id of product : " + it.product_id)
                            PrintLogs.printD(" category of product : " + it.category)
                            PrintLogs.printD(" img of product : " + it.img)
                            PrintLogs.printD(" price of product : " + it.price)

                            data.add(it)
                            addProductinDB(it)
                        }

                        PrintLogs.printD(" data.size ----  " + data.size)
                        var listproduct: ListPRODUCTS = ListPRODUCTS(data)
                        _tasks.value = data
                        PrintLogs.printD(" listproduct.products.size ----  " + listproduct.products.size)

                    } else {
                        seterrorMessage(response.error)
                    }
                } else {
                    seterrorMessage("No iternet")
                }
                set_is_busy(false)
            } catch (e: Exception) {
                seterrorMessage("Exception  " + e.message.toString())
                PrintLogs.printD("Exception  " + e.message)
                set_is_busy(false)
            }


        }


        PrintLogs.printD("onClickCallgetAllProducts  ")
    }

    fun onClickCallgetProducts_DB() {

        set_is_busy(true)
        PrintLogs.printD(" -----------  onClickCallgetProducts_DB   -------------")

        try {
            val response = repository.getProducts_DB()

            seterrorMessage("Database is OKay")
            response.products.forEach {
                PrintLogs.printD(" name of product : " + it.name)
                PrintLogs.printD(" product_id of product : " + it.product_id)
                PrintLogs.printD(" category of product : " + it.category)
                PrintLogs.printD(" img of product : " + it.img)
                PrintLogs.printD(" price of product : " + it.price)

            }

            _tasks.value = response.products
            set_is_busy(false)
        } catch (e: Exception) {
            seterrorMessage("Exception  " + e.message.toString())
            PrintLogs.printD("Exception  " + e.message)
            set_is_busy(false)
        }


        PrintLogs.printD(" -----------  onClickCallgetProducts_DB END  -------------")


    }

    fun addProductinDB(product: PRODUCT) {
        //   set_is_busy(true)
        try {
            if (repository.fetchProductByName(product.name, product.product_id) != null) {
                repository.updateProductinDB(product)
            } else {
                repository.insertProductinDB(product)
            }

        } catch (e: Exception) {
            seterrorMessage("Exception " + e.message.toString())

            PrintLogs.printD("Exception: ${e.message}")
        }
        //   set_is_busy(false)
    }


}
