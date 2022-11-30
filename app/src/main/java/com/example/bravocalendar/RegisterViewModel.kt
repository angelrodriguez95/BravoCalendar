package com.example.bravocalendar

import android.content.Context
import android.util.Patterns
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

class RegisterViewModel : ViewModel() {
    var nameLiveData: MutableLiveData<String> = MutableLiveData<String>()
    var emailLiveData: MutableLiveData<String> = MutableLiveData<String>()
    var passwordLiveData: MutableLiveData<String> = MutableLiveData<String>()
    var navigationController: NavController? = null
    var context: Context? = null

    fun onClickSignUp() = CoroutineScope(Dispatchers.IO).launch {
        if (nameLiveData.value != null && emailLiveData.value != null && passwordLiveData.value != null) {
             if(isEmailValid(emailLiveData.value)) {
                val name = nameLiveData.value!!
                val email = emailLiveData.value!!
                val password = passwordLiveData.value!!
                val db = Room.databaseBuilder(
                    context!!,
                    AppDatabase::class.java, "AppDatabase"
                ).build()

                val userExists = db.userDao().count(email) > 0

                if (userExists) {
                    toast("User already exists")
                } else {
                    db.userDao().insert(User(name = name, email = email, password = password))
                    toast("User registered")
                    withContext(Dispatchers.Main) {
                        backToLogin()
                    }
                }
             } else {
                 toast("Invalid email")
             }
        } else {
            toast("Fill all the fields!")
        }
    }

    private fun backToLogin() {
        navigationController!!.navigate(R.id.action_registerFragment_to_loginFragment)
    }

    private fun toast(message: String) = CoroutineScope(Dispatchers.Main).launch {
        Toast.makeText(context!!, message, Toast.LENGTH_LONG)
            .show()
    }

    private fun isEmailValid(email: CharSequence?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email!!).matches()
    }
}