package com.biola.places.di

import android.app.Application
import com.biola.places.di.viewmodels.ViewModelModule
import com.biola.places.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, ViewModelModule::class])
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun context(application: Application) : Builder

        fun build() : AppComponent
    }

}