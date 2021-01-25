package com.ml.product.search.ui.view.fragment

import android.annotation.SuppressLint
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
import com.ml.product.search.repository.model.Results
import com.ml.product.search.repository.model.Sites
import com.ml.product.search.ui.toSiteItems
import com.ml.product.search.ui.view.activity.ProductSearchActivity
import com.ml.product.search.ui.view.adapter.AdapterHelper
import com.ml.product.search.ui.view.adapter.LOADING_TYPE
import com.ml.product.search.ui.view.adapter.SITE_TYPE
import com.ml.product.search.ui.view.adapter.ViewType
import com.ml.product.search.ui.view.adapter.delegates.LoadingDelegateAdapter
import com.ml.product.search.ui.view.adapter.delegates.OnClickDelegateListener
import com.ml.product.search.ui.view.adapter.delegates.SiteDelegateAdapter
import com.ml.product.search.ui.view.adapter.items.SiteItem
import com.ml.product.search.ui.view.showErrorMessage
import com.ml.product.search.ui.viewmodel.SiteSelectionViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.site_selection_fragment.*
import javax.inject.Inject


/**
 * Fragment class for Sites Screen
 */
class SiteSelectionFragment : Fragment(), OnClickDelegateListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SiteSelectionViewModel
    private lateinit var adapterHelper: AdapterHelper

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.site_selection_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        bindViewModel()
        loadSites()
        sitesSwipeRefreshLayout.setOnRefreshListener {
            loadSites()
        }
    }

    override fun onClick(item: ViewType) {
        val site = (item as SiteItem).id
        startActivity(ProductSearchActivity.newIntent(context!!, site))
    }

    private fun bindViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(SiteSelectionViewModel::class.java)

        viewModel.sitesLiveData.observe(viewLifecycleOwner, Observer { results ->
            handleResults(results)
        })
    }

    @SuppressLint("ResourceAsColor")
    private fun initView() {
        sitesRecyclerView.layoutManager = LinearLayoutManager(context)
        sitesSwipeRefreshLayout.setColorSchemeResources(R.color.yellow_380)
        initAdapter()
    }


    private fun initAdapter() {
        adapterHelper = AdapterHelper()
        adapterHelper.registerDelegate(LOADING_TYPE, LoadingDelegateAdapter())
        adapterHelper.registerDelegate(SITE_TYPE, SiteDelegateAdapter(this))
        adapterHelper.build()
        sitesRecyclerView.adapter = adapterHelper.adapter
    }


    private fun hideSwipeRefreshLoader() {
        sitesSwipeRefreshLayout.isRefreshing = false
    }

    private fun loadSites() {
        adapterHelper.reload()
        viewModel.getSites()
    }

    private fun handleResults(results: Results<Sites>) {
        hideSwipeRefreshLoader()
        adapterHelper.removeItems()
        when (results) {
            is Results.Success -> {
                adapterHelper.setItems(results.value.toSiteItems())
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
        fun newInstance() =
            SiteSelectionFragment()
    }

}

