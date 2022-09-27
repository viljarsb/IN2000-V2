import com.google.gson.annotations.SerializedName



//Ignore this, just a data holder.
data class oceanDataInstant (@SerializedName("details") private val details : oceanDataDetails)
{
	fun getCurrentOceanConditions(): oceanDataDetails
	{
		return details
	}
}