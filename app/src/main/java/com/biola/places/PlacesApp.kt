package com.biola.places

import android.app.Application
import com.biola.places.di.AppComponent
import com.biola.places.di.DaggerAppComponent
import timber.log.Timber

class PlacesApp : Application() {

    val appComponent : AppComponent by lazy{
        DaggerAppComponent
            .builder()
            .context(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}