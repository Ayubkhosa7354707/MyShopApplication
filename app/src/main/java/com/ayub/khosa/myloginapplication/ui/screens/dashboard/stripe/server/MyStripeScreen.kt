package com.ayub.khosa.myloginapplication.ui.screens.dashboard.stripe


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ayub.khosa.myloginapplication.ui.screens.dashboard.stripe.server.ServerConfigs
import com.ayub.khosa.myloginapplication.utils.PrintLogs
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import com.stripe.android.paymentsheet.rememberPaymentSheet

@Composable
fun MyStripeScreen(price: String, modifier: Modifier = Modifier) {
    val paymentSheet = rememberPaymentSheet(::onPaymentSheetResult)

    val context = LocalContext.current
    var customerConfig by remember { mutableStateOf<PaymentSheet.CustomerConfiguration?>(null) }
    var paymentIntentClientSecret by remember { mutableStateOf<String?>(null) }


    var rnds = (100..10000).random() // generated random from 100 to 10000 included

    var amount: Long = price.toLong() * 100

    PrintLogs.printD("  amount " + amount)
    // Get server configs
    val serverConfig = ServerConfigs.serverConfig(amount)

    LaunchedEffect(context) {
        paymentIntentClientSecret = serverConfig["paymentIntent"]
        customerConfig = PaymentSheet.CustomerConfiguration(
            serverConfig["customer"] ?: "",
            serverConfig["ephemeralKey"] ?: ""
        )


        val publishableKey = serverConfig["publishableKey"]
        PaymentConfiguration.init(context, publishableKey ?: "")
    }

    Box(modifier = modifier.wrapContentSize()) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            shape = RectangleShape,
            onClick = {
                val currentConfig = customerConfig
                val currentClientSecret = paymentIntentClientSecret

                if (currentConfig != null && currentClientSecret != null) {
                    presentPaymentSheet(paymentSheet, currentConfig, currentClientSecret)
                }
            }) {

            var amountRs: Double = amount / 100.0
            Text("PAY " + amountRs + "PKR")
        }
    }
}

// This is from the Stripe documentation
/**This displays the Stripe bottom sheet on button click*/
private fun presentPaymentSheet(
    paymentSheet: PaymentSheet,
    customerConfig: PaymentSheet.CustomerConfiguration,
    paymentIntentClientSecret: String
) {
    paymentSheet.presentWithPaymentIntent(
        paymentIntentClientSecret,
        PaymentSheet.Configuration(
            merchantDisplayName = "My merchant name",
            customer = customerConfig,
            allowsDelayedPaymentMethods = true
        )
    )
}

// This is from the Stripe documentation
/**Fetch the result from payment operation as cancelled, failed and completed*/
private fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {
    when (paymentSheetResult) {
        is PaymentSheetResult.Canceled -> {
            PrintLogs.printD("Stripe Log ->  Cancelled")
        }

        is PaymentSheetResult.Failed -> {
            PrintLogs.printD("Stripe Log -> Failed")
        }

        is PaymentSheetResult.Completed -> {
            PrintLogs.printD("Stripe Log -> Completed")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyStripeScreenPreview() {
    MyStripeScreen("1.0")

}