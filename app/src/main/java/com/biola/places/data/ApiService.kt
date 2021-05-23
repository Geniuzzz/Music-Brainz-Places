package com.biola.places.data

import com.biola.places.BuildConfig
import com.biola.places.data.models.PlaceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers(
            "Accept: application/json",
            "User-Agent: ${BuildConfig.APPLICATION_ID}/${BuildConfig.VERSION_NAME}"
    )
    @GET("ws/2/place/")
    suspend fun place(@Query("query") query : String
    ) : Response<PlaceResponse>
}