package com.main.weatherapp.api
import com.main.weatherapp.utiles.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    //configures and returns an instance of retrofit
    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL) //sets base url for the instance
            .addConverterFactory(GsonConverterFactory.create()).build()//specifies json parsing library, here we use gson
    }


}