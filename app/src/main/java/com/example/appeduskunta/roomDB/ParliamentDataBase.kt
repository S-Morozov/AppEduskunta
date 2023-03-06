package com.example.appeduskunta.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appeduskunta.models.Parliament


//
//  Author: Semen Morozov
//  date: 6.03.2023
//  Student id:2217658


@Database(entities = [Parliament::class], version = 5, exportSchema = false)
abstract class ParliamentDatabase : RoomDatabase() {
    abstract val parliamentDAO: ParliamentDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: ParliamentDatabase? = null

        fun getInstance(context: Context): ParliamentDatabase {
            // Only one thread at a time can access this method
            // if the INSTANCE is not null, then return it, if it is, then creates the database
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ParliamentDatabase::class.java, "parliament_database"
                    )
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}