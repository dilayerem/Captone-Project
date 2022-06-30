package com.example.eshoppingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.eshoppingapp.databinding.AllitemsBinding
import com.example.eshoppingapp.fragments.MainPageFragment
import com.example.eshoppingapp.fragments.MainPageFragmentDirections
import com.example.eshoppingapp.models.ProductModel
import com.squareup.picasso.Picasso

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private val productList = ArrayList<ProductModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            AllitemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    inner class ViewHolder(private var binding: AllitemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductModel) {

            binding.apply {

                allitems = item

                item.image.let {
                    Picasso.get().load(it).into(allitemsImage)
                }
                allitemsImage.setOnClickListener {
                    val action = MainPageFragmentDirections.actionMainPageFragmentToProductsDetailFragment3(item)
                    it.findNavController().navigate(action)
                }
            }
        }
    }

    override fun getItemCount(): Int = productList.size

    fun updateList(list: List<ProductModel>) {
        productList.clear()
        productList.addAll(list)
        notifyDataSetChanged()
    }
}