package com.example.coroutinesapp

import com.google.gson.annotations.SerializedName

data class AdviceData (

    @SerializedName("slip")
    val slip: Slip? = null
)

data class Slip (
    @SerializedName("advice")
    val advice: String? = null,

    @SerializedName("id")
    val id: Int? = null
)