package com.biola.places.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.biola.places.R
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private val countryText : String? get() = findViewById<EditText>(R.id.editTextCountryName)?.text?.toString()?.trim()
    private val mainViewModel  by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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