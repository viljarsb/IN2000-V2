package com.example.team39_prosjekt.ui.uiStructures.Adapters

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.team39_prosjekt.data.beachData.beachLocationForecast
import com.example.team39_prosjekt.data.userDataPersistentStorage.Userdata
import com.example.team39_prosjekt.R
import kotlinx.android.synthetic.main.beach_card.view.*
import java.util.*

/**
 * Used for the recycler view of different beaches on the basic UI.
 */
class ListFragmentAdapter(val context: Context, val clickListener: (beachLocationForecast) -> Unit) : RecyclerView.Adapter<ListFragmentAdapter.MyViewHolder>(), Filterable{

    private var beachList = mutableListOf<beachLocationForecast>()
    private var filteredBeachList = beachList
    var filterFavourites = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.beach_card, parent, false))
    }


    /**
     * Internal function used by the recycler view to reuse views to save memory once they are
     * scrolled out of the screen. The function simply fills in the UI of a given view with
     * data for a given beach and creates the onClickListener for favorite button.
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(filteredBeachList[position], clickListener)

        val beach = filteredBeachList[position]
        holder.name.text = beach.getName()
        holder.airTemp.text = beach.getOceanTemp(0)
        holder.oceanTemp.text = beach.getTemp(0)
        holder.wind.text = beach.getWindSpeed(0)

        //Checks is beach is favourite, sets icon accordingly
        if (Userdata.isFavourite(beach.getName(), context as Activity)) {
            holder.favouriteButton.setImageResource(android.R.drawable.btn_star_big_on)
        } else {
            holder.favouriteButton.setImageResource(android.R.drawable.btn_star_big_off)
        }

            //Sets listener to favouriteButton
            holder.favouriteButton.setOnClickListener {
                val favourite = Userdata.toggleFavourite(beach.getName(), context)
                if (favourite) {
                    holder.favouriteButton.setImageResource(android.R.drawable.btn_star_big_on)
                } else {
                    holder.favouriteButton.setImageResource(android.R.drawable.btn_star_big_off)
                }
            }

            //sets background and weather symbol.
            val imageResource = this.context.resources.getIdentifier(
                beach.getName().lowercase(Locale.getDefault())
                    .replace(" ", "")
                    .replace("å", "a")
                    .replace("ø", "o")
                    .replace("æ", "ae"),
                "drawable", this.context.packageName
            )
            Glide.with(holder.card).load(imageResource).into(object : CustomTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    holder.img_background.background = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {} //don't remove
            })

            val iconResource = this.context.resources.getIdentifier(
                beach.getSymbol(0),
                "drawable",
                this.context.packageName
            )
            Glide.with(holder.card).load(iconResource).into(holder.weatherSymbol)
        }


    /**
     * Used to clear the recycler view of data, add new data then notify the adapter that
     * the new data is ready to be displayed.
     *
     * @param   value   a list of BeachLocationForecast
     */
    fun updateData(value : List<beachLocationForecast>)
    {
        beachList.clear()
        beachList.addAll(value)
        filteredBeachList = beachList
        this.notifyDataSetChanged()
    }


    /**
     * Used to filter the recyclerview with a text query and or a set of favorite beaches.
     * In simple terms it makes the recyclerview display a subset of the entire data.
     */
    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val string = constraint.toString()

                if(string.isEmpty())
                {
                    filteredBeachList = beachList
                }
                else
                {
                    val results = mutableListOf<beachLocationForecast>()
                    beachList.forEach {
                        if(it.getName().lowercase(Locale.getDefault()).contains(string.lowercase(Locale.getDefault()))) {
                            results.add(it)
                        }
                    }
                    filteredBeachList = results
                }


                if (filterFavourites) {
                    filteredBeachList = filteredBeachList.filter{
                        Userdata.isFavourite(it.getName(), context as Activity)
                    } as MutableList<beachLocationForecast>

                }
                return FilterResults().apply { values = filteredBeachList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredBeachList = if(results?.values == null)
                {
                    mutableListOf<beachLocationForecast>()
                }

                else
                {
                    results.values as MutableList<beachLocationForecast>
                }
                notifyDataSetChanged()
            }
        }
    }


    /**
     * returns the size of the list currently displayed.
     *
     * @return  The size of the list currently displayed.
     */
    override fun getItemCount(): Int {
        return filteredBeachList.size
    }

    //Just a inner class needed to display data.
    class MyViewHolder(cardview: View) : RecyclerView.ViewHolder(cardview)
    {
        val card = cardview
        val name = cardview.beachTitle
        val airTemp = cardview.airTemp
        val oceanTemp = cardview.OceanTemp
        val wind = cardview.wind
        val weatherSymbol = cardview.weatherSymbol
        val img_background = cardview.img_background
        val favouriteButton = cardview.favouriteButton
        fun bind(beach: beachLocationForecast, clickListener: (beachLocationForecast) -> Unit)
        {
            card.setOnClickListener { clickListener(beach)}
        }
    }
 }
