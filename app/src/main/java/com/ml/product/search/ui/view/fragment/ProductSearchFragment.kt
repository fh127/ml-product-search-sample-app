package com.ml.product.search.ui.view.fragment

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ml.product.search.R
import com.ml.product.search.repository.model.Results
import com.ml.product.search.ui.view.activity.ProductDetailsActivity
import com.ml.product.search.ui.view.activity.SITE_KEY
import com.ml.product.search.ui.view.adapter.AdapterHelper
import com.ml.product.search.ui.view.adapter.LOADING_TYPE
import com.ml.product.search.ui.view.adapter.PRODUCT_TYPE
import com.ml.product.search.ui.view.adapter.ViewType
import com.ml.product.search.ui.view.adapter.delegates.LoadingDelegateAdapter
import com.ml.product.search.ui.view.adapter.delegates.OnClickDelegateListener
import com.ml.product.search.ui.view.adapter.delegates.ProductDelegateAdapter
import com.ml.product.search.ui.view.adapter.items.ProductItem
import com.ml.product.search.ui.view.hideSoftKeyboard
import com.ml.product.search.ui.view.showErrorMessage
import com.ml.product.search.ui.viewmodel.ProductSearchViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.product_search_fragment.*
import javax.inject.Inject

/**
 * Fragment class for Product Search screen
 */
class ProductSearchFragment : Fragment(), OnClickDelegateListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ProductSearchViewModel
    private lateinit var adapterHelper: AdapterHelper
    private var searchView: SearchView? = null
    private var queryTextListener: SearchView.OnQueryTextListener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.product_search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ProductSearchViewModel::class.java)

        viewModel.productsLiveData.observe(viewLifecycleOwner, Observer { results ->
           handleResults(results)
        })

        arguments?.getString(SITE_KEY)?.let {
            viewModel.setSite(it)
        }
    }

    override fun onResume() {
        super.onResume()
        hideSoftKeyboard(activity)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        setupSearchView(menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onClick(item: ViewType) {
        startActivity(ProductDetailsActivity.newIntent(context!!, item as ProductItem))
    }

    private fun setupSearchView(menu: Menu) {
        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = searchItem.actionView as SearchView
        searchView?.let {

            it.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))

            // set queryText listener
            queryTextListener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean = true
                override fun onQueryTextSubmit(query: String): Boolean {
                    if (query.isNotEmpty()) {
                        adapterHelper.reload()
                        viewModel.searchProduct(query)
                    }
                    return true
                }
            }
            it.setOnQueryTextListener(queryTextListener)

            //set onClose listener
            it.setOnCloseListener {
                it.clearFocus()
                adapterHelper.removeItems()
                false
            }
        }
    }

    private fun initView() {
        productRecyclerView.layoutManager = LinearLayoutManager(context)
        initAdapter()
    }

    private fun initAdapter() {
        adapterHelper = AdapterHelper()
        adapterHelper.registerDelegate(LOADING_TYPE, LoadingDelegateAdapter())
        adapterHelper.registerDelegate(PRODUCT_TYPE, ProductDelegateAdapter(this))
        adapterHelper.build()
        productRecyclerView.adapter = adapterHelper.adapter
    }

    private fun handleResults(results: Results<List<ProductItem>>) {
        hideSoftKeyboard(activity)
        adapterHelper.removeItems()
        when (results) {
            is Results.Success -> {
                adapterHelper.setItems(results.value)
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
        fun newInstance(site: String): ProductSearchFragment = ProductSearchFragment().apply {
            arguments = Bundle().apply {
                putString(SITE_KEY, site)
            }
        }
    }
}
