package com.ayub.khosa.myloginapplication.ui.screens.dashboard.stripe.server


import com.ayub.khosa.myloginapplication.utils.PrintLogs 
import kotlinx.coroutines.runBlocking

import com.stripe.Stripe
import com.stripe.model.Customer.create
import com.stripe.model.EphemeralKey
import com.stripe.model.PaymentIntent
import com.stripe.param.CustomerCreateParams
import com.stripe.param.EphemeralKeyCreateParams
import com.stripe.param.PaymentIntentCreateParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

object ServerConfigs {

    /**Set up server configs that returns stripe payment info
     *
     * Do not run this code in the main thread. You can use runBlocking but pass the IO dispatcher to
     * avoid blocking the UI thread which throws an error
     *
     * You can return a custom data class of [StripePaymentInfo]
     * */
    fun serverConfig(amount: Long): Map<String, String> = runBlocking(Dispatchers.IO) {

        // Pass your api key from: https://dashboard.stripe.com/apikeys
//        Stripe.apiKey =
//            "enter your stripe apikey_sk_test_51Ng7fCBRTw3h93jKhGLzqrluIYqmJBHCRfKg0StCB5PBoEYbwUD0B90Pvtma7tbaMk1WmKBNxyWcDjr4CVWwjZTS00hJoNT2Ja"

        // Create customer object
        val customerParams = CustomerCreateParams.builder().build()
        val customer = create(customerParams)

        // Create ephemeralKey object
        val ephemeralKeyParams = EphemeralKeyCreateParams.builder()
            .setStripeVersion("2024-04-10")
            .setCustomer(customer.id)
            .build()
        val ephemeralKey: EphemeralKey = EphemeralKey.create(ephemeralKeyParams)

        PrintLogs.printD(" amount "+amount)
        // Create payment intent object
        val paymentIntentParams = PaymentIntentCreateParams.builder()
            .setAmount( amount  ) // You can pass amount as a parameter
            .setCurrency("usd") // Set valid currency eg. usd, euro
            .setCustomer(customer.id)
            .build()
        val paymentIntent = PaymentIntent.create(paymentIntentParams)

        // Stripe payment info. You can pass this as a custom data class
        val stripePaymentInfo = mapOf(
            "paymentIntent" to paymentIntent.clientSecret,
            "ephemeralKey" to ephemeralKey.secret,
            "customer" to customer.id,
         //   "publishableKey" to "setyourpublisherkey_pk_test_51Ng7fCBRTw3h93jKBGjX3ccS3SwIjnI3eTnW3J4tkEtE1vuznZHH1mHx6I4T0ACWjl5DdcoQooZPax58UA8sGNWH0032CMVqJs" // You can get his value from stripe tutorial
        )

        stripePaymentInfo
    }

}