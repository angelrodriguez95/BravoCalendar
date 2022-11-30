package com.example.bravocalendar

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.room.Room
import com.example.bravocalendar.model.AppDatabase
import com.example.bravocalendar.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel : ViewModel() {
    var nameLiveData: MutableLiveData<String> = MutableLiveData<String>()
    var emailLiveData: MutableLiveData<String> = MutableLiveData<String>()
    var passwordLiveData: MutableLiveData<String> = MutableLiveData<String>()
    var navigationController: NavController? = null
    var context: Context? = null

    fun onClickSaveChanges() = CoroutineScope(Dispatchers.IO).launch {
        if (nameLiveData.value != null && emailLiveData.value != null && passwordLiveData.value != null) {
            val name = nameLiveData.value!!
            val email = emailLiveData.value!!
            val password = passwordLiveData.value!!
            val db = Room.databaseBuilder(
                context!!,
                AppDatabase::class.java, "AppDatabase"
            ).build()
            val user = db.userDao().getUser(email)
            db.userDao().update(User(name = name, email = email, password = password, id = user.id))
            toast("User updated")
            withContext(Dispatchers.Main) {
                val action =
                    ProfileFragmentDirections.actionProfileFragmentToCalendarFragment(email,name)
                navigationController!!.navigate(action)
            }
        } else {
            toast("Fill all the fields!")
        }
    }

    private fun toast(message: String) = CoroutineScope(Dispatchers.Main).launch {
        Toast.makeText(context!!, message, Toast.LENGTH_LONG)
            .show()
    }

    fun onClickSignOut() {
        navigationController!!.navigate(R.id.action_profileFragment_to_loginFragment)
    }
}