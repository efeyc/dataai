package com.eck.dataai.helpers

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.testing.FragmentScenario
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingResource
import java.util.*


class DataBindingIdlingResource : IdlingResource {
    // list of registered callbacks
    private val idlingCallbacks = mutableListOf<IdlingResource.ResourceCallback>()

    // give it a unique id to workaround an espresso bug where you cannot register/unregister
    // an idling resource w/ the same name.
    private val id = UUID.randomUUID().toString()

    // holds whether isIdle is called and the result was false. We track this to avoid calling
    // onTransitionToIdle callbacks if Espresso never thought we were idle in the first place.
    private var wasNotIdle = false

    lateinit var activity: FragmentActivity

    override fun getName() = "DataBinding $id"

    override fun isIdleNow(): Boolean {
        val idle = !getBindings().any { it.hasPendingBindings() }
        @Suppress("LiftReturnOrAssignment")
        if (idle) {
            if (wasNotIdle) {
                // notify observers to avoid espresso race detector
                idlingCallbacks.forEach { it.onTransitionToIdle() }
            }
            wasNotIdle = false
        } else {
            wasNotIdle = true
            // check next frame
            activity.findViewById<View>(android.R.id.content).postDelayed({
                isIdleNow
            }, 16)
        }
        return idle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        idlingCallbacks.add(callback)
    }

    /**
     * Find all binding classes in all currently available fragments.
     */
    private fun getBindings(): List<ViewDataBinding> {

        val fragments = (activity as? FragmentActivity)
            ?.supportFragmentManager
            ?.fragments

        val bindings = fragments?.mapNotNull { it.view?.getBinding() } ?: emptyList()

        val childrenBindings = fragments?.flatMap { it.childFragmentManager.fragments }
            ?.mapNotNull { it.view?.getBinding() } ?: emptyList()

        val recyclerViewBindings = fragments?.flatMap { getRecyclerViewBindings(it.view) } ?: emptyList()

        return bindings + childrenBindings + recyclerViewBindings
    }

    private fun getRecyclerViewBindings(parent: View?): List<ViewDataBinding> {
        (parent as? ViewGroup)?.let {
            for (i in 0 until parent.childCount) {
                val child = parent.getChildAt(i)
                if (child is ViewGroup) {
                    return if (child is RecyclerView) {
                        val childBindings = ArrayList<ViewDataBinding>()
                        for (k in 0 until child.childCount) {
                            // Loop through all children of the RecyclerView to get their bindings and add them to our queue.
                            DataBindingUtil.getBinding<ViewDataBinding>(child.getChildAt(k))?.let {
                                childBindings.add(it)
                            }
                        }
                        childBindings
                    } else {
                        getRecyclerViewBindings(child)
                    }
                }
            }
        }
        return emptyList()
    }

    private fun View.getBinding(): ViewDataBinding? = DataBindingUtil.getBinding(this)

    /**
     * Sets the activity from an [ActivityScenario] to be used from [DataBindingIdlingResource].
     */
    fun DataBindingIdlingResource.monitorActivity(
        activityScenario: ActivityScenario<out FragmentActivity>
    ) {
        activityScenario.onActivity {
            this.activity = it
        }
    }

    /**
     * Sets the fragment from a [FragmentScenario] to be used from [DataBindingIdlingResource].
     */
    fun <T : Fragment> monitorFragment(fragmentScenario: FragmentScenario<T>) {
        fragmentScenario.onFragment {
            this.activity = it.requireActivity()
        }
    }
}