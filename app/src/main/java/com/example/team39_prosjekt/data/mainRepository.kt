package com.example.team39_prosjekt.data


import android.app.Activity
import android.app.Application
import com.example.team39_prosjekt.data.beachData.beachLocationForecast
import com.example.team39_prosjekt.data.network.*
import com.example.team39_prosjekt.data.roomDB.beachDatabase
import com.example.team39_prosjekt.data.roomDB.beachDao
import com.example.team39_prosjekt.data.roomDB.beachLocationData
import com.example.team39_prosjekt.data.userDataPersistentStorage.Userdata
import kotlinx.coroutines.*


/**
 * The single point of truth for application data and the portal to the model.
 */
class mainRepository(application: Application)
{
    //Initilize database of creation of repo.
    val beachDao: beachDao
    init { beachDao = beachDatabase.getDatabase(application).beachDao() }


    /**
     *   Fetches the location data from the ocean forecast and location forecast APIs.
     *
     *   @Return A list of beachLocationForecast objects with data from the APIs.
    */
    suspend fun fetchLocations(): List<beachLocationForecast>
     {
        val beaches = mutableListOf<beachLocationForecast>()
        val subJobs = mutableListOf<Job>()

         //Get the names and coordinates of every beach from the database.
        val locations = beachDao.getLocations()

         /* For each beach create a new coroutine, that fetches the data for
         that specific beach in async, so that several beaches can be loaded at once. */
        locations.forEach{
            subJobs.add(CoroutineScope(Dispatchers.IO).launch
            {
                /*Fetches the ocean and weather data for a given location.
                Both functions/API calls are executed async so they can run concurrently.*/
                val oceanDataResponse = async { fetchOceanData(it.lat, it.lon) }
                val weatherDataResponse = async { fetchWeatherData(it.lat, it.lon) }
                val beachObject = beachLocationForecast(it.name, oceanDataResponse.await(), weatherDataResponse.await())
                synchronized(this) { beaches.add(beachObject) }
            })
        }

        // Wait for all jobs to finish and sort the list in alf-order.
        subJobs.joinAll()
        return beaches.sortedBy { it.getName() }
    }


    /**
     *  Fetches general information about a beach for Oslokommune.no
     *
     *  @Param  beachName   The name of the beach to fetch general data about.
     *  @Return A webscapeData object that holds the information retrived.
    */
    fun fetchGeneralData(beachName: String): webscrapeData {
        //Fetch the url for the specific beach from the database.
        val url = beachDao.getUrl(beachName)
        val data = getGeneralData(beachName, url)
        return data
    }


    /**
     * Fetches the coordinates for every beach from the database so that they can be
     * displayed in the map fragment.
     *
     * @return  A list of beachLocationData objects, with name and coordinates of a beach.
     */
    fun googleMapsCoordinates(): List<beachLocationData>
    {
        return beachDao.getGoogleMapsLocations()
    }
}

