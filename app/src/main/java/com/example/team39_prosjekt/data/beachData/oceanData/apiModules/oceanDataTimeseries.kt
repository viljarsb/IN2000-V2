import com.google.gson.annotations.SerializedName
import java.util.*



//Ignore this, just a data holder.
data class oceanDataTimeseries (
	@SerializedName("time") private val time: Date,
	@SerializedName("data") private val data: oceanData
)
{
	fun getTime(): Date {return time}
	fun getOceanForecastData(): oceanData {return data}
}