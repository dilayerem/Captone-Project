package com.example.eshoppingapp.services

import com.example.eshoppingapp.models.CrudResponse
import com.example.eshoppingapp.models.ProductModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductInterface {
    @GET("get_products.php")
    fun products(): Call<List<ProductModel>>

    @POST("get_products.php")
    @FormUrlEncoded
    fun newProducts(
        @Field("limit") limit: Int,
        @Field("sort") sort: String
    ): Call<List<ProductModel>>

    @POST("add_to_bag.php")
    @FormUrlEncoded
    fun addToBag(
        @Field("user") user:String,
        @Field("title") title:String,
        @Field("price") price:String,
        @Field("description") description:String,
        @Field("category") category:String,
        @Field("image") image:String,
        @Field("rate") rate:Double,
        @Field("count") count:Int,
        @Field("sale_state") saleState:Int
    ):Call<CrudResponse>

    @POST("get_bag_products_by_user.php")
    @FormUrlEncoded
    fun basketProducts(
        @Field("user") user:String
    ):Call<List<ProductModel>>

    @POST("delete_from_bag.php")
    @FormUrlEncoded
    fun deleteFromBasket(
        @Field("id") id:Int
    ):Call<CrudResponse>



}