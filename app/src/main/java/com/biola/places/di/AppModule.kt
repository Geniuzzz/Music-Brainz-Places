package com.biola.places.di

import com.biola.places.data.ApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule() {

    @Provides
    @Singleton
    fun providesMoshi(): Moshi
            = Moshi.Builder()
        .build()

    @Provides
    @Singleton
    fun providesApiService(moshi: Moshi) : ApiService {
        return Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
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
    }

}