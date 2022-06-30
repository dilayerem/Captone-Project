package com.example.eshoppingapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eshoppingapp.models.ProductModel
import com.example.eshoppingapp.repository.ProductRepository

class MainPageViewModel(context: Context):ViewModel() {
    private var productRepository= ProductRepository(context)

    private var _productList= MutableLiveData<List<ProductModel>>()
    val productList: LiveData<List<ProductModel>>
        get()=_productList

    private var _newproductList= MutableLiveData<List<ProductModel>>()
    val newproductList: LiveData<List<ProductModel>>
        get()=_newproductList

    private var _isProductAddedBag = MutableLiveData<Boolean>()
    val isProductAddedBag: LiveData<Boolean>
        get() = _isProductAddedBag
    init{
        getProducts()
        getNewProducts()
    }
    private fun getProducts(){
        productRepository.products()
        _productList=productRepository.productList
    }
    private fun getNewProducts(){
        productRepository.newProducts()
        _newproductList=productRepository.newProductList
    }
}