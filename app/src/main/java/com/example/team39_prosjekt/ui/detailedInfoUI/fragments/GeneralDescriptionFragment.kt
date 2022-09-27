package com.example.team39_prosjekt.ui.detailedInfoUI.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer

import com.example.team39_prosjekt.R
import com.example.team39_prosjekt.data.network.webscrapeData
import com.example.team39_prosjekt.viewmodels.myViewmodel
import kotlinx.android.synthetic.main.info_view.*

class GeneralDescriptionFragment : Fragment() {
    private val viewModel: myViewmodel by activityViewModels()

    companion object { fun newInstance() = GeneralDescriptionFragment() }

    /**
     * Runs once the fragment start for the first time.
     * Simply starts to observe the selected beach variable in view-model
     * to decide what beach to fetch general data about.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.selectedBeach.observe(viewLifecycleOwner, Observer {if(it != null) {
            viewModel.fetchGeneralData(it.getName()) } })


        viewModel.generalDetailed.observe(viewLifecycleOwner, Observer { generalInfo ->
            drawData(generalInfo)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.info_view, container, false)
    }


    /**
     * Draws data on the screen based on what the webscraper could find.
     * If webscraper could not fetch any data for some reason, an endless
     * progress bar is shown.
     */
    private fun drawData(generalInfo: webscrapeData) {
        BeachName.text = generalInfo.name
        BeachDesc.text = generalInfo.description
        FacilitiyList.text = generalInfo.faciltities
        TransportDesc.text = generalInfo.transport
        WaterQuality.text = generalInfo.waterQuality

        if (!generalInfo.gotData) {
            val errorToast = Toast.makeText(this.context, "Kunne ikke laste inn info..", Toast.LENGTH_LONG)
            errorToast.show()
            return
        }

        //If data, show it, if not show endless loader.
        progressBarGeneralInfo.visibility = View.INVISIBLE
        BeachName.visibility = View.VISIBLE
        BeachDescHeader.visibility = View.VISIBLE
        BeachDesc.visibility = View.VISIBLE
        FacilitiesHeader.visibility = View.VISIBLE
        FacilitiyList.visibility = View.VISIBLE
        TransportHeader.visibility = View.VISIBLE
        TransportDesc.visibility = View.VISIBLE
        WaterQualityHeader.visibility = View.VISIBLE
        WaterQuality.visibility = View.VISIBLE
    }
}
