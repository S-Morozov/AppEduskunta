package com.example.appeduskunta.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.appeduskunta.AppRepository



//
//  Author: Semen Morozov
//  date: 6.03.2023
//  Student id:2217658

//View model for each screen

class ParliamentMemberViewModel(application: Application): AndroidViewModel(application){
    val parliamentMemberList = AppRepository(application).parliamentMemberList
}


