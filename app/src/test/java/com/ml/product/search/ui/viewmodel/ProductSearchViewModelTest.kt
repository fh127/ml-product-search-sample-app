package com.ml.product.search.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ml.product.search.TestCoroutineRule
import com.ml.product.search.mockProduct
import com.ml.product.search.repository.ERROR_CONNECTION_MESSAGE
import com.ml.product.search.repository.ERROR_TITLE
import com.ml.product.search.repository.MLRepository
import com.ml.product.search.repository.UNKNOWN_ERROR
import com.ml.product.search.repository.api.services.NetworkUtils
import com.ml.product.search.repository.model.Results
import com.ml.product.search.repository.model.Sites
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.io.IOException

/**
 * Unit test for [ProductSearchViewModelTest]
 */
class ProductSearchViewModelTest {

    val query = "test"
    val site = "site"
    lateinit var viewModel: ProductSearchViewModel

    @Mock
    lateinit var repository: MLRepository
    lateinit var networkUtils: NetworkUtils

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = TestCoroutineRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        networkUtils = Mockito.mock(NetworkUtils::class.java)
        viewModel = ProductSearchViewModel(networkUtils, repository)
    }

    @Test
    fun offLine_searchProduct_failureEvent() = coroutineRule.dispatcher.runBlockingTest {

        Mockito.`when`(networkUtils.isNetworkConnectionEnabled).thenReturn(false)

        viewModel.setSite(site)
        viewModel.searchProduct(query)

        Mockito.verifyZeroInteractions(repository)
        Assert.assertThat(
            viewModel.productsLiveData.value,
            CoreMatchers.instanceOf(Results.Failure::class.java)
        )
        val failure = viewModel.productsLiveData.value as Results.Failure
        Assert.assertEquals(failure.errorTitle, ERROR_TITLE)
        Assert.assertEquals(failure.errorMessage, ERROR_CONNECTION_MESSAGE)
    }

    @Test
    fun successFlow_searchProduct_successEvent() = coroutineRule.dispatcher.runBlockingTest {
        val query = "test"
        val product = mockProduct()
        Mockito.`when`(networkUtils.isNetworkConnectionEnabled).thenReturn(true)
        Mockito.`when`(repository.searchProduct(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(
                flowOf(
                    Results.Success(
                        listOf(product)
                    )
                )
            )

        viewModel.setSite(site)
        viewModel.searchProduct(query)

        Mockito.verify(repository).searchProduct(anyString(), anyString(), anyString(), anyString())
        Assert.assertThat(
            viewModel.productsLiveData.value,
            CoreMatchers.instanceOf(Results.Success::class.java)
        )
    }

    @Test
    fun throwException_searchProduct_failureEvent() =
        coroutineRule.dispatcher.runBlockingTest {
            val query = "test"
            Mockito.`when`(networkUtils.isNetworkConnectionEnabled).thenReturn(true)
            Mockito.`when`(
                repository.searchProduct(
                    anyString(),
                    anyString(),
                    anyString(),
                    anyString()
                )
            )
                .thenAnswer { flow<Sites> { emit(throw IOException()) } }

            viewModel.setSite(site)
            viewModel.searchProduct(query)

            Mockito.verify(repository)
                .searchProduct(anyString(), anyString(), anyString(), anyString())
            Assert.assertThat(
                viewModel.productsLiveData.value,
                CoreMatchers.instanceOf(Results.Failure::class.java)
            )
            val failure = viewModel.productsLiveData.value as Results.Failure
            Assert.assertEquals(failure.errorTitle, ERROR_TITLE)
            Assert.assertEquals(failure.errorMessage, UNKNOWN_ERROR)
        }


}


