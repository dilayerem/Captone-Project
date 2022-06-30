package com.example.eshoppingapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eshoppingapp.models.CrudResponse
import com.example.eshoppingapp.models.ProductModel
import com.example.eshoppingapp.repository.ProductRepository

class ProductsDetailViewModel(context: Context):ViewModel() {

    private val productRepository = ProductRepository(context)

    private var _productAddedToBasket = MutableLiveData<CrudResponse>()
    val productAddedToBasket: LiveData<CrudResponse>
        get() = _productAddedToBasket

    fun addProductsToBasket(productModel: ProductModel) {
        productRepository.addToBag(productModel)
        _productAddedToBasket = productRepository.productAddedToBasket
    }

}