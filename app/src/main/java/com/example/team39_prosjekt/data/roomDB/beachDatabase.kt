package com.example.team39_prosjekt.data.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Used to get the database, only a single instance can be shared trough the entire app lifecycle.
 */
@Database(entities = [beachEntity::class, gmapEntity::class], version = 1, exportSchema = false)
abstract class beachDatabase: RoomDatabase() {

    abstract fun beachDao(): beachDao

    companion object
    {
        @Volatile
        private var INSTANCE: beachDatabase? = null

        fun getDatabase(context: Context): beachDatabase
        {
            val tempInstance = INSTANCE
            if(tempInstance != null)
            {
                return tempInstance
            }

            synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    beachDatabase::class.java,
                    "BeachDatabase"
                ).createFromAsset("database/BeachDatabase.db").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}