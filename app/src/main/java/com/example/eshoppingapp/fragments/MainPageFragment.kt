package com.example.eshoppingapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.eshoppingapp.R
import com.example.eshoppingapp.adapter.NewProductsAdapter
import com.example.eshoppingapp.adapter.ProductsAdapter
import com.example.eshoppingapp.databinding.FragmentMainPageBinding
import com.example.eshoppingapp.viewmodel.MainPageViewModel

class MainPageFragment : Fragment() {

    private var _binding: FragmentMainPageBinding? = null
    private val mainPageBinding get() = _binding!!
    private val viewModel by lazy { MainPageViewModel(requireContext()) }

    private val newProductsAdapter by lazy { NewProductsAdapter() }
    private val productsAdapter by lazy { ProductsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_page, container, false)
        return mainPageBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productPuller()

    }

    private fun productPuller() {
        with(mainPageBinding) {
            with(viewModel) {
                productList.observe(viewLifecycleOwner) { list ->
                    allClothesRecycler.apply {
                        setHasFixedSize(true)
                        adapter = productsAdapter.also {
                            it.updateList(list)
                        }
                    }
                }
                newproductList.observe(viewLifecycleOwner) { list ->
                    newClothesRecycler.apply {
                        setHasFixedSize(true)
                        adapter = newProductsAdapter.also {
                            it.updateList(list)
                        }
                    }
                }
            }
        }
    }

}
