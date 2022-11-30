package com.example.bravocalendar

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.room.Room
import com.example.bravocalendar.data.Date
import com.example.bravocalendar.model.AppDatabase
import com.example.bravocalendar.model.Event
import com.example.bravocalendar.model.User
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateEventViewModel : ViewModel() {
    var date : Date? = null
    var email : String? = null
    var titleLiveData: MutableLiveData<String> = MutableLiveData<String>()
    var timeLiveData: MutableLiveData<String> = MutableLiveData<String>()
    var navigationController: NavController? = null
    var context: Context? = null

    fun onClickCreate() = CoroutineScope(Dispatchers.IO).launch {
        if (titleLiveData.value != null && timeLiveData.value != null) {
            val title = titleLiveData.value!!
            val time = timeLiveData.value!!.toCharArray()
            val hours = getNumber(time[0]) * 10 + getNumber(time[1])
            val minutes = getNumber(time[3]) * 10 + getNumber(time[4])
            val db = Room.databaseBuilder(
                context!!,
                AppDatabase::class.java, "AppDatabase"
            ).build()
            val stringDate = date!!.year.toString() + "-" + (date!!.month + 1).toString() + "-" + date!!.day.toString()
            Log.v("DATABASE", Gson().toJson(Event(title = title, date = stringDate, hours = hours, minutes = minutes, userEmail = email!!)))
            db.eventDao().insert(Event(title = title, date = stringDate, hours = hours, minutes = minutes, userEmail = email!!))
            toast("Event created")
            withContext(Dispatchers.Main) {
                backToEvents()
            }
        } else {
            toast("Fill all the fields!")
        }
    }

    private fun toast(message: String) = CoroutineScope(Dispatchers.Main).launch {
        Toast.makeText(context!!, message, Toast.LENGTH_LONG)
            .show()
    }

    fun backToEvents() {
        val action = CreateEventFragmentDirections.actionCreateEventFragmentToEventsFragment(date!!, email!!)
        navigationController!!.navigate(action)
    }

    private fun getNumber(number: Char): Int {
        var intNumber: Int?= null
        when (number) {
            '0' -> intNumber = 0
            '1' -> intNumber = 1
            '2' -> intNumber = 2
            '3' -> intNumber = 3
            '4' -> intNumber = 4
            '5' -> intNumber = 5
            '6' -> intNumber = 6
            '7' -> intNumber = 7
            '8' -> intNumber = 8
            '9' -> intNumber = 9
        }

        return intNumber!!
    }
}