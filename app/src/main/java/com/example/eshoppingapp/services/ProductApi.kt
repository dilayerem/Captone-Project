package com.example.eshoppingapp.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductApi {
    companion object{
        fun getClient(baseUrl: String): Retrofit {
            return  Retrofit.Builder()
                .baseUrl(baseUrl)
                //.addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}