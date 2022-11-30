package com.example.bravocalendar.model

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [User::class, Event::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun eventDao(): EventDao

    private var instance: AppDatabase? = null

    @Synchronized
    fun getInstance(ctx: Context): AppDatabase {
        if(instance == null)
            instance = Room.databaseBuilder(ctx.applicationContext, AppDatabase::class.java,
                "app_database")
                .fallbackToDestructiveMigration()
                .build()

        return instance!!
    }

}