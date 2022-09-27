package com.example.team39_prosjekt.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.team39_prosjekt.R
import com.example.team39_prosjekt.ui.uiStructures.DetailedBeachFragmentAdapter
import com.example.team39_prosjekt.ui.uiStructures.MainFragmentPagerAdapter
import com.example.team39_prosjekt.viewmodels.myViewmodel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.listview_fragment.*
import kotlinx.android.synthetic.main.mapview_fragment.*


class MainActivity : AppCompatActivity() {
    private val viewModel: myViewmodel by viewModels { myViewmodel.Factory(this.application) }


    /**
     * This is the main activity onCreate that will run once the application
     * starts for the first time. Sets up the UI and observers to observe the
     * state of the view-model.
     *
     * Will automatically change the viewpager if a fragment asks for it.
     * WIll show a update toast if a fragment asks for it.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Connects ViewPager with TabLayout, making the tabs visible.
        tabLayout.setupWithViewPager(viewPager)

        viewModel.currentDisplayedUI.observe(this, Observer { setupFragmentViewpager(it) })

        viewModel.beachDataRefresh.observe(this, Observer { if(it) {showUpdateToast()} })
    }


    /**
     * Called once the OS resumes the application.
     * Checks how old the current data is, and requests new data if data is over an hour old.
     */
    override fun onResume() {
        super.onResume()
        val updatedAt = viewModel.locationDataUpdatedAt
        if ((System.currentTimeMillis() - updatedAt) >= 60 * 6000) {
            viewModel.refreshRequest()
        }
    }


    /**
     * Since we use a single activity application, we have to override back functionality if
     * currently located in the detailed UI, if not use the super implementation.
     */
    override fun onBackPressed() {
        if(viewModel.currentDisplayedUI.value == 1)
        {
            viewModel.currentDisplayedUI.postValue(0)
            return
        }
        super.onBackPressed()
    }


    /**
     * Show a update Toast indicating that the application is refreshing data.
     */
    private fun showUpdateToast() {
        Toast.makeText(this, "Oppdaterer data", Toast.LENGTH_LONG).show()
    }


    /**
     * Utility function that set the current viewpager adapter,
     * effectively deciding if the user sees the basic or detailed UI on their screen.
     *
     * @param   value   A value, either 0 or 1, decides if the user sees the general
     *                  or detailed view.
     */
    private fun setupFragmentViewpager(value: Int) {

        viewPager.currentItem = 0
        val transaction = supportFragmentManager.beginTransaction()
        supportFragmentManager.fragments.forEach {
            transaction.remove(it)
        }

        transaction.commitNow()

        if (value == 0) {
            viewPager.adapter = MainFragmentPagerAdapter(supportFragmentManager)

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

                override fun onTabSelected(tab: TabLayout.Tab) {
                    if (currentFocus != null) {
                        currentFocus!!.hideKeyboard()
                    }
                }

                //The following functions can't be removed since they need to be overridden
                override fun onTabUnselected(tab: TabLayout.Tab) {}
                override fun onTabReselected(tab: TabLayout.Tab) {}
            })
        } else if (value == 1) {
            viewPager.adapter = DetailedBeachFragmentAdapter(supportFragmentManager)
        }
    }


    /**
     * Utility function that hides the keyboard if its open once called.
     */
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}
