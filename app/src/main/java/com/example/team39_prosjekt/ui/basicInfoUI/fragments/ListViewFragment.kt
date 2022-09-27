package com.example.team39_prosjekt.ui.basicInfoUI.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.team39_prosjekt.data.beachData.beachLocationForecast
import com.example.team39_prosjekt.R
import com.example.team39_prosjekt.ui.uiStructures.Adapters.ListFragmentAdapter
import com.example.team39_prosjekt.viewmodels.myViewmodel
import kotlinx.android.synthetic.main.listview_fragment.*


class ListViewFragment : Fragment() {
    private val viewModel: myViewmodel by activityViewModels()
    private lateinit var adapter: ListFragmentAdapter

    companion object { fun newInstance() = ListViewFragment() }


    /**
     *  Called once the fragment is started for the first time.
     *  Sets up the recycler view, and the listeners needed to enable filtering of the beaches
     *  displayed in the recyclerview based on search/favorites and the on click listener
     *  for each beach. In addition the function starts to observe view-model state
     *  to decide when to update the recyclerview with new data and when to display the
     *  refreshing UI.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Setup recycler view and the filter listeners for text and favorite
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = ListFragmentAdapter(this.requireActivity(), { BeachLocationForecast -> beachItemClicked(BeachLocationForecast) })
        recyclerView.adapter = adapter

        SearchBox.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

        favouriteFilterButton.setOnClickListener {
            adapter.filterFavourites = !adapter.filterFavourites //toggle
            if (adapter.filterFavourites) {
                favouriteFilterButton.setImageResource(android.R.drawable.btn_star_big_on)
            } else {
                favouriteFilterButton.setImageResource(android.R.drawable.btn_star_big_off)
            }

            adapter.filter.filter(SearchBox.query)
        }

        //Force refresh data on demand with a swipe up in recycler view.
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshRequest()
            swipeRefreshLayout.isRefreshing = false
        }

        // Observes/subscribes to the livedata in the view-model, executed once variable changes.
        viewModel.locationForecasts.observe(viewLifecycleOwner, Observer { updateRecycler(it) })

        viewModel.beachDataRefresh.observe(this, Observer {if(it) { showRefreshUI() } })
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.listview_fragment, container, false)
    }



    /**
     * Notifies the recyclerview-adapter that new data is available by calling adapter.updateData()
     * with the new the new data as argument, so that it can refresh it self with new data.
     * In addition it disables the refresh UI.
     *
     * @param   value   A list of beachLocationForecast objects that hold updated data.
     */
    private fun updateRecycler(value: List<beachLocationForecast>) {
        adapter.updateData(value)

        // Display beaches according to the filter
        adapter.filter.filter(SearchBox.query)

        progressBarList.visibility = View.INVISIBLE
        metAttributeList.visibility = View.INVISIBLE
        recyclerView.visibility = View.VISIBLE
    }


    /**
     * Utility function to display a UI that indicates to the user that a refresh is happening.
     */
    private fun showRefreshUI() {
        progressBarList.visibility = View.VISIBLE
        metAttributeList.visibility = View.VISIBLE
        recyclerView.visibility = View.INVISIBLE
    }


    /**
     * Function to handle onClick events on the beaches displayed in the recycler-view.
     * Checks if the clicked beach has data to show. If no data is available, show a
     * error toast. If there is data to be displayed (ocean and/or weather data) update
     * view-model state to indicate to the main-activity that the users wishes to
     * see detailed information about the clicked beach.
     *
     * @param   beach   A BeachLocationForecast object representing the clicked beach.
     */
    private fun beachItemClicked(beach: beachLocationForecast) {

        /* Make sure the clicked beach actually has data to display.
           If no data is available give a toast with a error.
           This only happens if both LF and OF api gave no data. */
        if (!beach.isValid()) {
            Toast.makeText(this.context, "Kan ikke hente detaljert data.", Toast.LENGTH_SHORT)
                .show()
            return
        }


        /* Save the clicked beach in the view-model, and update the currentDisplayedUI
           value in the view-model, to signal the activity to switch to detailed view. */
        viewModel.selectedBeach.postValue(beach)
        viewModel.currentDisplayedUI.postValue(1)
    }
}
