package com.example.appeduskunta

import com.example.appeduskunta.models.Parliament
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET



//
//  Author: Semen Morozov
//  date: 6.03.2023
//  Student id:2217658


// Retrofit Builder to create a Retrofit object
 const val BASE_URL = "https://users.metropolia.fi/~peterh/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi)) //Retrofit can use Moshi to convert JSON response to Kotlin objects
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

//Implement the ParliamentApiService interface with getProperties returning a String from json file.

interface ParliamentApiService {
    @GET("mps.json")
    //Return list of MemberOfParliament
    suspend fun getMemberList(): MutableList<Parliament>
}

//Create the ParliamentApi object using Retrofit to implement the ParliamentApiService
object ParliamentApi {
    val service: ParliamentApiService? by lazy {
        retrofit.create(ParliamentApiService::class.java)
    }
}
