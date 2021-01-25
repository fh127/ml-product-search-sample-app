package com.ml.product.search.ui.view.adapter

import androidx.collection.SparseArrayCompat
import com.ml.product.search.ui.view.POSITION_ZERO
import com.ml.product.search.ui.view.adapter.delegates.DelegateAdapter
import com.ml.product.search.ui.view.adapter.items.LoadingItem

/**
 * This helper class to handle common actions about the
 * adapter in the screens
 */
class AdapterHelper() {

    var adapter: BaseAdapter? = null
        private set

    private val delegateAdapters = SparseArrayCompat<DelegateAdapter>()

    fun registerDelegate(type: Int, delegateAdapter: DelegateAdapter) {
        delegateAdapters.put(type, delegateAdapter)
    }

    fun build() {
        adapter = BaseAdapter(delegateAdapters)
    }

    fun setItems(items: List<ViewType>) {
        adapter?.setItems(items)
    }


    fun reload() {
        adapter?.removeItems()
        adapter?.addItem(POSITION_ZERO, LoadingItem())
    }

    fun removeItems() = adapter?.removeItems()

}
