package com.example.bravocalendar

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.bravocalendar.adapter.CardAdapter
import com.example.bravocalendar.data.Date
import com.example.bravocalendar.databinding.EventsFragmentBinding
import com.example.bravocalendar.model.AppDatabase
import com.example.bravocalendar.model.Event
import com.example.bravocalendar.model.eventList
import com.google.gson.Gson
import kotlinx.coroutines.*

class EventsViewModel : ViewModel() {
    var date : Date? = null
    var email : String? = null
    var navigationController: NavController? = null
    var context: Context? = null
    var binding: EventsFragmentBinding? = null

    fun setEvents() = CoroutineScope(Dispatchers.IO).launch {
        val db = Room.databaseBuilder(
            context!!,
            AppDatabase::class.java, "AppDatabase"
        ).build()
        val stringDate = date!!.year.toString() + "-" + (date!!.month + 1).toString() + "-" + date!!.day.toString()
        val events = db.eventDao().getUserEventsByDate(email!!, stringDate)
        Log.v("DATABASE", Gson().toJson(events))
        Log.v("DATABASE", stringDate)
        withContext(Dispatchers.Main) {
            binding!!.eventsView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = CardAdapter(events)
            }
        }
    }

    fun onClickCreate(){
        val action = EventsFragmentDirections.actionEventsFragmentToCreateEventFragment(email!!, date!!)
        navigationController!!.navigate(action)
    }
}