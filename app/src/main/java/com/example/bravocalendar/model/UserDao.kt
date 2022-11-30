package com.example.bravocalendar.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("delete from user")
    fun deleteAllUsers()

    @Query("select * from user order by id desc")
    fun getAllUsers(): List<User>

    @Query("select * from user where email = :email")
    fun getUser(email: String): User

    @Query("select count() from user where email = :email")
    fun count(email: String): Int
}