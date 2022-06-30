package com.example.eshoppingapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eshoppingapp.models.ProductModel
import com.example.eshoppingapp.repository.ProductRepository

class ProductsBasketViewModel(context:Context): ViewModel() {

    private val productRepository = ProductRepository(context)

    private var _productsBasket = MutableLiveData<List<ProductModel>>()
    val productsBasket: LiveData<List<ProductModel>>
        get() = _productsBasket

    init {
        getProductsBasket()
    }

    private fun getProductsBasket() {
        productRepository.basketProducts()
        _productsBasket = productRepository.productBasketList
    }

    fun deleteProductsFromBasket(id: Int) {
        productRepository.deleteProductsFromBasket(id)
        getProductsBasket()
    }
}