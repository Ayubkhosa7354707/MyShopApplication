package com.ayub.khosa.myloginapplication.ui.screens.dashboard.productsScreen

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayub.khosa.myloginapplication.api.RetrofitBuilder
import com.ayub.khosa.myloginapplication.common.Loading
import com.ayub.khosa.myloginapplication.common.TitleText
import com.ayub.khosa.myloginapplication.common.CustomDefaultBtn
import com.ayub.khosa.myloginapplication.model.PRODUCT
import com.ayub.khosa.myloginapplication.room.MainActivityRepository
import com.ayub.khosa.myloginapplication.ui.theme.MyLoginApplicationTheme
import com.ayub.khosa.myloginapplication.utils.NetworkHelper


@Composable
fun ProductlistScreen(
    viewModel: ProductsViewModel,
    modifier: Modifier = Modifier
) {
    var mydatalist = rememberMutableStateListOf<PRODUCT>()
    if (mydatalist.isEmpty()) {
        viewModel.onClickCallgetAllProducts()
        viewModel.getproductsItems().forEach { it ->
            LaunchedEffect(Unit) {
                mydatalist.add(it)
            }
        }
    }

    TitleText(Modifier.padding(top = 30.dp, start = 10.dp, end = 10.dp), "Product List Screen")

    Column(modifier = Modifier.padding(top = 80.dp, start = 10.dp, end = 10.dp)) {


        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
        ) {
            CustomDefaultBtn(
                btnText = "Products",
                onClick = { viewModel.onClickCallgetAllProducts() },
            )

            CustomDefaultBtn(
                btnText = "Products_DB",
                onClick = { viewModel.onClickCallgetProducts_DB() },
            )


        }

        Spacer(modifier = Modifier.height(8.dp))

        if (!viewModel.get_is_busy()) {


            LazyColumn(
                modifier = Modifier.align(Alignment.Start)
            ) {
                items(
                    items = mydatalist,
                    key = { product -> product.product_id }

                ) { product ->


                    ProductItem(
                        viewModel,
                        product_id = product.product_id,
                        name = product.name,
                        price = product.price,
                        img = product.img,
                        category = product.category,
                        description = product.description,
                        modifier = modifier
                    )
                }

            }
        } else {
            Loading()

        }


    }
}

@Composable
fun <T : Any> rememberMutableStateListOf(vararg elements: T): SnapshotStateList<T> {
    return rememberSaveable(saver = snapshotStateListSaver()) {
        elements.toList().toMutableStateList()
    }
}

private fun <T : Any> snapshotStateListSaver() = listSaver<SnapshotStateList<T>, T>(
    save = { stateList -> stateList.toList() },
    restore = { it.toMutableStateList() },
)


@Composable
fun DoneButton(msg: String) {
    val context = LocalContext.current
    Button(shape = RectangleShape, onClick = { showToast(context, "Button clicked " + msg) }) {
        Text(
            text = "Done",
            color = Color.Yellow,
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier
                .background(Color(0xFF8BC34A))
                .padding(4.dp)
        )
    }
}

fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}


@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true, name = "")
@Composable
fun MYPlayListScreenPreview() {

    MyLoginApplicationTheme {

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