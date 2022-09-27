
import com.google.gson.annotations.SerializedName



//Ignore this, just a data holder.
data class summary (@SerializedName("symbol_code") private val symbol : String)
{
	fun getSymbol(): String {return symbol}
}