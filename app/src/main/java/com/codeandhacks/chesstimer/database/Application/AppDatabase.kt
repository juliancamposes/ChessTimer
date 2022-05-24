package com.codeandhacks.chesstimer.database.Application

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codeandhacks.chesstimer.database.dao.TimeValuesDao
import com.codeandhacks.chesstimer.database.entities.TimeValues

@Database(entities = [TimeValues::class], version = 1 , exportSchema = false)

abstract class AppDatabase : RoomDatabase(){
    abstract fun timeValuesDao(): TimeValuesDao

    companion object{
        private var db : AppDatabase? = null
        fun getDB(context : Context) : AppDatabase {

            if(db == null){
                db = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "database"
                ).build()
            }
            return db!!
        }
    }


}