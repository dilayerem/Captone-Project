package com.example.eshoppingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eshoppingapp.databinding.FragmentProductsBasketBinding
import com.example.eshoppingapp.databinding.ProductsBasketItemsBinding
import com.example.eshoppingapp.models.ProductModel
import com.squareup.picasso.Picasso

class ProductsBasketAdapter : RecyclerView.Adapter<ProductsBasketAdapter.ProductBasketDesign>(){

    private val productsBasketList= ArrayList<ProductModel>()
    var onRemoveBasketClick: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductBasketDesign {
        val binding =
            ProductsBasketItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductBasketDesign(binding)
    }

    override fun onBindViewHolder(holder: ProductBasketDesign, position: Int) {
        holder.bind(productsBasketList[position])
    }

    inner class ProductBasketDesign(private var binding: ProductsBasketItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(productBasket: ProductModel) {

            binding.apply {

                productBasketModel = productBasket

                productBasket.image.let {
                    Picasso.get().load(it).into(productBasketImageView)
                }

                minusproductbutton.setOnClickListener {
                    productBasket.id?.let {
                        onRemoveBasketClick(it)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return productsBasketList.size
    }

    fun updateList(list: List<ProductModel>) {
        productsBasketList.clear()
        productsBasketList.addAll(list)
        notifyDataSetChanged()
    }

}