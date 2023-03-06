package com.example.appeduskunta.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.appeduskunta.models.Review
import com.example.appeduskunta.roomDB.ReviewDataBase



//
//  Author: Semen Morozov
//  date: 6.03.2023
//  Student id:2217658

class ReviewRepository(application: Application) : AndroidViewModel(application){

    private val reviewDao = ReviewDataBase.getInstance(application).reviewDao

    var reviewList: LiveData<List<Review>> = reviewDao.getAllReview()

    suspend fun addReview(review: Review){
        reviewDao.addReview(review)
    }
}