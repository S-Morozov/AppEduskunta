package com.example.appeduskunta.models

import androidx.room.Entity
import androidx.room.PrimaryKey

//
//  Author: Semen Morozov
//  date: 6.03.2023
//  Student id:2217658


@Entity
data class Review(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val personId: Int? = null,
    val comment: String,
    val rating: Int? = null,
)
