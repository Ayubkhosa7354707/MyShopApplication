package com.ayub.khosa.myloginapplication.ui.screens.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ayub.khosa.myloginapplication.api.RetrofitBuilder
import com.ayub.khosa.myloginapplication.common.TitleText
import com.ayub.khosa.myloginapplication.common.CustomDefaultBtn
import com.ayub.khosa.myloginapplication.room.MainActivityRepository
import com.ayub.khosa.myloginapplication.ui.screens.dashboard.categoryScreen.CatagorylistScreen
import com.ayub.khosa.myloginapplication.ui.screens.dashboard.categoryScreen.CategorysViewModel
import com.ayub.khosa.myloginapplication.ui.screens.dashboard.productsScreen.ProductlistScreen
import com.ayub.khosa.myloginapplication.ui.screens.dashboard.productsScreen.ProductsViewModel
import com.ayub.khosa.myloginapplication.ui.theme.MyLoginApplicationTheme
import com.ayub.khosa.myloginapplication.utils.NetworkHelper

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
    email_id: String,
    onNavigateBack: () -> Boolean
) {
    var show_products = rememberSaveable { mutableStateOf(false) }
    var show_category = rememberSaveable { mutableStateOf(false) }


    TitleText(Modifier.padding(top = 30.dp, start = 10.dp, end = 10.dp), "WellCome Screen")
    Column(modifier = Modifier.padding(top = 80.dp, start = 10.dp, end = 10.dp)) {
        Text(
            text = "welcome   $email_id!",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold, modifier = Modifier
                .clickable {
                    viewModel.user_logout("$email_id")
                    onNavigateBack.invoke()

                }
                .padding(10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
        ) {
            CustomDefaultBtn(
                btnText = "Products",
                onClick = { show_products.value = true;show_category.value = false },
            )

            CustomDefaultBtn(
                btnText = "Category",
                onClick = { show_products.value = false;show_category.value = true },
            )


        }

        if (show_products.value) {
            MYPlayListScreen()
        }
        if (show_category.value) {
            MYCatagoryListScreen()
        }


    }


}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(
        viewModel(), "a@a",
        onNavigateBack = { true },
    )
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun MYPlayListScreen(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val context = LocalContext.current
        val networkHelper: NetworkHelper = NetworkHelper(context.applicationContext)


        val repository: MainActivityRepository by lazy {
            val apiService = RetrofitBuilder.provideRestApiService(
                RetrofitBuilder.getRetrofit(
                    RetrofitBuilder.provideOkHttpClient(RetrofitBuilder.providesLoggingInterceptor()),
                    RetrofitBuilder.providesBaseUrl()
                )
            )
            MainActivityRepository(context.applicationContext, apiService)


        }
        val viewModel: ProductsViewModel = ProductsViewModel(repository, networkHelper)
        ProductlistScreen(viewModel)
    }
}


@Preview(showBackground = true, name = "")
@Composable
fun MYPlayListScreenPreview() {

    MyLoginApplicationTheme {
        MYPlayListScreen(modifier = Modifier.fillMaxSize())
    }
}


@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun MYCatagoryListScreen(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val context = LocalContext.current
        val networkHelper: NetworkHelper = NetworkHelper(context.applicationContext)


        val repository: MainActivityRepository by lazy {
            val apiService = RetrofitBuilder.provideRestApiService(
                RetrofitBuilder.getRetrofit(
                    RetrofitBuilder.provideOkHttpClient(RetrofitBuilder.providesLoggingInterceptor()),
                    RetrofitBuilder.providesBaseUrl()
                )
            )
            MainActivityRepository(context.applicationContext, apiService)


        }
        val viewModel: ViewModel = CategorysViewModel(repository, networkHelper)
        CatagorylistScreen(viewModel as CategorysViewModel)
    }
}


@Preview(showBackground = true, name = "")
@Composable
fun MYCatagoryListScreenPreview() {

    MyLoginApplicationTheme {
        MYCatagoryListScreen(modifier = Modifier.fillMaxSize())
    }
}
