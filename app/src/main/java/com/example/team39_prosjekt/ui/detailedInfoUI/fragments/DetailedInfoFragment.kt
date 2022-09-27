package com.example.team39_prosjekt.ui.detailedInfoUI.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.example.team39_prosjekt.data.beachData.beachLocationForecast
import com.example.team39_prosjekt.R
import com.example.team39_prosjekt.ui.uiStructures.Adapters.GraphListAdapter
import com.example.team39_prosjekt.viewmodels.myViewmodel
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_detailed_view.*
import kotlinx.android.synthetic.main.fragment_detailed_view.symbol
import java.text.SimpleDateFormat


class DetailedInfoFragment : Fragment() {
    private val viewModel: myViewmodel by activityViewModels()

    companion object { fun newInstance() = DetailedInfoFragment() }

    /**
     * Called once fragment is started for the first time. Simply fetches the selected beach and
     * calls draw data to draw the data on the screen, no additional API requests needed.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.selectedBeach.observe(viewLifecycleOwner, Observer {beachObject -> drawData(beachObject)})
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detailed_view, container, false)
    }


    /**
     * Draws data about the selected beach on the screen.
     * This includes some simple text, symbols and graphs.
     *
     * @param   beach   The BeachLocationForecast object representing the beach clicked
     *                  by the user in either MapViewFragment or ListViewFragment.
     */
    private fun drawData(beach: beachLocationForecast?) {
        if(beach != null)
        {
            BeachName.text = beach.getName()

            tempText.text = beach.getTemp(0)

            feelsLikeTempText.text = "føles som " + beach.getRelativeTemp(0)

            var iconResource = this.context?.resources?.getIdentifier(beach.getSymbol(0), "drawable", this.context?.packageName)
            Glide.with(symbol).load(iconResource).into(symbol)

            val sdf = SimpleDateFormat("HH:mm")

            var time = beach.getForecastTime(1)?.let { sdf.format(it) }
            timeHourOne.text = time

            time = beach.getForecastTime(2)?.let { sdf.format(it) }
            timeHourTwo.text = time

            time = beach.getForecastTime(3)?.let { sdf.format(it) }
            timeHourThree.text = time

            time = beach.getForecastTime(4)?.let { sdf.format(it) }
            timeHourFour.text = time

            time = beach.getForecastTime(5)?.let { sdf.format(it) }
            timeHourFive.text = time

            iconResource = this.context?.resources?.getIdentifier(beach.getSymbol(1), "drawable", this.context?.packageName)
            Glide.with(symbol).load(iconResource).into(symbolHourOne)

            iconResource = this.context?.resources?.getIdentifier(beach.getSymbol(2), "drawable", this.context?.packageName)
            Glide.with(symbol).load(iconResource).into(symbolHourTwo)

            iconResource = this.context?.resources?.getIdentifier(beach.getSymbol(3), "drawable", this.context?.packageName)
            Glide.with(symbol).load(iconResource).into(symbolHourThree)

            iconResource = this.context?.resources?.getIdentifier(beach.getSymbol(4), "drawable", this.context?.packageName)
            Glide.with(symbol).load(iconResource).into(symbolHourFour)

            iconResource = this.context?.resources?.getIdentifier(beach.getSymbol(5), "drawable", this.context?.packageName)
            Glide.with(symbol).load(iconResource).into(symbolHourFive)

            tempHourOne.text = beach.getTemp(1)
            tempHourTwo.text = beach.getTemp(2)
            tempHourThree.text = beach.getTemp(3)
            tempHourFour.text = beach.getTemp(4)
            tempHourFive.text = beach.getTemp(5)

            humidity.text = beach.getRelativeHumidity(0)
            wind.text = beach.getWindAndGust(0)
            rain.text = beach.getRainInterval(0)
            oceanTemp.text = beach.getOceanTemp(0)
            cloudCover.text = beach.getCloudCover(0)
            uvIndex.text = beach.getUv(0)
            airPressure.text = beach.getAirPressure(0)

            windDescription.text = beach.getWindDescription(resources.getStringArray(R.array.BaufortScale).toList())
            oceanDescription.text = beach.getOceanDescription()
            rainDescription.text = beach.getRainDescription()
            cloudDescription.text = beach.getCloudDescription()
            uvDescription.text = beach.getUvDescription(resources.getStringArray(R.array.uvIndexes).toList())
            swimDescription.text = beach.safeToSwim()

            val list = createGraphs(beach)
            val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            GraphRec.layoutManager = layoutManager
            val adapter = GraphListAdapter(beach, list, this.requireActivity())
            GraphRec.adapter = adapter

            //Smoother scrolling. 1 swipe for next graph. Will center the next item after swipe.
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(GraphRec)
        }
    }


    /**
     * Generates graph views for different types of weather conditions.
     *
     * @param   beach   A beachLocationForecast object that holds data needed for graphs.
     * @return  A list of graph info objects.
     */
    private fun createGraphs(beach: beachLocationForecast): MutableList<graphInfo> {
        val list = mutableListOf<graphInfo>()
        val lineEntriesOne = mutableListOf<Entry>()
        var LineDataSet: LineDataSet
        val barEntries = mutableListOf<BarEntry>()
        var BarDataSet: BarDataSet

        //Temperature data
        for(i in 0..5) {
            val temp = beach.getTempNumerical(i)?.toFloat()
            if(temp != null)
            {
                lineEntriesOne.add(Entry(i.toFloat(), temp))
                barEntries.add(BarEntry(i.toFloat(), temp))
            }
        }
        LineDataSet = LineDataSet(mutableListOf<Entry>().apply { addAll(lineEntriesOne) }, "Temperaturer")
        BarDataSet = BarDataSet(mutableListOf<BarEntry>().apply { addAll(barEntries) }, "Temperaturer")
        list.add(graphInfo(LineDataSet, BarDataSet,"airTemp", beach))

        lineEntriesOne.clear()
        barEntries.clear()

        //Wind data
        for(i in 0..5) {
            val windSpeed = beach.getWindSpeedNumerical(i)?.toFloat()
            if(windSpeed != null)
            {
                lineEntriesOne.add(Entry(i.toFloat(), windSpeed))
                barEntries.add(BarEntry(i.toFloat(), windSpeed))
            }
        }
        LineDataSet = LineDataSet(mutableListOf<Entry>().apply { addAll(lineEntriesOne) }, "Vindstyrke")
        BarDataSet = BarDataSet(mutableListOf<BarEntry>().apply { addAll(barEntries) }, "Vindstyrke")
        list.add(graphInfo(LineDataSet, BarDataSet,"wind", beach))

        lineEntriesOne.clear()
        barEntries.clear()

        //Rain data
        for(i in 0..5) {
            val rain = beach.getRainNumerical(i)?.toFloat()
            if(rain != null)
            {
                lineEntriesOne.add(Entry(i.toFloat(), rain))
                barEntries.add(BarEntry(i.toFloat(), rain))
            }
        }
        LineDataSet = LineDataSet(mutableListOf<Entry>().apply { addAll(lineEntriesOne) }, "Nedbør")
        BarDataSet = BarDataSet(mutableListOf<BarEntry>().apply { addAll(barEntries) }, "Nedbør")
        list.add(graphInfo(LineDataSet, BarDataSet,"rain", beach))

        lineEntriesOne.clear()
        barEntries.clear()

        //Cloud data
        for(i in 0..5) {
            val cloudCover = beach.getCloudCoverNumerical(i)?.toFloat()
            if(cloudCover != null)
            {
                lineEntriesOne.add(Entry(i.toFloat(),cloudCover))
                barEntries.add(BarEntry(i.toFloat(), cloudCover))
            }
        }
        LineDataSet = LineDataSet(mutableListOf<Entry>().apply { addAll(lineEntriesOne) }, "Skydekke")
        BarDataSet = BarDataSet(mutableListOf<BarEntry>().apply { addAll(barEntries) }, "Skydekke")
        list.add(graphInfo(LineDataSet, BarDataSet,"cloudCover", beach))

        return list
    }

    /**
     * Just a utility class needed to generate graphs.
     */
    data class graphInfo(val p1: LineDataSet, val p2: BarDataSet, val p3: String, val p4: beachLocationForecast) {
        val LineDataSet = p1
        val barDataSet = p2
        val type = p3
        val beach = p4
     }

}
