package com.ml.product.search.ui.view.adapter.items

import com.ml.product.search.ui.view.adapter.SITE_TYPE
import com.ml.product.search.ui.view.adapter.ViewType

/**
 * Item class for [com.ml.product.search.ui.view.adapter.delegates.SiteDelegateAdapter]
 */
data class SiteItem(val name: String, val id: String) : ViewType {
    override fun getViewType(): Int = SITE_TYPE
}
