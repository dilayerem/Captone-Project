package com.example.eshoppingapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.eshoppingapp.R
import com.example.eshoppingapp.adapter.ProductsBasketAdapter
import com.example.eshoppingapp.databinding.FragmentProductsBasketBinding
import com.example.eshoppingapp.viewmodel.ProductsBasketViewModel

class ProductsBasketFragment : Fragment() {

    private var _binding: FragmentProductsBasketBinding? =null
    private val productsBasketBinding get() = _binding!!

    private val viewModel by lazy { ProductsBasketViewModel(requireContext()) }

    private val productsBasketAdapter by lazy { ProductsBasketAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_products_basket, container, false)
        return productsBasketBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        with(productsBasketBinding) {

            productsBasketRecyclerView.setHasFixedSize(true)

            productsBasketAdapter.onRemoveBasketClick = {
                viewModel.deleteProductsFromBasket(it)
            }
        }
    }

    private fun initObservers() {

        with(productsBasketBinding) {

            with(viewModel) {

                productsBasket.observe(viewLifecycleOwner) { list ->
                    if (list.isNotEmpty()){
                        emptybasket.visibility = View.GONE
                    }
                    else{
                        emptybasket.visibility = View.VISIBLE
                    }
                    productsBasketAdapter.updateList(list)
                    productsBasketRecyclerView.adapter = productsBasketAdapter
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}