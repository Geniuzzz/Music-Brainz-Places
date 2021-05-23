package com.biola.places.di

import com.biola.places.data.ApiService
import com.biola.places.data.models.PlaceResponse
import java.lang.Exception
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {

    suspend fun fetchPlaces(country : String) : PlaceResponse? {
       return apiService.place(country).let {response ->
            if (response.isSuccessful){
                response.body()
            }else throw Exception(response.message() ?: "")
        }
    }
}