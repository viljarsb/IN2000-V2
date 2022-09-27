package com.example.team39_prosjekt.data.roomDB

import androidx.room.Dao
import androidx.room.Query


/**
 * Provides the methods used by the rest of the application to query the database.
 */
@Dao
interface beachDao {
    @Query("SELECT name FROM beachTable ORDER BY name ASC")
    fun getAllBeachNames(): List<String>

    @Query("SELECT name, lat, lon FROM beachTable")
    fun getLocations() : List<beachLocationData>

    @Query("SELECT * FROM googleMapsCoordinates")
    fun getGoogleMapsLocations(): List<beachLocationData>

    @Query("SELECT url FROM beachTable WHERE name = :beachName")
    fun getUrl(beachName : String): String
}