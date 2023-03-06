package com.example.appeduskunta.roomDB

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.appeduskunta.models.Parliament

//
//  Author: Semen Morozov
//  date: 6.03.2023
//  Student id:2217658

@Dao
interface ParliamentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: Parliament)

    @Query("select * from Parliament")
    fun getParliamentMembers(): LiveData<List<Parliament>>
}