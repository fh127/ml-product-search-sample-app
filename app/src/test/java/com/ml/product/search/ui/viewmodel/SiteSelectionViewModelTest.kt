package com.ml.product.search.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ml.product.search.TestCoroutineRule
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
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.io.IOException


/**
 * Unit test for [SiteSelectionViewModel]
 */
class SiteSelectionViewModelTest {

    lateinit var viewModel: SiteSelectionViewModel

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
        viewModel = SiteSelectionViewModel(networkUtils, repository)
    }

    @Test
    fun offLine_getSites_failureEvent() = coroutineRule.dispatcher.runBlockingTest {
        Mockito.`when`(networkUtils.isNetworkConnectionEnabled).thenReturn(false)

        viewModel.getSites()

        Mockito.verifyZeroInteractions(repository)
        Assert.assertThat(
            viewModel.sitesLiveData.value,
            CoreMatchers.instanceOf(Results.Failure::class.java)
        )
        val failure = viewModel.sitesLiveData.value as Results.Failure
        Assert.assertEquals(failure.errorTitle, ERROR_TITLE)
        Assert.assertEquals(failure.errorMessage, ERROR_CONNECTION_MESSAGE)
    }

    @Test
    fun successFlow_getSites_SuccessEvent() = coroutineRule.dispatcher.runBlockingTest {
        val sites = Mockito.mock(Sites::class.java)
        Mockito.`when`(networkUtils.isNetworkConnectionEnabled).thenReturn(true)
        Mockito.`when`(repository.getSites()).thenReturn(flowOf(Results.Success(sites)))

        viewModel.getSites()

        Mockito.verify(repository).getSites()
        Assert.assertThat(
            viewModel.sitesLiveData.value,
            CoreMatchers.instanceOf(Results.Success::class.java)
        )
    }

    @Test
    fun throwException_getSites_failureEvent() =
        coroutineRule.dispatcher.runBlockingTest {
            Mockito.`when`(networkUtils.isNetworkConnectionEnabled).thenReturn(true)
            Mockito.`when`(repository.getSites())
                .thenAnswer { flow<Sites> { emit(throw IOException()) } }

            viewModel.getSites()

            Mockito.verify(repository).getSites()
            Assert.assertThat(
                viewModel.sitesLiveData.value,
                CoreMatchers.instanceOf(Results.Failure::class.java)
            )
            val failure = viewModel.sitesLiveData.value as Results.Failure
            Assert.assertEquals(failure.errorTitle, ERROR_TITLE)
            Assert.assertEquals(failure.errorMessage, UNKNOWN_ERROR)
        }

}
