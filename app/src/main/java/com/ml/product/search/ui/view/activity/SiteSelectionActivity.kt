package com.ml.product.search.ui.view.activity

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.ml.product.search.R
import com.ml.product.search.ui.view.fragment.SiteSelectionFragment

/**
 * Activity class for Sites Screen
 */
class SiteSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.site_message)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.yellow_380)))
        setContentView(R.layout.site_selection_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SiteSelectionFragment.newInstance())
                .commitNow()
        }
    }
}