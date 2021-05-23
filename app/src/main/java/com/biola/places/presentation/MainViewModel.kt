package com.biola.places.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biola.places.data.ApiService
import com.biola.places.data.models.Place
import com.biola.places.di.Repository
import com.squareup.moshi.Moshi
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val repository: Repository
) : ViewModel() {

    private val _places : MutableLiveData<List<Place>> = MutableLiveData()
    val places : LiveData<List<Place>> = _places

    fun fetchPlacesFor(country : String){

        viewModelScope.launch {
            try {

                repository.fetchPlaces(country)?.let {
                        //filter list for places within where lifespan > 0
                       val filteredList = it.places.filter { place -> place.`life-span`.getLifeSpan() > 0 }
                        Timber.i("XXX FL ${filteredList.size}")
                        _places.postValue(filteredList)
                    } // ?: ToDo handle null response

            }catch (e: Exception){
                Timber.e(e)
            }
        }

    }

}