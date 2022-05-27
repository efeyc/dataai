package com.eck.dataai.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.eck.dataai.R
import com.eck.dataai.di.appModule
import com.eck.dataai.helpers.DataBindingIdlingResource
import com.eck.dataai.helpers.TestConstants
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
class MainFragmentTest {

    private val dataBindingIdlingResource = DataBindingIdlingResource()
    private val mockViewModel: MainViewModel = mock(MainViewModel::class.java)
    private val mockItemViewModel = MutableLiveData<List<ItemViewModel>>()

    @Before
    fun setUp() {
        `when`(mockViewModel.data).thenReturn(mockItemViewModel)

        loadKoinModules(module {
            appModule()
            viewModel { mockViewModel }
        })

        val fragmentScenario = launchFragmentInContainer<MainFragment>()
        dataBindingIdlingResource.monitorFragment(fragmentScenario)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

    @Test
    fun testFragmentVisible() {
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun testRenderingWorks() {
        mockItemViewModel.postValue(listOf(TestConstants.uiProduct))
        onView(withText(TestConstants.PRODUCT_NAME)).check(matches(isDisplayed()))
        onView(withText(TestConstants.CATEGORY + " | " + TestConstants.PUBLISHER)).check(matches(isDisplayed()))
        onView(withId(R.id.root)).check(matches(isClickable()))
    }
}
