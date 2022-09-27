import com.google.gson.annotations.SerializedName
import java.util.*



//Ignore this, just a data holder.
data class locationDataTimeseries (
	@SerializedName("time") private val time: Date,
	@SerializedName("data") private val data: weatherData
)
{
	fun getTime(): Date {return time}
	fun getForecastData(): weatherData {return data}
}