package com.example.appeduskunta

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appeduskunta.models.Parliament
import com.example.appeduskunta.roomDB.ParliamentDatabase
import kotlinx.coroutines.launch


//
//  Author: Semen Morozov
//  date: 6.03.2023
//  Student id:2217658


class AppRepository(application: Application) : AndroidViewModel(application) {

    //Reference of the Dao
    private val parliamentMemberDao =
        ParliamentDatabase.getInstance(application).parliamentDAO

    //Fetching all the data from the databaase
    var parliamentMemberList = parliamentMemberDao.getParliamentMembers()

    private val _fetchedMembersList = MutableLiveData<List<Parliament>>()
    private val fetchedMemberList: LiveData<List<Parliament>>
        get() = _fetchedMembersList

    //Initializing the functions
    init {
        viewModelScope.launch {
            try {
                getParliamentMember()
                insertMembers()
            } catch (e: Exception) {
                Log.d("****", e.toString())
            }
        }
    }

    //fetching data from server through retrofit
    private suspend fun getParliamentMember() {
        _fetchedMembersList.value = ParliamentApi.service?.getMemberList()
    }

    //Inserting parliamentMember in database
    private suspend fun insertMembers() {
        fetchedMemberList.value?.forEach {
            parliamentMemberDao.insert(it)
        }
    }
}



