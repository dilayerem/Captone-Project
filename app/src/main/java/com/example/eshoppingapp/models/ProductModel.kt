package com.example.eshoppingapp.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize

data class ProductModel(
    @SerializedName ("id") var id:Int?=0,
    @SerializedName ("user") var user:String?,
    @SerializedName ("title") var title:String?,
    @SerializedName ("price")  var price:String?,
    @SerializedName ("description") var description:String?,
    @SerializedName ("category") var category:String?,
    @SerializedName ("image") var image:String?,
    @SerializedName ("rate")  var rate:Double?,
    @SerializedName ("count")  var count:Int?,
    @SerializedName ("sale_state") var sale_state:Int?
): Parcelable
