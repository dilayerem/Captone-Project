package com.example.eshoppingapp.models

import com.google.gson.annotations.SerializedName

data class CrudResponse(
    @SerializedName("status") var status:Int?=0,
    @SerializedName("message") var message: String?
)
