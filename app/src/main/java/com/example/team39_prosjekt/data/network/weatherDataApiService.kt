package com.example.team39_prosjekt.data.network

import weatherDataResponse
import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.coroutines.awaitString
import com.google.gson.Gson


/**
     *  Requests data from the LocationForecast 2.0 by MET for a given beach.
     *
     *  @param  lat     the latitude of the location/beach to fetch data for.
     *  @param  lon     the longitude of the location/beach to fetch data for.
     *  @return         An WeatherDataReponse with data for the given beach or null.
     */
    suspend fun fetchWeatherData(lat: String, lon: String): weatherDataResponse?
    {
        val URL = "https://in2000-apiproxy.ifi.uio.no/weatherapi/locationforecast/2.0/?lat=$lat&lon=$lon"
        val gsonConverter = Gson()
        var data: weatherDataResponse? = null
        lateinit var response: String

        try
        {
            response = Fuel.get(URL).awaitString()
            data = gsonConverter.fromJson(response, weatherDataResponse::class.java)
        }

        catch (exception: FuelError)
        {
            Log.e("LF-API-ERROR", "Could not fetch data from LF-API - location: $lat $lon " + exception.message)
        }
        return data
    }