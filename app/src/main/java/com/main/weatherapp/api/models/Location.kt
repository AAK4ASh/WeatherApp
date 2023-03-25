package com.main.weatherapp.api.models

data class Location(
    val name:String,val region: String,val country: String,val lat: Double,val lon: Double,val localtime_epoch: Int,val localtime: String,val tz_id: String)
