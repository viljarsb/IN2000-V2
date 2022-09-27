package com.example.team39_prosjekt.data.beachData

import weatherDataResponse
import com.example.team39_prosjekt.data.beachData.oceanData.oceanDataResponse
import java.util.*
import kotlin.math.roundToInt

/**
 * Represents a beach with its corresponding ocean and weather forecast data.
 * A an instance of this class will hold a oceanDataResponse and a weatherDataResponse.
 */
data class beachLocationForecast(private val name: String, private val oceanData: oceanDataResponse?, private val weatherData: weatherDataResponse?)
{

    /**
     * Checks if the beach has any data at all, either weather and/or ocean forecast data.
     *
     * @return  A boolean value to indicate if beach is valid, only false if oceanData and weatherData is null.
     */
    fun isValid() : Boolean { return !(oceanData == null && weatherData == null) }


    /**
     * Gets the name of the beach.
     *
     * @return  The name of the beach.
     */
    fun getName() : String { return name }


    /**
     * Gets the timestamp of a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours into the future.
     * @return  A timestamp.
     */
    fun getForecastTime(hour: Int) : Date? { return weatherData?.getTime(hour)}


    /* === Methods to retrieve data about weather forecasts === */


    /**
     * Gets a summary icon of the weather, example "rainy", "cloudy".
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The symbol identifier.
     */
    fun getSymbol(hour: Int): String {
        val symbol = weatherData?.getSymbol(hour)
        if(symbol == null) {return "N/A"}
        return symbol
    }


    /**
     * Gets the air-pressure for a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The air-pressure.
     */
    fun getAirPressure(hour: Int) : String
    {
        val airPressure = weatherData?.getAirPressure(hour)

        if(airPressure == null) {return "N/A"}
        return "$airPressure" + "hPa"
    }


    /**
     * Gets the air-pressure for a certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The air-pressure as a double?.
     */
    fun getAirPressureNumerical(hour: Int) : Double?
    {
        return weatherData?.getAirPressure(hour)
    }


    /**
     * Gets the air temperature for a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The air temp as a string.
     */
    fun getTemp(hour: Int): String {
        val temp = weatherData?.getTemperature(hour)
        if(temp == null) { return "N/A"}
        return "$temp°"
    }

    /**
     * Gets the air temperature for a certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The air temp as a double?.
     */
    fun getTempNumerical(hour: Int): Double? {
        return weatherData?.getTemperature(hour)
    }

    /**
     * Gets the cloud cover for a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The cloud cover.
     */
    fun getCloudCover(hour: Int): String {
        val cover = weatherData?.getCloudAreaFraction(hour)
        if(cover == null) {return "N/A"}
        return "$cover%"
    }

    /**
     * Gets the cloud cover for a certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The cloud cover as a double.
     */
    fun getCloudCoverNumerical(hour: Int): Double? {
        return weatherData?.getCloudAreaFraction(hour)
    }

    /**
     * Gets the low cloud cover for a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The low cloud cover.
     */
    fun getLowCloudCover(hour: Int): String {
        val cover = weatherData?.getLowCloudAreaFraction(hour)
        if(cover == null) {return "N/A"}
        return "$cover%"
    }

    /**
     * Gets the low cloud cover for a certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The low cloud cover as a double?.
     */
    fun getLowCloudCoverNumerical(hour: Int): Double? {
        return weatherData?.getLowCloudAreaFraction(hour)
    }

    /**
     * Gets the medium cloud cover for a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The cloud cover.
     */
    fun getMediumCloudCover(hour: Int): String {
        val cover = weatherData?.getMediumCloudAreaFraction(hour)
        if(cover == null) {return "N/A"}
        return "$cover%"
    }

    /**
     * Gets the medium cloud cover for a certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The cloud cover as a double?.
     */
    fun getMediumCloudCoverNumerical(hour: Int): Double? {
        return weatherData?.getMediumCloudAreaFraction(hour)
    }

    /**
     * Gets the high cloud cover for a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The high cloud cover.
     */
    fun getHighCloudCover(hour: Int): String {
        val cover = weatherData?.getHighCloudAreaFraction(hour)
        if(cover == null) {return "N/A"}
        return "$cover%"
    }

    /**
     * Gets the high cloud cover for a certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The cloud cover as a double?.
     */
    fun getHighCloudCoverNumerical(hour: Int): Double? {
        return weatherData?.getHighCloudAreaFraction(hour)
    }

    /**
     * Gets the dew point temperature a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The dew point temperature.
     */
    fun getDewPointTemp(hour: Int): String {
        val temp = weatherData?.getDewPointTemperature(hour)
        if(temp == null) {return "N/A"}
        return "$temp°"
    }

    /**
     * Gets the high cloud cover for a certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The cloud cover as a double?.
     */
    fun getDewPointTempNumerical(hour: Int): Double? {
        return weatherData?.getDewPointTemperature(hour)
    }

    /**
     * Gets the fog fraction a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The fog fraction.
     */
    fun getFogFraction(hour: Int): String {
        val fog = weatherData?.getFogAreaFraction(hour)
        if(fog == null) {return "N/A"}
        return "$fog" + "µm"
    }

    /**
     * Gets the fog fraction a certain forecast (hour). as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The fog fraction as a double?.
     */
    fun getFogFractionNumerical(hour: Int): Double? {
        return weatherData?.getFogAreaFraction(hour)
    }

    /**
     * Gets the relative humidity a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The humidity.
     */
    fun getRelativeHumidity(hour: Int): String {
        val humidity = weatherData?.getRelativeHumidity(hour)
        if(humidity == null) {return "N/A"}
        return "$humidity%"
    }

    /**
     * Gets the relative humidity a certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The humidity as a dobule?.
     */
    fun getRelativeHumidityNumerical(hour: Int): Double? {
        return weatherData?.getRelativeHumidity(hour)
    }

    /**
     * Gets the UV-index a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The UV.
     */
    fun getUv(hour: Int): String {
        val uv = weatherData?.getUvIndex(hour)
        if(uv == null) {return "N/A"}
        return "$uv"
    }

    /**
     * Gets the UV-index humidity a certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The UV as a dobule?.
     */
    fun getUvNumerical(hour: Int): Double? {
        return weatherData?.getUvIndex(hour)
    }

    /**
     * Gets the wind direction a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The wind direction.
     */
    fun getWindDirection(hour: Int): String {
        val windDir = weatherData?.getWindDirection(hour)
        if(windDir == null) {return "N/A"}
        return "$windDir"
    }

    /**
     * Gets the wind direction a certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The wind direction as a double?.
     */
    fun getWindDirectionNumerical(hour: Int): Double? {
        return weatherData?.getWindDirection(hour)
    }

    /**
     * Gets the wind speed a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The wind speed.
     */
    fun getWindSpeed(hour: Int): String {
        val wind = weatherData?.getWindSpeed(hour)
        if(wind == null) {return "N/A"}
        return "$wind" + "m/s"
    }

    /**
     * Gets the wind speed a certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The wind speed as a double?.
     */
    fun getWindSpeedNumerical(hour: Int): Double? {
        return weatherData?.getWindSpeed(hour)
    }

    /**
     * Gets the wind gust a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The wind gust.
     */
    fun getGustSpeed(hour: Int): String {
        val wind = weatherData?.getGustSpeed(hour)
        if(wind == null) {return "N/A"}
        return "$wind" + "m/s"
    }

    /**
     * Gets the wind gust a certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The wind gust as a double?.
     */
    fun getGustSpeedNumerical(hour: Int): Double? {
        return weatherData?.getGustSpeed(hour)
    }

    /**
     * Gets the rain for a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The rain.
     */
    fun getRain(hour: Int): String {
        val rain = weatherData?.precipitationForecast(hour)
        if(rain == null) {return "N/A"}
        return "$rain" + "mm"
    }

    /**
     * Gets the rain a certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The rain as a double?.
     */
    fun getRainNumerical(hour: Int): Double? {
        return weatherData?.getGustSpeed(hour)
    }

    /**
     * Gets the min rain for a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The min rain.
     */
    fun getMinRain(hour: Int): String {
        val rain = weatherData?.minPrecipitationForecast(hour)
        if(rain == null) {return "N/A"}
        return "$rain" + "mm"
    }

    /**
     * Gets the min rain a certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The min rain as a double?.
     */
    fun getMinRainNumerical(hour: Int): Double? {
        return weatherData?.minPrecipitationForecast(hour)
    }

    /**
     * Gets the max rain for a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The max rain.
     */
    fun getMaxRain(hour: Int): String {
        val rain = weatherData?.maxPrecipitationForecast(hour)
        if(rain == null) {return "N/A"}
        return "$rain" + "mm"
    }

    /**
     * Gets the max rain a certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The max rain as a double?.
     */
    fun getMaxRainNumerical(hour: Int): Double? {
        return weatherData?.maxPrecipitationForecast(hour)
    }

    /**
     * Gets the probability of rain for a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The max rain.
     */
    fun getRainProb(hour: Int): String {
        val prob = weatherData?.precipitationForecast(hour)
        if(prob == null) {return "N/A"}
        return "$prob%"
    }

    /**
     * Gets the probability of rain for certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The probability of rain as a double?.
     */
    fun getRainProbNumerical(hour: Int): Double? {
        return weatherData?.precipitationProbForecast(hour)
    }

    /**
     * Gets the probability of thunder for a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The probability of thunder.
     */
    fun getThunderProb(hour: Int): String {
        val prob = weatherData?.thunderProbForecast(hour)
        if(prob == null) {return "N/A"}
        return "$prob%"
    }

    /**
     * Gets the probability of thunder for certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The probability of thunder as a double?.
     */
    fun getThunderProbNumerical(hour: Int): Double? {
        return weatherData?.thunderProbForecast(hour)
    }


    /**
     * Gets the relative temperature for a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The relative temperature.
     */
    fun getRelativeTemp(hour: Int): String {
        val temp = weatherData?.getRelativeTemp(hour)
        if(temp == null) {return "N/A"}
        return "$temp°"
    }

    /**
     * Gets the relative temperature for certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The relative temperature as a double?.
     */
    fun getRelativeTempNumerical(hour: Int): Double? {
        return weatherData?.getRelativeTemp(hour)
    }



    /* === Methods to retrieve data about ocean forecasts === */



    /**
     * Gets the wave height for a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The waveheight.
     */
    fun getWaveHeight(hour: Int): String {
        val waveHeight = oceanData?.getWaveHeight(hour)
        if(waveHeight == null) {return "N/A"}
        return "$waveHeight" + "m"
    }

    /**
     * Gets the wave height for a certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The waveheight as a double?.
     */
    fun getWaveHeightNumerical(hour: Int): Double? {
        return oceanData?.getWaveHeight(hour)
    }

    /**
     * Gets the wave direction for a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The wave direction.
     */
    fun getWaveDirection(hour: Int): String {
        val waveDir = oceanData?.getWaveDirection(hour)
        if(waveDir == null) {return "N/A"}
        return "$waveDir"
    }

    /**
     * Gets the wave direction for a certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The wave direction as a double?.
     */
    fun getWaveDirectionNumerical(hour: Int): Double? {
        return oceanData?.getWaveDirection(hour)
    }

    /**
     * Gets the sea-current direction for a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The wave direction.
     */
    fun getSeaCurrentDirection(hour: Int): String {
        val currentDir = oceanData?.getSeaCurrentDirection(hour)
        if(currentDir == null) {return "N/A"}
        return "$currentDir"
    }

    /**
     * Gets the sea-current direction for a certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The sea current direction as a double?.
     */
    fun getSeaCurrentDirectionNumerical(hour: Int): Double? {
        return oceanData?.getSeaCurrentDirection(hour)
    }

    /**
     * Gets the sea-current speed for a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The sea-current speed.
     */
    fun getSeaCurrentSpeed(hour: Int): String {
        val current = oceanData?.getSeaCurrentSpeed(hour)
        if(current == null) {return "N/A"}
        return "$current" + "m/s"
    }

    /**
     * Gets the sea-current speed for a certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The sea-current speed as a double?.
     */
    fun getSeaCurrentSpeedNumerical(hour: Int): Double? {
        return oceanData?.getSeaCurrentSpeed(hour)
    }

    /**
     * Gets the ocean temp for a certain forecast (hour).
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The ocean temp speed.
     */
    fun getOceanTemp(hour: Int): String {
        val temp = oceanData?.getSeaTemperature(hour)
        if(temp == null) {return "N/A"}
        return "$temp" + "°"
    }

    /**
     * Gets the ocean temp for a certain forecast (hour) as a numerical value.
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The ocean temp as a double?.
     */
    fun getOceanTempNumerical(hour: Int): Double? {
        return oceanData?.getSeaTemperature(hour)
    }



    /* === Some methods to return descriptions of conditions === */



    /**
     * Gets a description of wind conditions right now.
     *
     * @param   BaufortScale    A wind scale.
     * @return  The description of wind.
     */
    fun getWindDescription(BaufortScale: List<String>): String {
        val wind = weatherData?.getWindSpeed(0)
        val windGust = weatherData?.getGustSpeed(0)
        val windDirection = weatherData?.getWindDirection(0)
        val windKh = if (wind != null) (wind * 3.6) else return "Ingen data tilgjengelig"
        var baufort = ""
        when(windKh.toInt()) {
            in 0..1 -> baufort = BaufortScale[0]
            in 1..5 -> baufort = BaufortScale[1]
            in 6..11 -> baufort = BaufortScale[2]
            in 12..19 -> baufort = BaufortScale[3]
            in 20..28 -> baufort = BaufortScale[4]
            in 29..38 -> baufort = BaufortScale[5]
            in 39..49 -> baufort = BaufortScale[6]
            in 50..51 -> baufort = BaufortScale[7]
            in 62..74 -> baufort = BaufortScale[8]
            in 75..88 -> baufort = BaufortScale[9]
            in 89..102 -> baufort = BaufortScale[10]
            in 103..117 -> baufort = BaufortScale[11]
            in 118..Int.MAX_VALUE -> baufort = BaufortScale[12]
            else -> {baufort = "N/A"}
        }


        var windDir = "N/A"
        when(windDirection?.roundToInt()) {
            in 337..359 -> windDir = "nord"
            in 0..23 -> windDir = "nord"
            in 23..68 -> windDir = "nordøst"
            in 68..113 -> windDir = "øst"
            in 113..158 -> windDir = "sørøst"
            in 158..203 -> windDir = "sør"
            in 203..248 -> windDir = "sørvest"
            in 248..293 -> windDir = "vest"
            in 293..337 -> windDir = "nordvest"
        }
        return "Det er for øyeblikket $baufort fra $windDir med vindstryke på ${wind}m/s, vindkast med hastighet opp mot ${windGust}m/s."
    }


    /**
     * Gets a description of ocean conditions right now.
     *
     * @return  Description of ocean conditions.
     */
    fun getOceanDescription(): String {
        val waveHeight = oceanData?.getWaveHeight(0)
        val waveDirection = oceanData?.getWaveDirection(0)
        val seaCurrentSpeed = oceanData?.getSeaCurrentSpeed(0)
        val seaTemperature = oceanData?.getSeaTemperature(0)
        val seaCurrentDirection = oceanData?.getSeaCurrentDirection(0)

        var waveHeightStr = waveHeight.toString()
        var seaTemperatureStr = seaTemperature.toString()
        var seaCurrentSpeedStr = seaCurrentSpeed.toString()

        if(waveHeight == null && seaTemperature == null && seaCurrentDirection == null) {return "Ingen data tilgjengelig"}
        if(waveHeight == null) {waveHeightStr = " N/A "}
        if(seaTemperature == null) {seaTemperatureStr = " N/A "}
        if(seaCurrentSpeed == null) {seaCurrentSpeedStr = "N/A"}

        return "Badevannet holder for øyeblikket en temperatur på ${seaTemperatureStr}°" +
                ", en bølgehøyde ${waveHeightStr}m" +
                " og en undervannsstrøm med en styrke på ${seaCurrentSpeedStr}m/s"
    }


    /**
     * Gets a description of rain conditions right now.
     *
     * @return  Description of rain conditions.
     */
    @Suppress("DEPRECATION")
    fun getRainDescription(): String {
        val rainForeMax = weatherData?.maxPrecipitationForecast(0)
        val rainForeMin = weatherData?.minPrecipitationForecast(0)
        val rainProb = weatherData?.precipitationProbForecast(0)

        if(rainForeMax == null && rainForeMin == null && rainProb == null) {
            return "Ingen data tilgjengelig"
        }

        val rainMinStr : String
        val rainMaxStr : String
        val rainProbStr : String

        if(rainForeMin == null) {rainMinStr = " N/A "}
        else {rainMinStr = rainForeMin.toString()}
        if(rainForeMax == null) {rainMaxStr = " N/A "}
        else {rainMaxStr = rainForeMax.toString()}
        if(rainProb == null) {rainProbStr = " N/A "}
        else {rainProbStr = rainProb.toString()}

        var rainForecast = ""
        if((rainForeMin != null) && (rainForeMin > 0.0))
            rainForecast = ", det er forventet minimum ${rainMinStr}mm og maksimalt ${rainMaxStr}mm nedbør"
        return "Sannsynligheten for nedbør er ${rainProbStr}%" +
                rainForecast
    }


    /**
     * Gets a description of cloud conditions right now.
     *
     * @return  Description of cloud conditions.
     */
    fun getCloudDescription(): String {
        val cloudCover = weatherData?.getCloudAreaFraction(0)
        if(cloudCover == null) {return "Ingen data tilgjengelig"}
        var toReturn = ""
        when(cloudCover.roundToInt()) {
            in 0..20 -> toReturn = "Minimalt skydekke på $cloudCover%"
            in 20..40 -> toReturn = "Lavt skydekke på $cloudCover%"
            in 40..60 -> toReturn = "Medium skydekke på $cloudCover%"
            in 60..80 -> toReturn = "Høyt skydekke på $cloudCover%"
            in 80..100 -> toReturn = "Totalt skydekke på $cloudCover%"
        }
        val lowClouds = weatherData?.getLowCloudAreaFraction(0)
        val mediumClouds = weatherData?.getMediumCloudAreaFraction(0)
        val highClouds = weatherData?.getHighCloudAreaFraction(0)
        return toReturn.plus("\nLave skyer (≈ 2000m) - $lowClouds%" +
                "\nMellomhøye skyer (≈2000m-6000m) - $mediumClouds%" +
                "\nHøye skyer  (6000m +) - $highClouds%")
    }



    /**
     * Gets a description of UV conditions.
     *
     * @return  Description of ocean conditions.
     */
    fun getUvDescription(UvIndexes: List<String>): String {
        val UV = weatherData?.getUvIndex(0)
        if(UV == null) {return "Ingen data tilgjengelig"}

        val UvIndex = "UV-indeks: $UV"
        val warning = "\nOvereksponering for sol kan gi alvorlige skader på hud og øyne. \n\nHusk at lett til medium skydekke gir liten til ingen beskyttelse."
        when(UV.toDouble().toInt()) {
            in 0..2 -> return UvIndex + UvIndexes[0]
            in 3..5 -> return UvIndex + UvIndexes[1] + "\n$warning"
            in 5..7 -> return UvIndex + UvIndexes[2] + "\n$warning"
            in 8..10 -> return UvIndex + UvIndexes[3] + "\n$warning"
            in 11..15 -> return UvIndex + UvIndexes[4] + "\n$warning"
        }
        return "will never get here."
    }


    /**
     * Gets a description of ocean conditions right now.
     *
     * @return  Description of ocean conditions.
     */
    fun safeToSwim(): String {
        val seaTemperature = oceanData?.getSeaTemperature(0)
        val seaCurrentSpeed = oceanData?.getSeaCurrentSpeed(0)
        val totalWaveHeight = oceanData?.getWaveHeight(0)

        var tooCold = false
        var tooStrongCurrent = false
        var tooHighWaves = false

        if(seaTemperature == null && seaCurrentSpeed == null && totalWaveHeight == null) {
            return "Ingen data tilgjengelig"
        }

        var temp = ""
        if(seaTemperature != null) {
            if(seaTemperature < 15) { tooCold = true}
            when(seaTemperature.roundToInt()) {
                in -10..0 -> { temp = temp.plus("Badevannet er svært kladt($seaTemperature°).\n") }
                in 0..10 -> { temp = temp.plus("Badevannet er kaldt($seaTemperature°).\n") }
                in 10..12 -> { temp = temp.plus("Badevannet er relativt kaldt($seaTemperature°).\n") }
                in 12..15 -> { temp = temp.plus("Badevannet holder en kjølig temperatur($seaTemperature°).\n") }
                in 15..18 -> { temp = temp.plus("Badevannet holder en behagelig temperatur($seaTemperature°).\n") }
                in 18..30 -> { temp = temp.plus("Badevannet er varmt($seaTemperature°)°.\n") }
            }
        }

        var current = ""
        if(seaCurrentSpeed != null) {
            if(seaCurrentSpeed >= 4) { tooStrongCurrent = true }
            when(seaCurrentSpeed.roundToInt()) {
                in 0..1 -> { current = current.plus("Svak undervannsstrøm på $seaCurrentSpeed.\n") }
                in 1..2 -> { current = current.plus("Svak-medium undervannsstrøm på $seaCurrentSpeed.\n") }
                in 2..3 -> { current = current.plus("Medium-sterk undervannsstrøm på $seaCurrentSpeed.\n") }
                in 3..4 -> { current = current.plus("Sterk undervannsstrøm på $seaCurrentSpeed.\n") }
                in 4..100 -> { current = current.plus("Svært sterk undervannsstrøm på $seaCurrentSpeed.\n") }
            }
        }


        var waveHeight = ""
        if(totalWaveHeight != null) {
            if(totalWaveHeight >= 2) { tooHighWaves = true }
            when(totalWaveHeight.roundToInt()) {
                in 0..1 -> { waveHeight = waveHeight.plus("Lave bølger med en høyde på $totalWaveHeight.")}
                in 1..2 -> { waveHeight = waveHeight.plus("Relativt høye bølger med en høyde på $totalWaveHeight.")}
                in 2..15 -> { waveHeight = waveHeight.plus("Svært høye bølger med en høyde på $totalWaveHeight.")}
            }
        }

        var toReturn = "Det er fine badeforhold for øyeblikket. \n"
        if(tooCold || tooStrongCurrent || tooHighWaves) {
            toReturn = "Bading er ikke anbefalt for øyeblikket."
            if(tooCold) { toReturn = toReturn.plus("\n$temp") }
            if(tooStrongCurrent) { toReturn = toReturn.plus("\n$current") }
            if(tooHighWaves) { toReturn = toReturn.plus("\n$waveHeight") }
        }

        if(seaTemperature == null || seaCurrentSpeed == null || totalWaveHeight == null) {
            toReturn = toReturn.plus("Anbefalingen er ikke utfyllende, da ikke alle faktorer kunne tas i betrakning.\nVurder forholdene individuelt.")
        }
        return toReturn
    }


    /**
     * Gets the rain interval for a certain forecast (hour)
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The rain interval.
     */
    fun getRainInterval(hour: Int) : String
    {
        val minPrecipitation = weatherData?.minPrecipitationForecast(hour)
        val maxPrecipitation = weatherData?.maxPrecipitationForecast(hour)

        if(minPrecipitation != null && maxPrecipitation != null)
            if(minPrecipitation > 0.0)
                return "$minPrecipitation - $maxPrecipitation" + "mm"
        return "N/A"
    }


    /**
     * Gets the wind + gust for a certain forecast (hour)
     *
     * @param   hour    0 for current, n for n hours from now.
     * @return  The wind+gust.
     */
    fun getWindAndGust(hour: Int) : String
    {
        val wind = weatherData?.getWindSpeed(hour)
        val gust = weatherData?.getGustSpeed(hour)

        if(wind != null && gust != null)
            return "$wind ($gust) m/s"
        return "N/A"
    }
}