import com.google.gson.annotations.SerializedName



//Ignore this, just a data holder.
data class weatherData (
    @SerializedName("instant") private val instant: locationDataInstant,
    @SerializedName("next_1_hours") private val next_1_hours : nextOneHours,
)
{
	fun getInstantForecast(): locationDataInstant {return instant}
	fun getOneHourForecast(): nextOneHours {return next_1_hours}
}