package com.example.moviedex.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviedex.Database.Dao.MovieDao
import com.example.moviedex.Database.Entity.Movie

@Database(entities = [Movie::class], version = 1,exportSchema = false)
abstract class MainDatabase : RoomDatabase(){

    abstract fun movieDao(): MovieDao

    companion object{
        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getDatabase(appContext: Context): MainDatabase {
            if (INSTANCE == null) {
                synchronized(MainDatabase::class) {
                    INSTANCE = Room
                        .databaseBuilder(appContext.applicationContext
                            , MainDatabase::class.java
                            ,"movies_db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}