package com.example.eshoppingapp.services
import com.example.eshoppingapp.services.CanerApi.BASE_URL

class ApiUtil {
    companion object{
        fun getProductInterface():ProductInterface {
            return ProductApi.getClient(BASE_URL).create(ProductInterface::class.java)
        }
    }
}