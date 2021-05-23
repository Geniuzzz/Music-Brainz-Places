package com.biola.places.data.models

import com.squareup.moshi.JsonClass
import timber.log.Timber

@JsonClass(generateAdapter = true)
data class PlaceResponse(
        val created : String,
        val count : Int,
        val offset : String,
        val places : List<Place>
)

@JsonClass(generateAdapter = true)
data class Place(
        val id : String?,
        val score : Int?,
        val `type-id` : String?,
        val type : String?,
        val area : Area?,
        val name : String?,
        val address : String?,
        val coordinates : Coordinate?,
        val ended : String?,
        val begin : String?,
        val `life-span`: LIfeSpan
)

@JsonClass(generateAdapter = true)
data class LIfeSpan(
   val begin: String?,
   val ended: Boolean?
){
    //calculates the lifespan based on the requirement "year - 1990"
    fun getLifeSpan() : Int{
        var span = 0

      try {
          begin?.let {
              span = it.substring(0, 4).toInt() - 1990
          }
      }catch (e : Exception){
          Timber.e(e)
      }

        return span
    }
}


@JsonClass(generateAdapter = true)
data class Coordinate(
        val latitude : String?,
        val longitude : String?
        )

@JsonClass(generateAdapter = true)
data class Area(
        val id : String?,
        val `type-id` : String?,
        val type : String?,
        val name : String?,
        val `sort-name` : String?,
        val `life-span`: Any
)

