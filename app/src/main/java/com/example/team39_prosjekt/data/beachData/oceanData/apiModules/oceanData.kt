import com.google.gson.annotations.SerializedName



//Ignore this, just a data holder.
data class oceanData (
    @SerializedName("instant") private val instant: oceanDataInstant
)
{
	fun getInstantForecast(): oceanDataInstant {return instant}
}