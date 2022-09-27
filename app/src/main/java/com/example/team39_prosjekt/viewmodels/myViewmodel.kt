package com.example.team39_prosjekt.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.team39_prosjekt.data.beachData.beachLocationForecast
import com.example.team39_prosjekt.data.mainRepository
import com.example.team39_prosjekt.data.network.webscrapeData
import com.example.team39_prosjekt.data.roomDB.beachLocationData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * The viewmodel is the bridge between the UI and the Model.
 * Contains functions to fetch different types of data for the UI,
 * and different variables to tell the UI about current state.
 */
class myViewmodel(application: Application) : AndroidViewModel(application) {

    //Live data variables that the UI can subscribe to.
    val locationForecasts: MutableLiveData<List<beachLocationForecast>> by lazy { MutableLiveData<List<beachLocationForecast>>() }
    val generalDetailed: MutableLiveData<webscrapeData> by lazy { MutableLiveData<webscrapeData>() }
    val googleMapsCoordinates: MutableLiveData<List<beachLocationData>> by lazy { MutableLiveData<List<beachLocationData>>() }
    val selectedBeach: MutableLiveData<beachLocationForecast?> = MutableLiveData(null)
    val currentDisplayedUI: MutableLiveData<Int> = MutableLiveData(0)
    val beachDataRefresh: MutableLiveData<Boolean> = MutableLiveData(false)
    var locationDataUpdatedAt: Long = System.currentTimeMillis()
    private val mainRepository = mainRepository(application)

    //Fetch forecast data and coordinates on start, UI wants it anyway.
    init {
        fetchLocationData()
        getGoogleMapCoordinates()
    }


    /**
     * Communicates to the repository that it should fetch data for all beaches
     * Places the returned data in the livedata variable that views can subscribe to
     * to automatically update UI once new data arrives.
     */
    fun fetchLocationData() {
        viewModelScope.launch(Dispatchers.IO) {
            val beaches = mainRepository.fetchLocations()
            locationForecasts.postValue(beaches)
            locationDataUpdatedAt = System.currentTimeMillis()
            beachDataRefresh.postValue(false)
        }
    }


    /**
     * Communicates to the repository that it should fetch general information about a
     * given beach. Places the returned data in a livedata variable that views can
     * subscribe to to automatically update UI once data arrives.
     *
     * @param   beachName   The name of the given beach to fetch general data about.
     */
    fun fetchGeneralData(beachName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val generalInfo = mainRepository.fetchGeneralData(beachName)
            generalDetailed.postValue(generalInfo)
        }
    }


    /**
     * Communicates to the repository that it should fetch the coordinates/geographic position
     * for every beach. Places the returned data in a livedata variable that views can
     * subscribe to to automatically update UI once data arrives.
     */
    fun getGoogleMapCoordinates() {
        viewModelScope.launch(Dispatchers.IO) {
            val locations = mainRepository.googleMapsCoordinates()
            googleMapsCoordinates.postValue(locations)
        }
    }

    /**
     * Sets the livedata variable that indicates if a refresh is going on and calls
     * fetchLocationData() to fetch new data from the repository.
     */
    fun refreshRequest() {
        beachDataRefresh.postValue(true)
        fetchLocationData()
    }


    /**
     * A inner factory class of the view-model to enable the possibility to supply the
     * application context when constructing an instance of the view-model.
     *
     * @return  A myViewmodel instance with application context.
     */
    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return myViewmodel(application) as T
        }
    }
}
