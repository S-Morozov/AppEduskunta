package com.example.appeduskunta.roomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appeduskunta.models.Review

//
//  Author: Semen Morozov
//  date: 6.03.2023
//  Student id:2217658


@Dao
interface ReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addReview(review: Review)

    @Query("SELECT * FROM Review")
    fun getAllReview(): LiveData<List<Review>>
}