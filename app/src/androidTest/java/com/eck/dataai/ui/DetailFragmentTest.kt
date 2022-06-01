package com.eck.dataai.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.eck.dataai.di.appModule
import com.eck.dataai.helpers.DataBindingIdlingResource
import com.eck.dataai.helpers.TestConstants
import com.eck.dataai.helpers.TestConstants.ACCOUNT_ID
import com.eck.dataai.helpers.TestConstants.PRODUCT_ID
import com.eck.dataai.models.api.RevenueData
import com.eck.dataai.models.api.UnitsData
import com.eck.dataai.ui.common.ItemViewModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.dsl.module
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


@MediumTest
@RunWith(AndroidJUnit4::class)
class DetailFragmentTest {

    private val dataBindingIdlingResource = DataBindingIdlingResource()
    private val mockViewModel: MainViewModel = mock(MainViewModel::class.java)
    private val mockItemViewModel = MutableLiveData<List<ItemViewModel>>()

    @Before
    fun setUp() {
        `when`(mockViewModel.productSales).thenReturn(mockItemViewModel)

        loadKoinModules(module {
            appModule()
            viewModel { mockViewModel }
        })

        val args = DetailFragmentArgs(ACCOUNT_ID, PRODUCT_ID)
        val bundle = args.toBundle()
        val fragmentScenario = launchFragmentInContainer<DetailFragment>(fragmentArgs = bundle)
        dataBindingIdlingResource.monitorFragment(fragmentScenario)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

    @Test
    fun testFragmentVisible() {
        onView(withId(com.eck.dataai.R.id.recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun testRenderingWorks() {
        mockItemViewModel.postValue(listOf(TestConstants.uiSales))
        onView(withText(TestConstants.COUNTRY)).check(matches(isDisplayed()))
        onView(withText(getUnitsDescription(TestConstants.unitsData))).check(matches(isDisplayed()))
        onView(withText(getRevenueDescription(TestConstants.revenueData))).check(matches(isDisplayed()))
    }


    private fun getUnitsDescription(unitsData: UnitsData): String {
        val sb = StringBuilder()
        sb.append(String.format("%d downloads", unitsData.product.downloads))
        sb.append("\n")
        sb.append(String.format("%d updates", unitsData.product.updates))
        sb.append("\n")
        sb.append(String.format("%d sales", unitsData.iap.sales))
        return sb.toString()
    }

    private fun getRevenueDescription(revenueData: RevenueData): String {
        val sb = StringBuilder()
        sb.append(String.format("%s from sales", revenueData.iap.sales.toString()))
        sb.append("\n")
        sb.append(String.format("%s from refunds", revenueData.iap.refunds.toString()))
        return sb.toString()
    }
}
