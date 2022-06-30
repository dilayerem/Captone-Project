package com.example.eshoppingapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.eshoppingapp.R
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.example.eshoppingapp.databinding.FragmentProductsDetailBinding
import com.example.eshoppingapp.viewmodel.ProductsDetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso

class ProductsDetailFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentProductsDetailBinding? =null
    private val binding get() = _binding!!
    private val viewModel by lazy{ProductsDetailViewModel(requireContext())}
    private val args: ProductsDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_products_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product = args.productDetail

        binding.apply {
            productsDetail = product
            Picasso.get().load(product.image).into(imageView4)
        }

        binding.addtochart.setOnClickListener {
           viewModel.addProductsToBasket(product)
        }

        viewModel.productAddedToBasket.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it.message , Toast.LENGTH_SHORT).show()
        }
    }
}