package com.example.appeduskunta.di

import android.app.Application
import android.content.Context


//
//  Author: Semen Morozov
//  date: 6.03.2023
//  Student id:2217658

class ParliamentApp: Application() {
    companion object {
        lateinit var appContext: Context
    }
    override fun onCreate(){
        super.onCreate()
        appContext = applicationContext
    }
}
