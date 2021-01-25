package com.ml.product.search.di

import androidx.lifecycle.ViewModel
import com.ml.product.search.ui.view.fragment.ProductDetailsFragment
import com.ml.product.search.ui.view.fragment.ProductSearchFragment
import com.ml.product.search.ui.view.fragment.SiteSelectionFragment
import com.ml.product.search.ui.viewmodel.ProductDetailsViewModel
import com.ml.product.search.ui.viewmodel.ProductSearchViewModel
import com.ml.product.search.ui.viewmodel.SiteSelectionViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [ViewModelFactoryModule::class])
    internal abstract fun siteSelectionFragment(): SiteSelectionFragment

    @ContributesAndroidInjector(modules = [ViewModelFactoryModule::class])
    internal abstract fun ProductSearchFragment(): ProductSearchFragment

    @ContributesAndroidInjector(modules = [ViewModelFactoryModule::class])
    internal abstract fun ProductDetailsFragment(): ProductDetailsFragment

    @Binds
    @IntoMap
    @ViewModelKey(SiteSelectionViewModel::class)
    abstract fun bindSiteSelectionViewModel(viewModel: SiteSelectionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductSearchViewModel::class)
    abstract fun bindProductSearchViewModel(viewModel: ProductSearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailsViewModel::class)
    abstract fun bindProductDetailViewModel(viewModel: ProductDetailsViewModel): ViewModel
}
