package com.example.team39_prosjekt.ui.uiStructures.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.team39_prosjekt.data.beachData.beachLocationForecast
import com.example.team39_prosjekt.R
import kotlinx.android.synthetic.main.beach_card.view.wind
import kotlinx.android.synthetic.main.forecast_card.view.*
import java.util.*

/**
 * Adapter for 48-hour forecast in detailed view.
 */
class ForecastListAdapter(val currentBeach: beachLocationForecast, val context: Context) : RecyclerView.Adapter<ForecastListAdapter.MyViewHolder>()
{
    override fun getItemCount(): Int
    {
        return 48
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(
            R.layout.forecast_card,
                parent,
                false
            )
        )
    }


    /**
     * Binds information to the views in the recycler view.
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val date = formatDate(currentBeach.getForecastTime(position+1))

        val airTemp = currentBeach.getTemp(position+1)
        val windSpeed = currentBeach.getWindSpeed(position+1)
        val rain = currentBeach.getRain(position+1)

        holder.time.text = date
        holder.temp.text = airTemp
        holder.wind.text = windSpeed
        holder.rain.text = rain

        val iconResource = this.context.resources.getIdentifier(currentBeach.getSymbol(position+1), "drawable", this.context.packageName)
        Glide.with(holder.card).load(iconResource).into(holder.symbol)
    }


    /**
     * Utility function to format date into a more desirable format.
     */
    private fun formatDate(dateInput: Date?): String
    {
        val dateSplit = dateInput.toString().split(" ")
        var day = ""
        when(dateSplit.get(0))
        {
            "Mon" -> day = "Mandag"
            "Tue" -> day = "Tirsdag"
            "Wed" -> day = "Onsdag"
            "Thu" -> day = "Torsdag"
            "Fri" -> day = "Fredag"
            "Sat" -> day = "Lørdag"
            "Sun" -> day = "Søndag"
        }

        var month = ""
        when(dateSplit.get(1))
        {
            "Jan" -> month = "januar"
            "Feb" -> month = "februar"
            "Mar" -> month = "mars"
            "Apr" -> month = "april"
            "May" -> month = "mai"
            "Jun" -> month = "juni"
            "Jul" -> month = "juli"
            "Aug" -> month = "august"
            "Sep" -> month = "september"
            "Oct" -> month = "oktober"
            "Nov" -> month = "november"
            "Dec" -> month = "desember"
        }

        var date: String = dateSplit.get(2)
        when(date.get(0))
        {
            '0' -> date = date.get(1).toString()
        }

        return "$day $date. $month ${dateSplit.get(3).dropLast(3)}"
    }


    class MyViewHolder(cardview: View) : RecyclerView.ViewHolder(cardview)
    {
        val card = cardview
        val time = card.time
        val temp = card.temp
        val wind = card.wind
        val rain = card.rain
        val symbol = card.symbol
    }
}

