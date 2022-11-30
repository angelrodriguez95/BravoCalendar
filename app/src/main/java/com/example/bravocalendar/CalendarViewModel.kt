package com.example.bravocalendar

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.bravocalendar.api.ApiInterface
import com.example.bravocalendar.data.Date
import com.example.bravocalendar.data.WeatherData
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CalendarViewModel : ViewModel() {
    var name : String? = null
    var email : String? = null
    var navigationController: NavController? = null
    var context: Context? = null
    var nameLiveData: MutableLiveData<String> = MutableLiveData<String>("Hello")
    var tempLiveData: MutableLiveData<String> = MutableLiveData<String>()


    fun dateClicked(day: Int, month: Int, year: Int){
        val date = Date(day, month, year)
        val action = CalendarFragmentDirections.actionCalendarFragmentToEventsFragment(date, email!!)
        navigationController!!.navigate(action)
    }

    fun profileClicked() {
        val action = CalendarFragmentDirections.actionCalendarFragmentToProfileFragment(email!!, name!!)
        navigationController!!.navigate(action)
    }

}