package com.ml.product.search.ui.view.adapter.delegates

import com.ml.product.search.ui.view.adapter.ViewType

/**
 * Interface to implement generic onclick event from Delegate Adapters
 */
interface OnClickDelegateListener {
    fun onClick(item: ViewType)
}