package com.ml.product.search.ui.view.adapter

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import com.ml.product.search.ui.view.adapter.delegates.DelegateAdapter
import java.lang.IllegalStateException
import java.util.ArrayList

/**
 * This is a base class to implement common logic for [RecyclerView.Adapter] using delegates
 */
class BaseAdapter(private val delegateAdapters: SparseArrayCompat<DelegateAdapter>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: ArrayList<ViewType> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val delegateAdapter: DelegateAdapter? = delegateAdapters.get(viewType)
        return delegateAdapter?.run {
            onCreateViewHolder(parent)
        } ?: throw  IllegalStateException("onCreateViewHolder can't be null")
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position))
            ?.onBindViewHolder(holder, items[position])
    }

    override fun getItemViewType(position: Int) = items[position].getViewType()
}

