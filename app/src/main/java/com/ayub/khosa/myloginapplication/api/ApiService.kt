package com.ayub.khosa.myloginapplication.api

import com.ayub.khosa.myloginapplication.model.APIResponce
import com.ayub.khosa.myloginapplication.model.APIResponceListCATEGORYS
import com.ayub.khosa.myloginapplication.model.APIResponceListPRODUCTS
import com.ayub.khosa.myloginapplication.model.APIResponceUser
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {


    @FormUrlEncoded
    @POST("ecommerce-website-master/authmobile.php")
    suspend fun getSignup(
        @Field("btn-signup") btn_signup: String,
        @Field("lemail") email: String,
        @Field("lpassword") password: String,
        @Field("lfirstName") firstname: String,
        @Field("llastName") lastname: String
    ): APIResponceUser


    @FormUrlEncoded
    @POST("ecommerce-website-master/authmobile.php")
    suspend fun getforgetpasswordUser(
        @Field("btn-forgetpasswor") forgetpasswor: String,
        @Field("lemail") email: String
    ): APIResponce

    @FormUrlEncoded
    @POST("ecommerce-website/authmobile.php")
    suspend fun getLogin(
        @Field("btn-login") btn_login: String,
        @Field("lemail") email: String,
        @Field("lpassword") password: String
    ): APIResponceUser

    @FormUrlEncoded
    @POST("ecommerce-website/authmobile.php")
    suspend fun is_logged_in(
        @Field("is_logged_in") is_logged_in: String
    ): APIResponce

    @FormUrlEncoded
    @POST("ecommerce-website/authmobile.php")
    suspend fun userlogout(
        @Field("_logout_server") is_logout: String,
        @Field("lemail") email: String
    ): APIResponce

    @FormUrlEncoded
    @POST("/ecommerce-website/authmobile.php")
    suspend fun get_ListPRODUCTS(
        @Field("ListPRODUCTS") listPRODUCTS: String
    ): APIResponceListPRODUCTS

    @FormUrlEncoded
    @POST("ecommerce-website/authmobile.php")
    suspend fun get_ListCATEGORYS(
        @Field("ListCATEGORYS") listCATEGORYS: String
    ): APIResponceListCATEGORYS
}