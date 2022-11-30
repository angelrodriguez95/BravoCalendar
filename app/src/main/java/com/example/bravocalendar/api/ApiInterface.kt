package com.example.bravocalendar.api

import com.example.bravocalendar.data.WeatherData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("v2.0/current?lat=32.522499&lon=-117.046623&key=49d13e09a6934ae4a709c48235fbf76c&include=minutely")
    fun getData(): Call<WeatherData>
}