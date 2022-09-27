import android.util.Log
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * A class to hold all data retried from the location forecast API.
 */
data class weatherDataResponse (@SerializedName("properties") private val locationForecast : locationForecast)
{

	/**
	 * Gets the time for a specific forecast of a given location.
	 *
	 * @param	hour	0 for current and n, for n hours into the future.
	 * @return	The timestamp for a given forecast.
	 */
	fun getTime(hour: Int): Date { return locationForecast.getTimeseries().get(hour).getTime() }


	/**
	 * Gets the air pressure at sea level for the specific location.
	 *
	 * @param	hour	0 for current and n, for n hours into the future.
	 * @return	The air pressure for a given forecast.
	 */
	fun getAirPressure(hour: Int): Double { return locationForecast.getTimeseries().get(hour).getForecastData().getInstantForecast().getCurrentWeather().getAirPressure() }


	/**
	 * Gets the air temperature for the specific location.
	 *
	 * @param	hour	0 for current and n, for n hours into the future.
	 * @return	The air temperature for a given forecast.
	 */
	fun getTemperature(hour: Int): Double { return locationForecast.getTimeseries().get(hour).getForecastData().getInstantForecast().getCurrentWeather().getAirTemperature() }


	/**
	 * Gets the cloud area fraction for the specific location.
	 *
	 * @param	hour	0 for current and n, for n hours into the future.
	 * @return	The cloud area fraction for a given forecast.
	 */
	fun getCloudAreaFraction(hour: Int): Double { return locationForecast.getTimeseries().get(hour).getForecastData().getInstantForecast().getCurrentWeather().getCloudAreaFraction() }


	/**
	 * Gets the high cloud area fraction for the specific location.
	 *
	 * @param	hour	0 for current and n, for n hours into the future.
	 * @return	The high cloud area fraction for a given forecast.
	 */
	fun getHighCloudAreaFraction(hour: Int): Double { return locationForecast.getTimeseries().get(hour).getForecastData().getInstantForecast().getCurrentWeather().getCloudAreaFractionHigh() }


	/**
	 * Gets the low cloud area fraction for the specific location.
	 *
	 * @param	hour	0 for current and n, for n hours into the future.
	 * @return	The low cloud area fraction for a given forecast.
	 */
	fun getLowCloudAreaFraction(hour: Int): Double { return locationForecast.getTimeseries().get(hour).getForecastData().getInstantForecast().getCurrentWeather().getCloudAreaFractionLow() }

	/**
	 * Gets the medium cloud area fraction for the specific location.
	 *
	 * @param	hour	0 for current and n, for n hours into the future.
	 * @return	The medium cloud area fraction for a given forecast.
	 */
	fun getMediumCloudAreaFraction(hour: Int): Double { return locationForecast.getTimeseries().get(hour).getForecastData().getInstantForecast().getCurrentWeather().getCloudAreaFractionMedium() }

	/**
	 * Gets the dew point for the specific location.
	 *
	 * @param	hour	0 for current and n, for n hours into the future.
	 * @return	The dew point temperature for a given forecast.
	 */
	fun getDewPointTemperature(hour: Int): Double { return locationForecast.getTimeseries().get(hour).getForecastData().getInstantForecast().getCurrentWeather().getDewPointTemperature() }


	/**
	 * Gets the fog area fraction for the specific location.
	 *
	 * @param	hour	0 for current and n, for n hours into the future.
	 * @return	The fog area fraction for a given forecast.
	 */
	fun getFogAreaFraction(hour: Int): Double { return locationForecast.getTimeseries().get(hour).getForecastData().getInstantForecast().getCurrentWeather().getFogAreaFraction() }

	/**
	 * Gets the relative humidity for the specific location.
	 *
	 * @param	hour	0 for current and n, for n hours into the future.
	 * @return	The relative humidity for a given forecast.
	 */
	fun getRelativeHumidity(hour: Int): Double { return locationForecast.getTimeseries().get(hour).getForecastData().getInstantForecast().getCurrentWeather().getRelativeHumidity() }


	/**
	 * Gets the UV-index for the specific location.
	 *
	 * @param	hour	0 for current and n, for n hours into the future.
	 * @return	The UV-index for a given forecast.
	 */
	fun getUvIndex(hour: Int): Double { return locationForecast.getTimeseries().get(hour).getForecastData().getInstantForecast().getCurrentWeather().getUvIndex() }

	/**
	 * Gets the wind direction for the specific location.
	 *
	 * @param	hour	0 for current and n, for n hours into the future.
	 * @return	The wind direction for a given forecast.
	 */
	fun getWindDirection(hour: Int): Double { return locationForecast.getTimeseries().get(hour).getForecastData().getInstantForecast().getCurrentWeather().getWindFromDirection() }

	/**
	 * Gets the wind speed for the specific location.
	 *
	 * @param	hour	0 for current and n, for n hours into the future.
	 * @return	The wind speed for a given forecast.
	 */
	fun getWindSpeed(hour: Int): Double { return locationForecast.getTimeseries().get(hour).getForecastData().getInstantForecast().getCurrentWeather().getWindSpeed() }

	/**
	 * Gets the wind gust for the specific location.
	 *
	 * @param	hour	0 for current and n, for n hours into the future.
	 * @return	The wind gust for a given forecast.
	 */
	fun getGustSpeed(hour: Int): Double { return locationForecast.getTimeseries().get(hour).getForecastData().getInstantForecast().getCurrentWeather().getWindSpeedOfGust() }


	/**
	 * Gets the estimated amount of rain for the specific location.
	 *
	 * @param	hour	0 for current and n, for n hours into the future.
	 * @return	The estimated amount of rain for a given forecast.
	 */
	fun precipitationForecast(hour: Int): Double { return locationForecast.getTimeseries().get(hour).getForecastData().getOneHourForecast().getForecastOneHour().getPrecipitationAmount() }


	/**
	 * Gets the max estimated amount of rain for the specific location.
	 *
	 * @param	hour	0 for current and n, for n hours into the future.
	 * @return	The max estimated amount of rain for a given forecast.
	 */
	fun maxPrecipitationForecast(hour: Int): Double { return locationForecast.getTimeseries().get(hour).getForecastData().getOneHourForecast().getForecastOneHour().getPrecipitationAmountMax() }

	/**
	 * Gets the min estimated amount of rain for the specific location.
	 *
	 * @param	hour	0 for current and n, for n hours into the future.
	 * @return	The min estimated amount of rain for a given forecast.
	 */
	fun minPrecipitationForecast(hour: Int): Double {return locationForecast.getTimeseries().get(hour).getForecastData().getOneHourForecast().getForecastOneHour().getPrecipitationAmountMin()}

	/**
	 * Gets the estimated likelihood of rain for the specific location.
	 *
	 * @param	hour	0 for current and n, for n hours into the future.
	 * @return	The estimated likelihood of rain for a given forecast.
	 */
	fun precipitationProbForecast(hour: Int): Double {return locationForecast.getTimeseries().get(hour).getForecastData().getOneHourForecast().getForecastOneHour().getProbabilityOfPrecipitation()}

	/**
	 * Gets the estimated likelihood of thunder the specific location.
	 *
	 * @param	hour	0 for current and n, for n hours into the future.
	 * @return	The estimated likelihood of thunder for a given forecast.
	 */
	fun thunderProbForecast(hour: Int): Double {return locationForecast.getTimeseries().get(hour).getForecastData().getOneHourForecast().getForecastOneHour().getProbabilityOfThunder()}


	/**
	 * Gets the symbol identifier that represents the weather for a given location.
	 *
	 * @param	hour	0 for current and n, for n hours into the future.
	 * @return	The weather symbol representing current weather.
	 */
	fun getSymbol(hour: Int): String { return locationForecast.getTimeseries().get(hour).getForecastData().getOneHourForecast().getForecastOneHourSummary().getSymbol()}


	/**
	 * Gets the relative temp for a given location.
	 *
	 * @param	hour	0 for current and n, for n hours into the future.
	 * @return	The relative temp.
	 */
	fun getRelativeTemp(hour: Int): Double
	{
			val temp = getTemperature(hour)
			val wind = getWindSpeed(hour)
			val HeatIndex = 13.12 + (0.6215 * temp) - (11.37 * Math.pow(wind, -0.16)) + (0.3965 * temp) * Math.pow(wind, 0.16)
			return "%.1f".format(HeatIndex).replace(",", ".").toDouble()
		}
}

 