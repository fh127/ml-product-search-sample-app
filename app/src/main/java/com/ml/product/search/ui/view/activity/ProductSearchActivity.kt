package com.ml.product.search.ui.view.activity

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ml.product.search.R
import com.ml.product.search.repository.SITE_ERROR
import com.ml.product.search.ui.view.fragment.ProductSearchFragment

const val SITE_KEY = "site_key"

/**
 * Activity class for Product Search screen
 */
class ProductSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_search_activity)
        supportActionBar?.title = getString(R.string.product_search_message)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.yellow_380)))
        if (savedInstanceState == null) {
            validateExtras()
        }
    }

    private fun validateExtras() {
        intent.extras?.takeIf { it.containsKey(SITE_KEY) }
            ?.let {
                supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.container,
                        ProductSearchFragment.newInstance(
                            it.getString(SITE_KEY, null)
                        )
                    ).commitNow()
            }
            ?: let {
                Toast.makeText(this, SITE_ERROR, Toast.LENGTH_LONG).show()
                finish()
            }
    }

    companion object {
        fun newIntent(context: Context, site: String): Intent {
            val intent = Intent(context, ProductSearchActivity::class.java)
            intent.putExtra(SITE_KEY, site)
            return intent
        }

    }

}