package com.example.bravocalendar.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    var name: String,
    var email: String,
    var password: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
)