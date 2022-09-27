import com.google.gson.annotations.SerializedName

//Ignore this, just a data holder.
data class oceanForecast (
    @SerializedName("updated_at") private val updatedAt : Number,
    @SerializedName("timeseries") private val timeseries : List<oceanDataTimeseries>)
{

    fun getTimeseries(): List<oceanDataTimeseries> {return timeseries}
}