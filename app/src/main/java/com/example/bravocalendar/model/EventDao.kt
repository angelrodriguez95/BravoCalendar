package com.example.bravocalendar.model

import androidx.lifecycle.LiveData
import androidx.room.*
import java.sql.Date

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(event: Event)

    @Update
    fun update(event: Event)

    @Delete
    fun delete(event: Event)

    @Query("delete from event")
    fun deleteAllEvents()

    @Query("select * from event where id = :id")
    fun getEvent(id: Int): Event

    @Query("select * from event where title = :title")
    fun getEventByTitle(title: String): Event
    
    @Query("select * from event where title = :date")
    fun getEventsByDate(date: String): List<Event>

    @Query("select * from event order by id desc")
    fun getAllEvents(): List<Event>

    @Query("select * from event where userEmail = :userEmail order by hours asc")
    fun getUserEvents(userEmail: String): List<Event>

    @Query("select * from event where userEmail = :userEmail and date = :date order by hours asc")
    fun getUserEventsByDate(userEmail: String, date: String): List<Event>

    @Query("select * from event where userEmail = :userEmail and date = :date")
    fun getUserEventsFromDate(userEmail: String, date: String): List<Event>

    @Query("select count() from event where date = :date")
    fun count(date: String): Int
}