import com.google.gson.annotations.SerializedName



//Ignore this, just a data holder.
data class locationForecast (
	@SerializedName("timeseries") private val timeseries : List<locationDataTimeseries>
)

{
	fun getTimeseries(): List<locationDataTimeseries> {return timeseries}
}