package com.example.appeduskunta.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appeduskunta.models.Review

//
//  Author: Semen Morozov
//  date: 6.03.2023
//  Student id:2217658



@Database(entities = [Review::class], version = 5, exportSchema = false)

abstract class ReviewDataBase : RoomDatabase() {
    abstract val reviewDao: ReviewDao

    companion object{
        @Volatile
        private var INSTANCE: ReviewDataBase? = null

        fun getInstance(context: Context): ReviewDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ReviewDataBase::class.java,"review_database"
                    )
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
