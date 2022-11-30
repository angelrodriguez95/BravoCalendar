package com.example.bravocalendar

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bravocalendar.data.Time
import com.example.bravocalendar.model.AppDatabase
import com.example.bravocalendar.model.Event
import com.example.bravocalendar.model.User
import com.google.gson.Gson
import kotlinx.coroutines.*
import java.sql.Date
import java.util.*

class LoginViewModel : ViewModel() {
    var emailLiveData: MutableLiveData<String> = MutableLiveData<String>()
    var passwordLiveData: MutableLiveData<String> = MutableLiveData<String>()
    var navigationController: NavController? = null
    var context: Context? = null

    fun onClickSignIn() = CoroutineScope(Dispatchers.IO).launch {
        if(emailLiveData.value != null && passwordLiveData.value != null) {
            val email = emailLiveData.value!!
            val password = passwordLiveData.value!!
            val db = Room.databaseBuilder(
                context!!,
                AppDatabase::class.java, "AppDatabase"
            ).build()

            val userExists = db.userDao().count(email) > 0

            if (userExists) {
                val user = db.userDao().getUser(email)
                if (user.password == password) {
                    withContext(Dispatchers.Main) {
                        val action = LoginFragmentDirections.actionLoginFragmentToCalendarFragment(user.email, user.name)
                        navigationController!!.navigate(action)
                    }
                } else {
                    toast("Wrong Password")
                }

            } else {
                toast("User not found")
            }
        } else {
            toast("Fill all the fields!")
        }
    }

    fun onClickRegister() {
        navigationController!!.navigate(R.id.action_loginFragment_to_registerFragment)
    }

    fun toast(message: String) = CoroutineScope(Dispatchers.Main).launch {
        Toast.makeText(context!!, message, Toast.LENGTH_LONG)
            .show()
    }

    fun cleanDB() = CoroutineScope(Dispatchers.IO).launch {
        val db = Room.databaseBuilder(
            context!!,
            AppDatabase::class.java, "AppDatabase"
        ).build()
        db.userDao().deleteAllUsers()
    }

    fun fillDB()= CoroutineScope(Dispatchers.IO).launch {
        val db = Room.databaseBuilder(
            context!!,
            AppDatabase::class.java, "AppDatabase"
        ).build()
//        db.userDao().insert(User("Angel", "joseangelpadrino@gmail.com", "Dexcom123"))
//        db.eventDao().insert(Event(title = "Play with Garu", date = "2022-11-29", hours = 10, minutes = 58, userEmail ="joseangelpadrino@gmail.com", id = 0))
//        db.eventDao().insert(Event(title = "Dentist's Appointment", date = "2022-11-29", hours = 13, minutes = 58, userEmail ="joseangelpadrino@gmail.com"))
//        db.eventDao().insert(Event(title = "Car Repair", date = "2022-11-29", hours = 10, minutes = 52, userEmail = "joseangelpadrino@gmail.com"))
//        db.eventDao().insert(Event(title = "Walk the Dog", date = "2022-11-29", hours = 9, minutes = 58, userEmail = "joseangelpadrino@gmail.com"))
////        db.eventDao().insert(Event(title = "Don't forget to eat!", date = "2022-11-29", hours = 17, minutes = 45, userEmail = "joseangelpadrino@gmail.com"))
//        db.userDao().insert(User("Garu", "garu@hotmail.com", "Dexcom123"))
       db.eventDao().insert(Event(title = "Play with Me", date = "2022-11-29", hours = 10, minutes = 58, userEmail ="garu@hotmail.com"))
//        db.eventDao().insert(Event(title = "Don't forget to eat!", date = "2022-11-28", hours = 17, minutes = 45, userEmail = "joseangelpadrino@gmail.com"))
    }

    fun checkDB()= CoroutineScope(Dispatchers.IO).launch {
        val db = Room.databaseBuilder(
            context!!,
            AppDatabase::class.java, "AppDatabase"
        ).build()
//        val users = db.userDao().getUser("joseangelpadrino@gmail.com")
        val users = db.userDao().getUser("garu@hotmail.com")
        val events = db.eventDao().getUserEvents("garu@hotmail.com")
        Log.v("DATABASE", Gson().toJson(users))
        Log.v("DATABASE", Gson().toJson(events))
    }
}