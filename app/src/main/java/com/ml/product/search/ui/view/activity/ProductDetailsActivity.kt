package com.ml.product.search.ui.view.activity

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.ml.product.search.R
import com.ml.product.search.repository.ERROR_PRODUCTS_DETAILS_MESSAGE
import com.ml.product.search.ui.view.adapter.items.ProductItem
import com.ml.product.search.ui.view.fragment.ProductDetailsFragment


const val PRODUCT_ITEM_KEY = "productItem_key"

/**
 * Activity class for Product Details Screen
 */
class ProductDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details_activity)
        supportActionBar?.title = getString(R.string.product_details_message)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.yellow_380)))
        if (savedInstanceState == null) {
            validateExtras()
        }
    }

    private fun validateExtras(){
        intent.extras?.takeIf { it.containsKey(PRODUCT_ITEM_KEY) }
            ?.let {
                supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.container,
                        ProductDetailsFragment.newInstance(
                            it.getSerializable(PRODUCT_ITEM_KEY) as ProductItem
                        )
                    ).commitNow()
            }
            ?: let {
                Toast.makeText(this, ERROR_PRODUCTS_DETAILS_MESSAGE, Toast.LENGTH_LONG).show()
                finish()
            }
    }


    companion object {
        fun newIntent(context: Context, item: ProductItem): Intent {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra(PRODUCT_ITEM_KEY, item)
            return intent
        }
    }
}