package com.biola.places.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import com.biola.places.PlacesApp
import com.biola.places.R
import com.biola.places.di.viewmodels.ViewModelFactory
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val countryText : String? get() = findViewById<EditText>(R.id.editTextCountryName)?.text?.toString()?.trim()
    private val mainViewModel : MainViewModel  by lazy { ViewModelLazy(MainViewModel::class, factoryProducer = {viewModelFactory}, storeProducer = {this.viewModelStore}).value}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as PlacesApp).appComponent.inject(this)

        mainViewModel.places.observe(this, Observer {places ->
            //ToDo : display places on the map
        })

        findViewById<Button>(R.id.submitButton).setOnClickListener {
           if (!countryText.isNullOrEmpty()){
               mainViewModel.fetchPlacesFor(countryText!!)
           }
        }
    }
}