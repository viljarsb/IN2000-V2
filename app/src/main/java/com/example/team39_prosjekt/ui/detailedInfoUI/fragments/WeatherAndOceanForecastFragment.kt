package com.example.team39_prosjekt.ui.detailedInfoUI.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.team39_prosjekt.R
import com.example.team39_prosjekt.data.beachData.beachLocationForecast
import com.example.team39_prosjekt.ui.uiStructures.Adapters.ForecastListAdapter
import com.example.team39_prosjekt.viewmodels.myViewmodel
import kotlinx.android.synthetic.main.fragment_weather_and_ocean_forecast_view.*

class WeatherAndOceanForecastFragment : Fragment() {
    private val viewModel: myViewmodel by activityViewModels()
    companion object { fun newInstance() = WeatherAndOceanForecastFragment() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.selectedBeach.observe(this, Observer {beachObject -> createAndFillRecycler(beachObject)})
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weather_and_ocean_forecast_view, container, false)
    }

    private fun createAndFillRecycler(beach : beachLocationForecast?)
    {
        forecastRecycler.layoutManager = LinearLayoutManager(this.context)
        if (beach != null)
        {
            val adapter = ForecastListAdapter(beach, this.requireActivity())
            forecastRecycler.adapter = adapter
        }
    }
}
