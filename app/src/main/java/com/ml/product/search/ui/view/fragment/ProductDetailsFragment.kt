package com.ml.product.search.ui.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ml.product.search.R
import com.ml.product.search.repository.model.Product
import com.ml.product.search.repository.model.Results
import com.ml.product.search.ui.toProduct
import com.ml.product.search.ui.toProductItemDetails
import com.ml.product.search.ui.view.activity.PRODUCT_ITEM_KEY
import com.ml.product.search.ui.view.adapter.AdapterHelper
import com.ml.product.search.ui.view.adapter.LOADING_TYPE
import com.ml.product.search.ui.view.adapter.PRODUCT_TYPE
import com.ml.product.search.ui.view.adapter.delegates.LoadingDelegateAdapter
import com.ml.product.search.ui.view.adapter.delegates.ProductDetailsDelegateAdapter
import com.ml.product.search.ui.view.adapter.items.ProductItem
import com.ml.product.search.ui.view.showErrorMessage
import com.ml.product.search.ui.viewmodel.ProductDetailsViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.product_details_fragment.*
import javax.inject.Inject

/**
 * Fragment class for Product Search screen
 */
class ProductDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ProductDetailsViewModel
    private lateinit var adapterHelper: AdapterHelper

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.product_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ProductDetailsViewModel::class.java)
        viewModel = ViewModelProvider(this).get(ProductDetailsViewModel::class.java)
        viewModel.productDetailsLiveData.observe(viewLifecycleOwner, Observer { results ->
            showResults(results)
        })
        arguments?.getSerializable(PRODUCT_ITEM_KEY)?.let {
            viewModel.getDetails((it as ProductItem).toProduct())
        }
    }


    private fun initView() {
        productDetailsRecyclerView.layoutManager = LinearLayoutManager(context)
        initAdapter()
    }

    private fun initAdapter() {
        adapterHelper = AdapterHelper()
        adapterHelper.registerDelegate(LOADING_TYPE, LoadingDelegateAdapter())
        adapterHelper.registerDelegate(PRODUCT_TYPE, ProductDetailsDelegateAdapter())
        adapterHelper.build()
        productDetailsRecyclerView.adapter = adapterHelper.adapter
    }

    private fun showResults(results: Results<List<Product>>) {
        when (results) {
            is Results.Success -> {
                adapterHelper.setItems(results.value.toProductItemDetails())
            }
            is Results.Failure -> {
                results.run {
                    showErrorMessage(
                        context!!,
                        errorTitle,
                        errorMessage
                    )
                }
            }
        }
    }

    companion object {
        fun newInstance(item: ProductItem): ProductDetailsFragment =
            ProductDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(PRODUCT_ITEM_KEY, item)
                }
            }
    }

}
