package com.example.appeduskunta.models

import androidx.room.Entity
import androidx.room.PrimaryKey

//
//  Author: Semen Morozov
//  date: 6.03.2023
//  Student id:2217658

@Entity
data class Parliament(
    val bornYear: Int,
    val constituency: String,
    val first: String,
    val last: String,
    val minister: Boolean,
    val party: String,
    @PrimaryKey
    var personNumber: Int,
    val picture: String,
    val seatNumber: Int,
    val twitter: String
)

