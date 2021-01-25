package com.ml.product.search.ui.view.adapter.delegates

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ml.product.search.R
import com.ml.product.search.ui.view.adapter.ViewType
import com.ml.product.search.ui.view.inflate
import kotlinx.android.synthetic.main.loading_item_view.view.*

/**
 * Delegate Adapter for Loading View Type
 */
class LoadingDelegateAdapter(val listener: LoadingAnimaiton? = null) : DelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = ViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.loading_item_view)
    ) {
        fun bind(listener: LoadingAnimaiton? = null) = with(itemView) {
            listener?.let {
                loaderAnimation.addAnimatorListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        it.onFinish()
                    }
                })
            }

        }

    }
}

interface LoadingAnimaiton {
    fun onFinish()
}
