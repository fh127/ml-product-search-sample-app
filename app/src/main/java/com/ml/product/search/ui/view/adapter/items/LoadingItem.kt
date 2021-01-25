package com.ml.product.search.ui.view.adapter.items

import com.ml.product.search.ui.view.adapter.LOADING_TYPE
import com.ml.product.search.ui.view.adapter.ViewType

/**
 * Item class for [com.ml.product.search.ui.view.adapter.delegates.LoadingDelegateAdapter]
 */
class LoadingItem : ViewType {
    override fun getViewType(): Int = LOADING_TYPE
}