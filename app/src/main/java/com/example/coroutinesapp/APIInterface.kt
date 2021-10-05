package com.example.coroutinesapp

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET("advice")
    fun doGetListResources(): Call<AdviceData?>?
}