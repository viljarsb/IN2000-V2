package com.example.team39_prosjekt.ui.uiStructures.Adapters

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import com.example.team39_prosjekt.data.beachData.beachLocationForecast
import com.example.team39_prosjekt.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.gmaps_infowindow.view.*

/**
 * Marker info window adapter for MapViewFragment.
 */
class InfoWindowAdapter(val context: Context?) : GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(p0: Marker?): View {
        var infoView = (context as Activity).layoutInflater.inflate(R.layout.gmaps_infowindow, null)
        var infoWindow: beachLocationForecast? = p0?.tag as beachLocationForecast?



        infoView.title.text = infoWindow?.getName()
        infoView.airTemp.text = infoWindow?.getTemp(0)
        infoView.waterTemp.text = infoWindow?.getOceanTemp(0)
        infoView.windSpeed.text = infoWindow?.getWindSpeed(0)


        val iconResource = this.context.resources.getIdentifier(infoWindow?.getSymbol(0), "drawable", this.context.packageName)
        Log.d("icon", "iconResource: $iconResource")
        Log.d("icon", "packageName: ${this.context.packageName}")
        infoView.weatherSymbol.setImageResource(iconResource)

        return infoView
    }

    override fun getInfoWindow(p0: Marker?): View? {
        return getInfoContents(p0)
    }
}