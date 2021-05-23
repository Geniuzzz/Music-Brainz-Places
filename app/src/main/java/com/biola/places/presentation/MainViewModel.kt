package com.biola.places.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biola.places.data.ApiService
import com.biola.places.data.models.Place
import com.squareup.moshi.Moshi
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

class MainViewModel : ViewModel() {

    private val _places : MutableLiveData<List<Place>> = MutableLiveData()
    val places : LiveData<List<Place>> = _places

    private val apiService : ApiService =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .baseUrl("https://musicbrainz.org/")
            .client(
                OkHttpClient()
                    .newBuilder()
                    .addInterceptor(
                        HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                    )
                    .build())
            .build()
            .create(ApiService::class.java)

    fun fetchPlacesFor(country : String){

        viewModelScope.launch {
            try {
                val response = apiService.place(country)

                if (response.isSuccessful){
                    response.body()?.let {
                        //filter list for places within where lifespan > 0
                       val filteredList = it.places.filter { place -> place.`life-span`.getLifeSpan() > 0 }
                        Timber.i("XXX FL ${filteredList.size}")
                        _places.postValue(filteredList)
                    }
                }
            }catch (e: Exception){
                Timber.e(e)
            }
        }

    }

}