package com.example.appeduskunta.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.appeduskunta.fragments.ReviewRepository
import com.example.appeduskunta.models.Review
import kotlinx.coroutines.launch


//
//  Author: Semen Morozov
//  date: 6.03.2023
//  Student id:2217658

class ReviewViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ReviewRepository(application)
    val reviewList: LiveData<List<Review>> = repository.reviewList

//    adding review to database
    fun addReview(review: Review) {
        viewModelScope.launch {
            repository.addReview(review)
        }
    }
}