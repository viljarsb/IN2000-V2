package com.example.team39_prosjekt.data.network

import android.util.Log
import com.example.team39_prosjekt.data.beachData.oceanData.oceanDataResponse
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.coroutines.*
import com.google.gson.Gson

/**
     *  Requests data from the OceanForecast 2.0 by MET for a given beach.
     *
     *  @param  lat     the latitude of the location/beach to fetch data for.
     *  @param  lon     the longitude of the location/beach to fetch data for.
     *  @return         An OceanDataResponse with data for the given beach or null.
     */
    suspend fun fetchOceanData(lat: String, lon: String): oceanDataResponse?
    {
        val URL = "https://in2000-apiproxy.ifi.uio.no/weatherapi/oceanforecast/2.0/complete?lat=$lat&lon=$lon"
        val gsonConverter = Gson()
        var data: oceanDataResponse? = null
        lateinit var response: String

        try
        {
            response = Fuel.get(URL).awaitString()
            data = gsonConverter.fromJson(response, oceanDataResponse::class.java)
        }

        catch (exception: FuelError)
        {
            Log.e("OF-API-ERROR", "Could not fetch data from OF-API - location: $lat $lon " + exception.message)
        }

        return data
    }




