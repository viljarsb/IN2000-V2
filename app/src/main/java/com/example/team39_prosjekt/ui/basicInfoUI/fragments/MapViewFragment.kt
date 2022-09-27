package com.example.team39_prosjekt.ui.basicInfoUI.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.team39_prosjekt.R
import com.example.team39_prosjekt.data.beachData.beachLocationForecast
import com.example.team39_prosjekt.data.roomDB.beachLocationData
import com.example.team39_prosjekt.ui.uiStructures.Adapters.InfoWindowAdapter
import com.example.team39_prosjekt.viewmodels.myViewmodel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.mapview_fragment.*


class MapViewFragment : Fragment(), OnMapReadyCallback {
    companion object {
        fun newInstance() = MapViewFragment()
    }

    private lateinit var gMap: GoogleMap
    private lateinit var coordinatesList: List<beachLocationData>
    private val viewModel: myViewmodel by activityViewModels()


    /**
     *  Called once the fragment is started for the first time.
     *  Starts to observe the view-model to act once the view-model
     *  has gotten data about coordinates for the beaches.
     *
     *  When the view-model receives the data, the observer will fetch
     *  the map and save the locations in a list.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mapView.onCreate(savedInstanceState)
        mapView.onResume()

        viewModel.googleMapsCoordinates.observe(this, Observer {
            mapView.getMapAsync(this)
            coordinatesList = it
        })

        viewModel.beachDataRefresh.observe(this, Observer { if(it) { showRefreshUI() }
        })
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.mapview_fragment, container, false)
    }


    /**
     * Callback function that executes once the map is loaded.
     * Sets some constraints to the map, eg. limit it to Oslo area etc.
     * Then starts to observe the view-model and waits for data about beaches
     * before calling createGmap() once data arrives, observer will continue
     * to update the map with new data as long as application is alive.
     *
     * @param   m   A google map object.
     */
    override fun onMapReady(m: GoogleMap) {
        if(isDetached)
            return

        gMap = m
        //Limit Camera to Oslo
        val osloBorder = LatLngBounds(LatLng(59.626977, 10.411806), LatLng(59.955998, 10.908517))
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(osloBorder.center, 10f))
        gMap.setLatLngBoundsForCameraTarget(osloBorder)
        gMap.setMinZoomPreference(10.0f)

        //Update the map with new data once it arrives.
        viewModel.locationForecasts.observe(this, Observer { locationForecasts ->
            gMap.clear()
            createGmap(locationForecasts)
        })
    }


    /**
     * Setups up the map with markers for each beach, custom infoWindows and
     * infoWindow onClick listeners to enable the user to go to detailed view
     * from the map rather than the recycler-view in the ListViewFragment.
     *
     * @param   locationForecasts   A list of BeachLocationForecasts for every beach.
     */
    private fun createGmap(locationForecasts: List<beachLocationForecast>) {

        for (i in 0..(coordinatesList.size - 1)) {
            val beachForecast = locationForecasts.get(i)

            //Find the coordinates for the corresponding beach.
            var beachLocationData = coordinatesList.get(0)
            for (item in coordinatesList) {
                if (item.name == beachForecast.getName()) {
                    beachLocationData = item
                    break
                }
            }

            val beachPos = LatLng(beachLocationData.lat.toDouble(), beachLocationData.lon.toDouble())

            //Color markers relative to water temp
            val temp = beachForecast.getOceanTempNumerical(0)?.toFloat()
            if (temp != null) {
                val colorHue: Float
                if (temp <= 0) {
                    colorHue = 180f
                } else if (temp <= 15) {
                    colorHue = 240.0f - (60.0f * temp / 15.0f)
                } else if (temp <= 25) {
                    colorHue = 60.0f - (60.0f * temp / 25.0f)
                } else {
                    colorHue = 0f
                }
                //Set custom infoWindow
                val infoWindow = InfoWindowAdapter(context)
                gMap.setInfoWindowAdapter(infoWindow)
                val marker = gMap.addMarker(
                    MarkerOptions().position(beachPos)
                        .title(beachForecast.getName())
                        .icon(BitmapDescriptorFactory.defaultMarker(colorHue))
                )
                marker.tag = locationForecasts.get(i)
            }
        }

        gMap.setOnInfoWindowClickListener {
            for (i in 0..(coordinatesList.size - 1)) {
                if (coordinatesList[i].name.equals(it.title)) {
                    viewModel.selectedBeach.postValue(locationForecasts.get(i))
                    viewModel.currentDisplayedUI.postValue(1)
                }
            }
        }

        mapView.visibility = View.VISIBLE
        progressBarMap.visibility = View.INVISIBLE
        metAttributeMap.visibility = View.INVISIBLE
    }


    /**
     * Utility function to display a UI that indicates to the user that a refresh is happening.
     */
    private fun showRefreshUI() {
        progressBarMap.visibility = View.VISIBLE
        metAttributeMap.visibility = View.VISIBLE
        mapView.visibility = View.INVISIBLE
    }
}
