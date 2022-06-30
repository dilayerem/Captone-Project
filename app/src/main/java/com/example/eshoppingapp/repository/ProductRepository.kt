package com.example.eshoppingapp.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.eshoppingapp.models.CrudResponse
import com.example.eshoppingapp.models.ProductModel
import com.example.eshoppingapp.services.ApiUtil
import com.example.eshoppingapp.services.ProductInterface
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository(context:Context) {
    var productList= MutableLiveData<List<ProductModel>>()
    var newProductList= MutableLiveData<List<ProductModel>>()
    var productBasketList = MutableLiveData<List<ProductModel>>()
    //var categoryList= MutableLiveData<List<ProductModel>>()
    private val productInterface: ProductInterface = ApiUtil.getProductInterface()
    var productAddedToBasket = MutableLiveData<CrudResponse>()

    fun products() {
        productInterface.products().enqueue(object : Callback<List<ProductModel>> {
            override fun onResponse(call: Call<List<ProductModel>>, response: Response<List<ProductModel>>) {
                response.body()?.let {
                    productList.value = it
                }
            }
            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
                t.localizedMessage?.toString()?.let { Log.e("Product display failed.", it) }
            }
        })
    }

    fun newProducts() {
        productInterface.newProducts(5,"desc").enqueue(object : Callback<List<ProductModel>> {
            override fun onResponse(call: Call<List<ProductModel>>, response: Response<List<ProductModel>>) {
                response.body()?.let {
                    newProductList.value = it
                }
            }
            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
                t.localizedMessage?.toString()?.let { Log.e("Product display failed.", it) }
            }
        })
    }

    fun basketProducts() {
        FirebaseAuth.getInstance().currentUser?.uid?.let { uid->
            productInterface.basketProducts(uid).enqueue(object : Callback<List<ProductModel>> {
                override fun onResponse(call: Call<List<ProductModel>>, response: Response<List<ProductModel>>) {
                    response.body()?.let {
                        productBasketList.value = it
                    }
                }
                override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
                    t.localizedMessage?.toString()?.let { Log.e("Product display failed.", it) }
                }
            })
        }
    }

    fun addToBag(productModel: ProductModel) {

        FirebaseAuth.getInstance().currentUser?.uid?.let { uid->
            productInterface.addToBag(
                uid,
                productModel.title.orEmpty(),
                productModel.price.orEmpty(),
                productModel.description.orEmpty(),
                productModel.category.orEmpty(),
                productModel.image.orEmpty(),
                productModel.rate?: 0.0,
                productModel.count?: 0,
                productModel.sale_state?: 0
            ).enqueue(object : Callback<CrudResponse> {
                override fun onResponse(call: Call<CrudResponse>, response: Response<CrudResponse>) {
                    response.body()?.let {
                        productAddedToBasket.value = it
                    }
                }
                override fun onFailure(call: Call<CrudResponse>, t: Throwable) {
                    t.localizedMessage?.toString()?.let { Log.e("Product display failed.", it) }
                }
            })
        }
    }

    fun deleteProductsFromBasket(id: Int) {
        productInterface.deleteFromBasket(id).enqueue(object:Callback<CrudResponse>{
            override fun onResponse(call: Call<CrudResponse>, response: Response<CrudResponse>) {

            }
            override fun onFailure(call: Call<CrudResponse>, t: Throwable) {
                t.localizedMessage?.toString()?.let { Log.e("Product delete failed.", it) }
            }

        })
    }

    fun clearBasket() {

    }
}

