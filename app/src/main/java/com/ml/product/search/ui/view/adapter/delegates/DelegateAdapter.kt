package com.ml.product.search.ui.view.adapter.delegates

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ml.product.search.ui.view.adapter.ViewType

/**
 * Interface to bind View Holder for the Adapter implementation
 */
interface DelegateAdapter {

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}