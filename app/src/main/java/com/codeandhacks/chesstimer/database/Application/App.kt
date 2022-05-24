package com.codeandhacks.chesstimer.database.Application

import android.app.Application



class App: Application() {

    companion object{
        private var db : AppDatabase? = null

        public fun getDB() : AppDatabase {
            return db!!
        }
    }
    override fun onCreate() {
        super.onCreate()
        db = AppDatabase.getDB(applicationContext)
    }
}