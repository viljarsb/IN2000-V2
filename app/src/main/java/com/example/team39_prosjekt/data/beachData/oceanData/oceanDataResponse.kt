package com.example.team39_prosjekt.data.beachData.oceanData
import com.google.gson.annotations.SerializedName
import oceanForecast
import java.util.*


/**
 * A class to hold all data retried from the ocean forecast API.
 */
data class oceanDataResponse (@SerializedName("properties") private val oceanForecast : oceanForecast)
{

    /**
     * Gets the time for a specific forecast of a given location.
     *
     * @param	hour	0 for current and n, for n hours into the future.
     * @return	The timestamp for a given forecast.
     */
    fun getTime(hour: Int): Date { return oceanForecast.getTimeseries().get(hour).getTime() }


    /**
     * Gets the wave height for the specific location.
     *
     * @param	hour	0 for current and n, for n hours into the future.
     * @return	The wave height for a given forecast.
     */
    fun getWaveHeight(hour: Int): Double? { return oceanForecast.getTimeseries().get(hour).getOceanForecastData().getInstantForecast().getCurrentOceanConditions().getWaveHeight()}


    /**
     * Gets the wave direction for the specific location.
     *
     * @param	hour	0 for current and n, for n hours into the future.
     * @return	The wave direction for a given forecast.
     */
    fun getWaveDirection(hour: Int): Double? { return oceanForecast.getTimeseries().get(hour).getOceanForecastData().getInstantForecast().getCurrentOceanConditions().getWaveDirection() }


    /**
     * Gets the sea current direction for the specific location.
     *
     * @param	hour	0 for current and n, for n hours into the future.
     * @return	The sea current direction for a given forecast.
     */
    fun getSeaCurrentDirection(hour: Int): Double? { return oceanForecast.getTimeseries().get(hour).getOceanForecastData().getInstantForecast().getCurrentOceanConditions().getSeaWaterDirection() }


    /**
     * Gets the sea current speed for the specific location.
     *
     * @param	hour	0 for current and n, for n hours into the future.
     * @return	The sea current speed for a given forecast.
     */
    fun getSeaCurrentSpeed(hour: Int): Double? { return oceanForecast.getTimeseries().get(hour).getOceanForecastData().getInstantForecast().getCurrentOceanConditions().getSeaWaterSpeed() }


    /**
     * Gets the sea temperature for the specific location.
     *
     * @param	hour	0 for current and n, for n hours into the future.
     * @return	The sea temperature for a given forecast.
     */
    fun getSeaTemperature(hour: Int): Double? {return oceanForecast.getTimeseries().get(hour).getOceanForecastData().getInstantForecast().getCurrentOceanConditions().getOceanTemperature() }
}



