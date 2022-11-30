package com.example.bravocalendar.data

import com.google.gson.annotations.SerializedName

data class WeatherData(
    val count: Int,
    @SerializedName("data")
    val data: List<Data>
)