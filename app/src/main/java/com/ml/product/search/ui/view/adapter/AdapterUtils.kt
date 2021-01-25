package com.ml.product.search.ui.view.adapter

import com.ml.product.search.repository.model.Product
import com.ml.product.search.repository.model.Sites
import com.ml.product.search.ui.view.adapter.items.ProductItem
import com.ml.product.search.ui.view.adapter.items.SiteItem

// View Types Constants
const val SITE_TYPE = 100
const val LOADING_TYPE = 102
const val PRODUCT_TYPE = 103
const val NO_INDEX = -1

/**
 * Ext Fun to set items for Adapters
 * @param items
 */
fun BaseAdapter.setItems(items: List<ViewType>) {
    this.items.clear()
    this.items.addAll(items)
    notifyDataSetChanged()
}

/**
 * Ext Fun to add item for specific position for Adapters
 * @param position
 * @param item
 * @return true if is success or other case is false
 */
fun BaseAdapter.addItem(position: Int, item: ViewType): Boolean {
    return if (items.indexOf(item) == NO_INDEX) {
        items.add(position, item)
        notifyItemInserted(position)
        true
    } else false
}

/**
 * Ext Fun to add item for Adapters
 * @param item
 * @return true if is success or other case is false
 */
fun BaseAdapter.addItem(item: ViewType): Boolean {
    return if (items.indexOf(item) == NO_INDEX) {
        items.add(item)
        notifyDataSetChanged()
        true
    } else false
}

/**
 * Ext Fun to remove item for Adapters
 * @param item
 * @return true if is success or other case is false
 */
fun BaseAdapter.removeItem(item: ViewType): Boolean {
    val position = items.indexOf(item)
    return if (position != NO_INDEX) {
        items.remove(item)
        notifyItemRemoved(position)
        true
    } else false
}

/**
 * Ext Fun to remove item by type for Adapters
 * @param type
 * @return true if is success or other case is false
 */
fun BaseAdapter.removeItemByType(type: Int): Boolean {
    val item = items.takeIf { it.isNotEmpty() }
        ?.find { it.getViewType() == type }
    return item?.run {
        removeItem(this)
    } ?: false
}

/**
 * Ext Fun to remove all items for Adapters
 * @return true if is success or other case is false
 */
fun BaseAdapter.removeItems(): Boolean {
    return items.takeIf { it.isNotEmpty() }
        ?.run {
            val size = items.size
            items.clear()
            notifyItemRangeRemoved(0, size)
            true
        } ?: false
}




