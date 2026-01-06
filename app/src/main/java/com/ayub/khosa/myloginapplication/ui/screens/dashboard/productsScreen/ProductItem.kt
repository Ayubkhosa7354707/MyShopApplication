package com.ayub.khosa.myloginapplication.ui.screens.dashboard.productsScreen


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberAsyncImagePainter
import com.ayub.khosa.myloginapplication.api.RetrofitBuilder
import com.ayub.khosa.myloginapplication.common.TextExample
import com.ayub.khosa.myloginapplication.room.MainActivityRepository
import com.ayub.khosa.myloginapplication.ui.screens.dashboard.stripe.MyStripeScreen
import com.ayub.khosa.myloginapplication.ui.theme.MyLoginApplicationTheme
import com.ayub.khosa.myloginapplication.utils.NetworkHelper

@OptIn(ExperimentalMaterial3Api::class)
@JvmOverloads

@Composable
fun ProductItem(
    viewModel: ProductsViewModel,
    product_id: String,
    name: String,
    price: String,
    img: String,
    category: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    LocalContext.current
    var myproduct_id = rememberSaveable { mutableStateOf(product_id) }
    var myname = rememberSaveable { mutableStateOf(name) }
    var myprice = rememberSaveable { mutableStateOf(price) }
    var myimg = rememberSaveable { mutableStateOf(img) }
    var mycategory = rememberSaveable { mutableStateOf(category) }

    var mydescription = rememberSaveable { mutableStateOf(description) }
    var show_stripe = rememberSaveable { mutableStateOf(false) }









    Row(
        modifier = Modifier
            .border(1.dp, Color.Black),
        verticalAlignment = Alignment.CenterVertically,


        ) {

        Image(
            painter = rememberAsyncImagePainter(myimg.value),
            contentDescription = null,
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
                .padding(2.dp)
                .border(2.dp, Color.Black)
        )
        Column(
            modifier = Modifier
                .wrapContentSize()
        ) {
            TextExample(
                " id :" + myproduct_id.value,

                )
            TextExample(
                "Name :" +
                        myname.value
            )
//                TextExample(
//                    "Price :" +
//                            myprice.value + "PKR"
//                )


            if (!show_stripe.value) {
                Text(
                    modifier = Modifier
                        .clickable {
                            show_stripe.value = true
                        }
                        .padding(10.dp)
                        .fillMaxWidth(),
                    text = myprice.value + "PKR",
                    color = Color.Blue, fontSize = 18.sp,
                )
            } else {
                if (viewModel.isNetworkConnected()) {
//                    MyStripeScreen(myprice.value)

                    CustomAlertDialog(myname.value,myprice.value,onDismissRequest = { show_stripe.value = false })


                } else {
                    val context = LocalContext.current
                    showToast(context, "NO Internet... ")
                }
            }

            TextExample(
                "Category :" +
                        mycategory.value
            )
            TextExample(
                "Description :" +
                        mydescription.value
            )

        }


    }
}


@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true, name = "")
@Composable
fun ProductItemPreview() {
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

    MyLoginApplicationTheme {
        LocalContext.current
        ProductItem(
            viewModel,
            "1",
            "name",
            "10",
            "img",
            "category",
            "description",
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Composable
fun CustomAlertDialog(productname:String , productprice:String ,onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = onDismissRequest) {
        // Your custom content goes here
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Custom Dialog Title", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = " Do You want to purchase $productname")
                MyStripeScreen(productprice)
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = onDismissRequest) {
                    Text("Dismiss")
                }
            }
        }
    }
}