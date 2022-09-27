import com.google.gson.annotations.SerializedName


//Ignore this, just a data holder.
data class oceanDataDetails (
	@SerializedName("sea_surface_wave_from_direction") private val sea_surface_wave_from_direction: Double,
	@SerializedName("sea_surface_wave_height") private val sea_surface_wave_height: Double,
	@SerializedName("sea_water_speed") private val sea_water_speed: Double,
	@SerializedName("sea_water_temperature") private val sea_water_temperature: Double,
	@SerializedName("sea_water_to_direction") private val sea_water_to_direction: Double
)
{
	/* These methods return weather information about an hour forecast */
    //Replaces "," with ".", if not the devices crashes on deviced with non English style seperators (norwegian etc..)
	fun getWaveDirection(): Double {return sea_surface_wave_from_direction}
	fun getWaveHeight(): Double {return sea_surface_wave_height}
	fun getSeaWaterSpeed(): Double {return sea_water_speed}
	fun getOceanTemperature(): Double {return sea_water_temperature}
	fun getSeaWaterDirection(): Double {return sea_water_to_direction}
}

