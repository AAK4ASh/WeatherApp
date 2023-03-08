package com.main.weatherapp.api

import com.main.weatherapp.api.models.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//interface representation of weather info api
interface WeatherAPI {
    @GET("current.json")//specifies api end point and api method
    fun getWeatherInfo(
        @Query("key") apiKey: String,//specifies url parameters
        @Query("q") location: String,
        @Query("aqi") aqi: String
    ): Call<ApiResponse>
}