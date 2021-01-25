package com.ml.product.search.di

import com.ml.product.search.repository.MLRepository
import com.ml.product.search.repository.MLRepositoryImpl
import com.ml.product.search.repository.api.MLApiManager
import com.ml.product.search.repository.api.MLApiManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule {
    @Singleton
    @Provides
    fun providesMLRepository(repositoryImpl: MLRepositoryImpl): MLRepository = repositoryImpl

    @Singleton
    @Provides
    fun providesMLApiManager(apiManagerImpl: MLApiManagerImpl): MLApiManager = apiManagerImpl
}


