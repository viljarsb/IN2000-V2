import com.google.gson.annotations.SerializedName



//Ignore this, just a data holder.
data class locationDataInstant (@SerializedName("details") private val details : locationDataDetails)
{
	fun getCurrentWeather(): locationDataDetails
	{
		return details
	}
}