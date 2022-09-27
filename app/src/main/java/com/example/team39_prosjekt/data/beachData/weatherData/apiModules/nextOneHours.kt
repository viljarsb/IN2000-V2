import com.example.team39_prosjekt.data.weatherdata.weatherDataApiModules.detailsOneHour
import com.google.gson.annotations.SerializedName



//Ignore this, just a data holder.
data class nextOneHours (

	@SerializedName("summary") private val summary : summary,
	@SerializedName("details") private val details : detailsOneHour
)
{
	fun getForecastOneHour(): detailsOneHour {return details}
	fun getForecastOneHourSummary(): summary {return summary}
}