package com.example.bravocalendar.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.sql.Time

var eventList = mutableListOf<Event>()

@Entity(tableName = "event")
data class Event(
    var title: String,
    var date: String,
    var hours: Int,
    var minutes: Int,
    var userEmail: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
)